package pl.edu.agh.to2.commands;

import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.Turtle;
import pl.edu.agh.to2.model.geometry.Utilities;

public class BackwardCommand extends ForwardCommand {
    public BackwardCommand(Board board, double length) {
        super(board, length);
    }

    @Override
    public void execute() {
        Turtle turtle = board.getTurtle();
        int halfAngleDegrees = Utilities.FULL_ANGLE_DEGREES / 2;
        turtle.addToAngleDegrees(halfAngleDegrees);
        move();
        turtle.addToAngleDegrees(-halfAngleDegrees);
    }
}
