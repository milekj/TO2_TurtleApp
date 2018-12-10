package pl.edu.agh.to2.model.commands;

import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.EMarkerState;
import pl.edu.agh.to2.model.geometry.Point;
import pl.edu.agh.to2.model.geometry.Vector;
import pl.edu.agh.to2.parser.CommandParser;

import java.util.LinkedList;
import java.util.List;

/**
 * Executes n times commands from loop statement
 *
 * How does it work?
 * 1. Creates String from commands inside loop
 * 2. Executes n times parseCommands() with that String
 * @return list of commands from loop copied n times
 */
public class LoopCommand extends Command {
    private String commands;
    private int loops;

    public LoopCommand(String commands, int loops) {
        this.commands = commands;
        this.loops = loops;
    }


    @Override
    public void execute(Board board) {
        super.execute(board);
        for(int i = 0; i < loops; i++) {
            new CommandParser(commands).parseCommands();
        }
    }
}
