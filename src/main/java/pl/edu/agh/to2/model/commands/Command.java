package pl.edu.agh.to2.model.commands;

import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.Turtle;

public abstract class Command {
    protected Turtle turtle;

    public void execute(Board board) {
        turtle = board.getTurtle();
    }

    public int getCommandsNumber() {
        return 1;
    }
}
