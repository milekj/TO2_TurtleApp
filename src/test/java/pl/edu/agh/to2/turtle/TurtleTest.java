package pl.edu.agh.to2.turtle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pl.edu.agh.to2.model.Point;

class TurtleTest {

	@Test
	void testInitialiseTurtle() {
		Turtle t = new Turtle();
		assertEquals(t.getPosition(), new Point(0, 0));
		assertEquals(t.getAngleDegrees(), 0);
		assertEquals(t.getMarkerState(), EMarkerState.DOWN);
	}
}
