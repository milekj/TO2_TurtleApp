package pl.edu.agh.to2;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.*;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.*;

class VectorTest {
    @Test
    void testEquals() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        Vector v1 = new Vector(p1, 90, 10);
        Vector v2 = new Vector(p2, 90, 10);
        assertEquals(v1, v2);
    }

    @Test
    void testNotEquals() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, -1);

        Vector v1 = new Vector(p1, 90, 10);
        Vector v2 = new Vector(p2, 90, 10);
        assertNotEquals(v1, v2);

        Vector v3 = new Vector(p1, 30, 10);
        assertNotEquals(v1 ,v3);

        Vector v4 = new Vector(p1, 90, 100);
        assertNotEquals(v1, v4);
    }

    @Test
    void testNormalizeAngle() {
        Point p1 = new Point(0, 0);

        Point p2 = new Point(10, 0);
        Vector v1 = new Vector(p1, 90, 10);
        Vector v2 = new Vector(p2, 270, 10);
        assertEquals(v1, v2);

        Point p3 = new Point(0, 10);
        Vector v3 = new Vector(p1, 0, 10);
        Vector v4 = new Vector(p3, 180, 10);
        assertEquals(v3, v4);


        Vector v5 = new Vector(p1, 30, 10);
        Vector v6 = new Vector(getPointForOppositeVector(30, 10), 210, 10);
        assertEquals(v5, v6);

        Vector v7 = new Vector(p1, 111, 100);
        Vector v8 = new Vector(getPointForOppositeVector(111, 100), 291, 100);
    }

    private Point getPointForOppositeVector(int angleDegrees, int length) {
        double radians = toRadians(angleDegrees);
        double x = sin(radians) * length;
        double y = cos(radians) * length;
        return new Point(x, y);
    }

    @Test
    void testMergeIfOverlapping() {
        Point p1 = new Point(0, 0);
        Vector v1 = new Vector(p1, 90, 2);

        Point p2 = new Point(1 , 0);
        Vector v2 = new Vector(p2, 90, 2);
        Vector expected = new Vector(p1, 90, 3);
        Vector actual = v1.mergeIfOverlapping(v2);
        assertEquals(expected, actual);
        actual = v2.mergeIfOverlapping(v1);
        assertEquals(expected, actual);
        //overlapping partially

        Vector v3 = new Vector(p1, 45, 2);
        assertNull(v1.mergeIfOverlapping(v3));
        assertNull(v3.mergeIfOverlapping(v1));
        //not perpendicular

        Point p6 = new Point(3, 0);
        Vector v8 = new Vector(p6, 90, 1);
        Vector v4 = new Vector(p1, 90, 4);
        expected = v4;
        actual = v4.mergeIfOverlapping(v8);
        assertEquals(expected, actual);
        //overlapping by one point from inside


        expected = v1;
        actual = v1.mergeIfOverlapping(v1);
        assertEquals(expected, actual);
        //overlapping with itself

        Point p4 = new Point(100, 100);
        Vector v5 = new Vector(p4, 90, 10);
        assertNull(v1.mergeIfOverlapping(v5));
        assertNull(v5.mergeIfOverlapping(v1));
        //perpendicular, but not overlapping

        Point p5 = new Point(1, 0);
        Vector v6 = new Vector(p1, 90, 10);
        Vector v7 = new Vector(p5, 90, 1);
        expected = new Vector(p1, 90, 10);
        actual = v6.mergeIfOverlapping(v7);
        assertEquals(expected, actual);
        //overlapping, but one is included in the other

        Point p3 = new Point(10, 0);
        Point p7 = new Point(5, 0);
        Vector v10 = new Vector(p3, 270, 5);
        Vector v9 = new Vector(p7, 270, 5);
        expected = new Vector(p1, 90, 10);
        actual = v10.mergeIfOverlapping(v9);
        assertEquals(expected, actual);
        actual = v9.mergeIfOverlapping(v10);
        assertEquals(expected, actual);
        //overlapping by one point from outside
    }
}