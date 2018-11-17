package pl.edu.agh.to2.parser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by michal on 16.11.18.
 */
public class CommandParser {
    private List<Command> commands;

    public CommandParser(String baseText) throws ExceptionInInitializerError {
        commands = parseText(baseText);
    }
    public List<Command> getCommands() { return commands; }
    private List<Command> parseText(String baseText) throws ExceptionInInitializerError {
        List<Command> commands = new LinkedList<Command>();
        List<String> baseWords = Arrays.asList(baseText.split("[ \n\t]"));
        ListIterator<String> wordsIterator = baseWords.listIterator();
        while(wordsIterator.hasNext()) {
            try {
                String s = wordsIterator.next();
                ECommands c = ECommands.getCommand(s);
                switch(ECommands.getType(c)) {
                    case UNARY :
                        commands.add(new Command(c));
                        break;
                    case BINARY :
                        if(wordsIterator.hasNext()) {
                            String s2 = wordsIterator.next();
                            if(!s2.matches("-?(0|[1-9]\\d*)"))
                                throw new ExceptionInInitializerError();
                            int n = Integer.parseInt(s2);
                            commands.add(new Command(c, n));
                        }
                        break;
                    default : throw new ExceptionInInitializerError();
                }
            } catch (IllegalArgumentException e) {
                throw new ExceptionInInitializerError();
            }
        }
        return commands;
    }
}
