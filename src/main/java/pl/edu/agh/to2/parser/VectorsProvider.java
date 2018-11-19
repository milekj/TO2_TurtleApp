package pl.edu.agh.to2.parser;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.to2.model.*;
import pl.edu.agh.to2.model.EMarkerState;
import pl.edu.agh.to2.model.Turtle;

public class VectorsProvider {
	private Turtle currentPosition;
	private List<Vector> vectors = new LinkedList<Vector>();
	
	public VectorsProvider(Turtle startingPosition, List<Command> commands) {
		this.currentPosition = startingPosition;
		for(Command c : commands) {
			parseNextCommand(vectors, currentPosition, c);
		}
	}
	public List<Vector> getVectors() { return vectors; }
	
	private void parseNextCommand(List<Vector> vList, Turtle pos, Command c) {
		switch(c.getCommand()) {
			case NP	:
			case WS :
				if(pos.getMarkerState() == EMarkerState.DOWN)
					addToVectors(vList, pos, c);
			default :
				updateTurtle(pos, c);
		}
	}
	private void addToVectors(List<Vector> vList, Turtle pos, Command c) {
		Vector v = createVector(pos, c);
		vList.add(v);
	}
	private Vector createVector(Turtle currentPosition, Command c) {
		Point p		= currentPosition.getPosition();
		int angle	= currentPosition.getAngleDegrees();
		double length	= (double)c.getNumber();
		if(c.getCommand() == ECommands.WS)
			angle = ( angle + 180 ) % 360;
		return new Vector(p, angle, length);
	}
	
	private void updateTurtle(Turtle pos, Command c) {
		int newAngle;
		switch(c.getCommand()) {
			case NP	:
			case WS :
				pos.setPosition(createNewPosition(pos, c));
				break;
			case POD:
				pos.setMarkerState(EMarkerState.UP);
				break;
			case OPU:
				pos.setMarkerState(EMarkerState.DOWN);
				break;
			case LW	:
				newAngle = ( 360 + pos.getAngleDegrees() + c.getNumber() ) % 360;
				pos.setAngleDegrees(newAngle);
				break;
			case PW	:
				newAngle = ( 360 + pos.getAngleDegrees() - c.getNumber() ) % 360;
				pos.setAngleDegrees(newAngle);
				break;
		}
	}
	private Point createNewPosition(Turtle pos, Command c) {
		int currentAngle;
		double radians;
		BigDecimal xDifference;
		BigDecimal yDifference;
		BigDecimal newX;
		BigDecimal newY;
		
		currentAngle = pos.getAngleDegrees();
		if(c.getCommand() == ECommands.WS)
			currentAngle = ( currentAngle + 180 ) % 360;
		radians = Math.toRadians(currentAngle);
		xDifference = Utilities.newBigDecimal( Math.cos(radians) * c.getNumber() );
		yDifference = Utilities.newBigDecimal( Math.sin(radians) * c.getNumber() );
		newX = pos.getPosition().getX().add(xDifference);
		newY = pos.getPosition().getY().add(yDifference);
		
		return new Point(newX, newY);
	}
}
