package pl.edu.agh.to2.commands;

import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.Turtle;

public abstract class Command {
    protected Board board;

    public Command(Board board) {
        this.board = board;
    }

    public abstract void execute();

    public int getCommandsNumber() {
        return 1;
    }
}
