package pl.edu.agh.to2.model;

import pl.edu.agh.to2.model.geometry.Point;

import static pl.edu.agh.to2.model.geometry.Utilities.FULL_ANGLE_DEGREES;

public class Turtle {
	private Point position;
	private EMarkerState markerState;
	private int angleDegrees;

	public Turtle() {
	    this(new Point(0, 0), EMarkerState.DOWN, 90);
	}

    public Turtle(Point position, EMarkerState markerState, int angleDegrees) {
        this.position = position;
        this.markerState = markerState;
        setAngleDegrees(angleDegrees);
    }

    public Point getPosition() { return position; }

	public void setPosition(Point position) { this.position = position; }
	
	public EMarkerState getMarkerState() { return markerState; }

	public void setMarkerState(EMarkerState markerState) { this.markerState = markerState; }
	
	public int getAngleDegrees() { return angleDegrees; }

	public void setAngleDegrees(int angleDegrees) {
	    if (angleDegrees < 0)
	        angleDegrees += FULL_ANGLE_DEGREES;
	    this.angleDegrees = angleDegrees % FULL_ANGLE_DEGREES; }

	public void addToAngleDegrees (int deltaAngle) {
	    setAngleDegrees(angleDegrees + deltaAngle);
    }
}
