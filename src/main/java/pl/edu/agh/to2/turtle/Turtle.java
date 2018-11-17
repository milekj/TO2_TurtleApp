package pl.edu.agh.to2.turtle;

import pl.edu.agh.to2.model.Point;

public class Turtle {
	private Point position;
	private EMarkerState markerState;
	private int angleDegrees;
	
	public Turtle() {
		this.position = new Point(0, 0);
		this.markerState = EMarkerState.DOWN;
		this.angleDegrees = 0;
	}
	public Point getPosition() { return position; }
	public void setPosition(Point position) { this.position = position; }
	
	public EMarkerState getMarkerState() { return markerState; }
	public void setMarkerState(EMarkerState markerState) { this.markerState = markerState; }
	
	public int getAngleDegrees() { return angleDegrees; }
	/** 
	 * Does not normalise the angle
	 * TO_DO: extract normalizeAngle() method from Vector class
	 * use it in method below
	 */
	public void setAngleDegrees(int angleDegrees) { this.angleDegrees = angleDegrees; }
}
