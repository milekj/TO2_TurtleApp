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

    private double getNextDouble() {
        return scanner.nextDouble();
    }

    private int getNextInt() {
        return scanner.nextInt();
    }
}
