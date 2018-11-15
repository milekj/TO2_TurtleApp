package pl.edu.agh.to2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pl.edu.agh.to2.model.Point;
import java.math.BigDecimal;

class PointTest {
    @Test
    void testEquals() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        assertEquals(p1, p2);
    }

    @Test
    void testNotEquals() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, -1);
        assertNotEquals(p1, p2);
    }

    @Test
    void getDistance() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0, 4.9789);
        BigDecimal expected = new BigDecimal("4.9789");
        BigDecimal actual = p1.getDistance(p2);
        assertTrue(expected.compareTo(actual) == 0);

        Point p3 = new Point(4.9789, 0);
        actual = p1.getDistance(p3);
        assertTrue(expected.compareTo(actual) == 0);

        Point p4 = new Point(2, 2);
        Point p5 = new Point(6, 5);
        expected = new BigDecimal("5");
        actual = p4.getDistance(p5);
        assertTrue(expected.compareTo(actual) == 0);
    }
}