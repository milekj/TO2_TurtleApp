package pl.edu.agh.to2.commands;

import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.Turtle;

public abstract class Command {
    protected Turtle turtle;
    protected Board board;

    public Command(Board board) {
        this.board = board;
        this.turtle = board.getTurtle();
    }

    public abstract void execute();

    public int getCommandsNumber() {
        return 1;
    }
}
