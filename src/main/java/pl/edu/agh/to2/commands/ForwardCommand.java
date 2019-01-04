package pl.edu.agh.to2.commands;

import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.EMarkerState;
import pl.edu.agh.to2.model.Turtle;
import pl.edu.agh.to2.model.geometry.Point;
import pl.edu.agh.to2.model.geometry.Vector;

public class ForwardCommand extends Command{
    private double length;

    public ForwardCommand(Board board, double length) {
        super(board);
        this.length = length;
    }

    @Override
    public void execute() {
        move();
    }

    protected void move() {
        Turtle turtle = board.getTurtle();
        Point turtlePosition = turtle.getPosition();
        Vector vector = new Vector(turtlePosition, turtle.getAngleDegrees(), length);
        Point vectorStartPoint = vector.getStartPoint();
        Point newPosition = (turtlePosition.equals(vectorStartPoint)) ? vector.getEndPoint() : vectorStartPoint;
        turtle.setPosition(newPosition);
        if (turtle.getMarkerState() == EMarkerState.DOWN)
            board.addVector(vector);
    }
}