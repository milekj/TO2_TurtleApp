package pl.edu.agh.to2.model.commands;

import pl.edu.agh.to2.model.Board;

public class RotateCommand extends Command {
    private int deltaAngleDegrees;

    public RotateCommand(int deltaAngleDegrees) {
        this.deltaAngleDegrees = deltaAngleDegrees;
    }

    @Override
    public void execute(Board board) {
        super.execute(board);
        turtle.addToAngleDegrees(deltaAngleDegrees);
    }
}
