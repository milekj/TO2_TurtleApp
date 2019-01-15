package pl.edu.agh.to2.model;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.geometry.DisjointVectorsCollection;
import pl.edu.agh.to2.model.geometry.Point;
import pl.edu.agh.to2.model.geometry.Vector;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.edu.agh.to2.model.ExerciseGrade.*;

class ExerciseTest {
    @Test
    void testEquals() {
        Point p1 = new Point(0, 0);
        Vector v1 = new Vector(p1, 10, 90);
        List<Vector> l1 = Collections.singletonList(v1);
        List<Vector> l2 = Arrays.asList(v1, v1);
        Exercise e1 = new Exercise(l1, "", 1);
        Exercise e2 = new Exercise(l2,"", 2);
        assertFalse(e1.equals(e2));
        assertFalse(e2.equals(e1));

        Point p2 = new Point(0, 0);
        Vector v3 = new Vector(p1, 10, 90);
        Vector v4 = new Vector(p1, 100, 0);
        List<Vector> l3 = Collections.singletonList(v3);
        List<Vector> l4 = Collections.singletonList(v4);
        Exercise e3 = new Exercise(l3, "", 1);
        Exercise e4 = new Exercise(l4, "", 1);
        assertFalse(e3.equals(e4));
        assertFalse(e4.equals(e3));

        Point p3 = new Point(0, 0);
        Vector v5 = new Vector(p3, 10, 90);
        Vector v6 = new Vector(p3, 10, 90);
        Exercise e5 = new Exercise(Collections.singletonList(v5), "",  1);
        Exercise e6 = new Exercise(Collections.singletonList(v6), "",  1);
        assertTrue(e5.equals(e6));
        assertTrue(e6.equals(e5));
    }

    @Test
    void testEvaluate() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 0);
        Point p3 = new Point(10, -10);
        Point p4 = new Point(0, -10);
        Vector e1 = new Vector(p1, 90, 10);
        Vector e2 = new Vector(p2, 180, 10);
        Vector e3 = new Vector(p3, 270, 10);
        Vector e4 = new Vector(p4, 0, 10);
        Exercise ex = new Exercise(Arrays.asList(e1, e2, e3, e4), "",  8);

        Point p5 = new Point(5, 0);
        Vector v1 = new Vector(p1, 90, 6);
        Vector v2 = new Vector(p5, 90, 5);
        Vector v3 = new Vector(p1, 180, 10);
        Vector v4 = new Vector(p3, 0, 10);
        Vector v5 = new Vector(p3, 270, 10);
        ExerciseGrade expected = SOLVED;
        assertEquals(expected, ex.evaluate(Arrays.asList(v1, v2, v3, v4, v5), 10));
        expected = IDEAL;
        assertEquals(expected, ex.evaluate(Arrays.asList(v1, v2, v3, v4, v5), 8));
        //square

        Point p6 = new Point(0, 0);
        Vector e5 = new Vector(p6, 90, 10);
        Exercise ex2 = new Exercise(Collections.singletonList(e5), "",  1);

        Point p7 = new Point(10, 0);
        Point p8 = new Point(5, 0);
        Vector v6 = new Vector(p7, 270, 5);
        Vector v7 = new Vector(p8, 270, 5);
        DisjointVectorsCollection set2 = new DisjointVectorsCollection(Arrays.asList(v6, v7));
        expected = SOLVED;
        assertEquals(expected, ex2.evaluate(Arrays.asList(v6, v7), 2));
        expected = IDEAL;
        assertEquals(expected, ex2.evaluate(Arrays.asList(v6, v7), 1));
        //line from two lines

        double _5sqrt2 = Math.sqrt(2) * 5;
        Point p9 = new Point(5, 0);
        Vector e6 = new Vector(p1, 90, 5);
        Vector e7 = new Vector(p9, 180, 5);
        Vector e8 = new Vector(p1, 135, _5sqrt2);
        Exercise ex3 = new Exercise(Arrays.asList(e6, e7, e8), "",  3);

        Point p10 = new Point(5, -5);
        Vector v8 = new Vector(p10, 315, _5sqrt2);
        Vector v9 = new Vector(p9, 270, 5);
        Vector v10 = new Vector(p10, 0, 5);
        expected = SOLVED;
        assertEquals(expected, ex3.evaluate(Arrays.asList(v8, v9, v10), 4));
        expected = IDEAL;
        assertEquals(expected, ex3.evaluate(Arrays.asList(v8, v9, v10), 3));
        expected = IDEAL;
        assertEquals(expected, ex3.evaluate(Collections.singletonList(v7), 1));
        //triangle


    }
}