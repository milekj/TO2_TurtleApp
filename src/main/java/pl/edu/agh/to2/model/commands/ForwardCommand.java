package pl.edu.agh.to2.model.commands;

import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.EMarkerState;
import pl.edu.agh.to2.model.geometry.Point;
import pl.edu.agh.to2.model.geometry.Vector;

public class ForwardCommand extends Command{
    private double length;

    public ForwardCommand(double length) {
        this.length = length;
    }

    @Override
    public void execute(Board board) {
        super.execute(board);
        Point turtlePosition = turtle.getPosition();
        Point newPosition;
        Vector vector = new Vector(turtlePosition, turtle.getAngleDegrees(), length);
        Point vectorStartPoint = vector.getStartPoint();
        newPosition = (turtlePosition.equals(vectorStartPoint)) ? vector.getEndPoint() : vectorStartPoint;
        turtle.setPosition(newPosition);
        if (turtle.getMarkerState() == EMarkerState.DOWN)
            board.addVector(vector);
    }
}