package pl.edu.agh.to2.parsers;

import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.EMarkerState;
import pl.edu.agh.to2.commands.*;
import pl.edu.agh.to2.commands.Command;

import java.util.*;

public class CommandParser {
    private Board board;
    private Scanner scanner;
    private String token;

    public CommandParser(String commandsText, Board board) {
        scanner = new Scanner(commandsText);
        this.board = board;
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
                return new ForwardCommand(board, getNextDouble());

            case "ws":
                return new BackwardCommand(board, getNextDouble());

            case "lw":
                return new RotateCommand(board, -getNextInt());

            case "pw":
                return new RotateCommand(board, getNextInt());

            case "pod":
                return new SetMarkerStateCommand(board, EMarkerState.UP);

            case "opu":
                return new SetMarkerStateCommand(board, EMarkerState.DOWN);

            case "powtórz":
                return parseLoopCommand();

            default:
                throw new IllegalStateException("Unrecognized command found: " + token);
        }
    }

    private Command parseLoopCommand() {
        int repeatsCount = getNextInt();
        String loopToken = scanner.next();
        if (!loopToken.equals("["))
            throw new IllegalStateException("No `[` after loop count");
        return new LoopCommand(board, getLoopBodyCommands(), repeatsCount);
    }

    private List<Command> getLoopBodyCommands() {
        StringBuilder loopBody = new StringBuilder();
        int bracesCount = 1;
        while (scanner.hasNext()) {
            String loopToken = scanner.next();
            if (loopToken.equals("["))
                bracesCount ++;
            else if (loopToken.equals("]")) {
                bracesCount--;
                if(bracesCount == 0)
                    return new CommandParser(loopBody.toString(), board).parseCommands();
            }
            loopBody.append(" ")
                    .append(loopToken);
        }
        throw new IllegalStateException("No matching `]` for `[`");
    }

    private double getNextDouble() {
        return scanner.nextDouble();
    }

    private int getNextInt() {
        return scanner.nextInt();
    }
}
