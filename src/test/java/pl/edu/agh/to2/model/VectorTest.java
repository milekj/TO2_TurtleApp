package pl.edu.agh.to2.model;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.geometry.Point;
import pl.edu.agh.to2.model.geometry.Vector;

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

        Point p3 = new Point(0, 0);
        Point p4 = new Point(1, -1);
        Vector v3 = new Vector(p3, 90, 10);
        Vector v4 = new Vector(p4, 90, 10);
        assertNotEquals(v3, v4);

        Point p5 = new Point(0, 0);
        Vector v5 = new Vector(p5, 30, 10);
        Vector v6 = new Vector(p5, 90, 10);
        assertNotEquals(v5 ,v6);

        Point p6 = new Point(0, 0);
        Vector v7 = new Vector(p6, 90, 100);
        Vector v8 = new Vector(p6, 90, 10);
        assertNotEquals(v7, v8);
    }

    @Test
    void testNormalizeAngle() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 0);
        Vector v1 = new Vector(p1, 90, 10);
        Vector v2 = new Vector(p2, 270, 10);
        assertEquals(v1, v2);

        Point p3 = new Point(0, 0);
        Point p4 = new Point(0, 10);
        Vector v3 = new Vector(p3, 0, 10);
        Vector v4 = new Vector(p4, 180, 10);
        assertEquals(v3, v4);

        Point p5 = new Point(0, 0);
        Vector v5 = new Vector(p5, 30, 10);
        Vector v6 = new Vector(getPointForOppositeVector(30, 10), 210, 10);
        assertEquals(v5, v6);

        Point p6 = new Point(0, 0);
        Vector v7 = new Vector(p6, 111, 100);
        Vector v8 = new Vector(getPointForOppositeVector(111, 100), 291, 100);
        assertEquals(v7, v8);
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
        Point p2 = new Point(1 , 0);
        Vector v1 = new Vector(p1, 90, 2);
        Vector v2 = new Vector(p2, 90, 2);
        Vector expected = new Vector(p1, 90, 3);
        Vector actual = v1.mergeIfOverlapping(v2);
        assertEquals(expected, actual);
        actual = v2.mergeIfOverlapping(v1);
        assertEquals(expected, actual);
        //overlapping partially


        Point p3 = new Point(0, 0);
        Vector v3 = new Vector(p3, 90, 2);
        Vector v4 = new Vector(p3, 45, 2);
        assertNull(v3.mergeIfOverlapping(v4));
        assertNull(v4.mergeIfOverlapping(v3));
        //not perpendicular

        Point p5 = new Point(0, 0);
        Point p6 = new Point(3, 0);
        Vector v5 = new Vector(p5, 90, 4);
        Vector v6 = new Vector(p6, 90, 1);
        expected = new Vector(p5, 90, 4);
        actual = v5.mergeIfOverlapping(v6);
        assertEquals(expected, actual);
        actual = v6.mergeIfOverlapping(v5);
        assertEquals(expected, actual);
        //overlapping by one point from inside


        Point p7 = new Point(0, 0);
        Vector v7 = new Vector(p7, 90, 2);
        expected = v7;
        actual = v7.mergeIfOverlapping(v7);
        assertEquals(expected, actual);
        //overlapping with itself

        Point p8 = new Point(100, 100);
        Vector v8 = new Vector(p8, 90, 10);
        Vector v9 = new Vector(p1, 90, 2);
        assertNull(v9.mergeIfOverlapping(v8));
        assertNull(v8.mergeIfOverlapping(v9));
        //perpendicular, but not overlapping

        Point p9 = new Point(1, 0);
        Point p10 = new Point(0, 0);
        Vector v10 = new Vector(p10, 90, 10);
        Vector v11 = new Vector(p9, 90, 1);
        expected = new Vector(p10, 90, 10);
        actual = v10.mergeIfOverlapping(v11);
        assertEquals(expected, actual);
        actual = v11.mergeIfOverlapping(v10);
        assertEquals(expected, actual);
        //overlapping, but one is included in the other

        Point p11 = new Point(10, 0);
        Point p12 = new Point(5, 0);
        Point p13 = new Point(0, 0);
        Vector v12 = new Vector(p11, 270, 5);
        Vector v13 = new Vector(p12, 270, 5);
        expected = new Vector(p13, 90, 10);
        actual = v12.mergeIfOverlapping(v13);
        assertEquals(expected, actual);
        actual = v13.mergeIfOverlapping(v12);
        assertEquals(expected, actual);
        //overlapping by one point from outside
    }
}