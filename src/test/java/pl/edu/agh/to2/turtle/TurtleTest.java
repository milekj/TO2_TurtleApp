package pl.edu.agh.to2.turtle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pl.edu.agh.to2.model.EMarkerState;
import pl.edu.agh.to2.model.geometry.Point;
import pl.edu.agh.to2.model.Turtle;

class TurtleTest {

	@Test
	void testInitialiseTurtle() {
		Turtle t = new Turtle();
		assertEquals(t.getPosition(), new Point(0, 0));
		assertEquals(t.getAngleDegrees(), 90);
		assertEquals(t.getMarkerState(), EMarkerState.DOWN);
	}
}
