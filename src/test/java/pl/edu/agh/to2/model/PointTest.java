package pl.edu.agh.to2.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static pl.edu.agh.to2.model.geometry.Utilities.*;

import pl.edu.agh.to2.model.geometry.Point;
import pl.edu.agh.to2.model.geometry.Utilities;

import java.math.BigDecimal;

class PointTest {
    @Test
    void testEquals() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        assertEquals(p1, p2);

        Point p3 = new Point(0, 0);
        Point p4 = new Point(1, -1);
        assertNotEquals(p3, p4);
    }

    @Test
    void getDistance() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0, 4.9789);
        double expected = 4.9789;
        double actual = p1.getDistance(p2);
        assertTrue(compareAsBigDecimals(expected, actual) == 0);

        Point p3 = new Point(0,0);
        Point p4 = new Point(4.9789, 0);
        actual = p3.getDistance(p4);
        assertTrue(compareAsBigDecimals(expected, actual) == 0);

        Point p5 = new Point(2, 2);
        Point p6 = new Point(6, 5);
        expected = 5;
        actual = p5.getDistance(p6);
        assertTrue(compareAsBigDecimals(expected, actual) == 0);
    }
}