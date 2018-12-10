package pl.edu.agh.to2.parser;

import pl.edu.agh.to2.model.EMarkerState;
import pl.edu.agh.to2.model.commands.*;
import pl.edu.agh.to2.model.commands.Command;

import java.util.*;

public class CommandParser {
    private Scanner scanner;
    private String token;

    public CommandParser(String commandsText) {
        scanner = new Scanner(commandsText);
    }

    public List<Command> parseCommands() {
        List<Command> commands = new LinkedList<>();
        try {
            while (scanner.hasNext()) {
                token = scanner.next();
                if(token.equals("loop"))
                    commands.add(parseLoopCommand());
                else
                    commands.add(parseSingleCommand());
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("No value specified for " + token);
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return commands;
    }

    private Command parseSingleCommand() {
        switch (token) {
            case "np":
                return new ForwardCommand(getNextDouble());

            case "ws":
                return new BackwardCommand(getNextDouble());

            case "lw":
                return new RotateCommand(-getNextInt());

            case "pw":
                return new RotateCommand(getNextInt());

            case "pod":
                return new SetMarkerStateCommand(EMarkerState.UP);

            case "opu":
                return new SetMarkerStateCommand(EMarkerState.DOWN);

            default:
                throw new IllegalStateException("Unrecognized command found: " + token);
        }
    }

    private Command parseLoopCommand() {
        /** [String with commands] variables */
        String loopSyntax = "<loop> <number> <[> <commands> <]>";
        int innerLoops = 0;
        String token;
        String loopCommands = "";

        /** [List of commands] variables */
        int loops = getNextInt();

        /**
         * Prepare [String with commands] from loop statement
         */
        if(!scanner.next().equals("["))
            throw new IllegalStateException("Validated loop syntax: " + loopSyntax);
        while( (innerLoops >= 0) && (scanner.hasNext()) ) {
            token = scanner.next();
            if(token.equals("loop"))
                innerLoops++;
            if(token.equals("]"))
                innerLoops--;
            if(innerLoops == -1)
                break;
            loopCommands = loopCommands + " " + token;
        }
        if(innerLoops != -1)
            throw new IllegalStateException("Validated loop syntax: " + loopSyntax);

        return new LoopCommand(loopCommands, loops);
    }

    private double getNextDouble() {
        return scanner.nextDouble();
    }

    private int getNextInt() {
        return scanner.nextInt();
    }
}
