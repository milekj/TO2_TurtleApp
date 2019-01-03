package pl.edu.agh.to2.commands;

import pl.edu.agh.to2.model.Board;

public class RotateCommand extends Command {
    private int deltaAngleDegrees;

    public RotateCommand(Board board, int deltaAngleDegrees) {
        super(board);
        this.deltaAngleDegrees = deltaAngleDegrees;
    }

    @Override
    public void execute() {
        turtle.addToAngleDegrees(deltaAngleDegrees);
    }
}
