package pl.edu.agh.to2.model.commands;

import pl.edu.agh.to2.model.Board;

public class BackwardCommand extends ForwardCommand {
    public BackwardCommand(double length) {
        super(length);
    }

    @Override
    public void execute(Board board) {
        turtle = board.getTurtle();
        turtle.addToAngleDegrees(180);
        super.execute(board);
        turtle.addToAngleDegrees(-180);
    }
}
