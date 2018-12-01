package pl.edu.agh.to2.model;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.geometry.DisjointVectorsCollection;
import pl.edu.agh.to2.model.geometry.Point;
import pl.edu.agh.to2.model.geometry.Vector;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class ExerciseTest {
    @Test
    void testEquals() {
        Point p1 = new Point(0, 0);
        pl.edu.agh.to2.model.geometry.Vector v1 = new pl.edu.agh.to2.model.geometry.Vector(p1, 10, 90);
        List<pl.edu.agh.to2.model.geometry.Vector> l1 = Collections.singletonList(v1);
        List<pl.edu.agh.to2.model.geometry.Vector> l2 = Arrays.asList(v1, v1);
        Exercise e1 = new Exercise(l1);
        Exercise e2 = new Exercise(l2);
        assertTrue(e1.equals(e2));
        assertTrue(e2.equals(e1));

        Point p2 = new Point(0, 0);
        pl.edu.agh.to2.model.geometry.Vector v3 = new pl.edu.agh.to2.model.geometry.Vector(p1, 10, 90);
        pl.edu.agh.to2.model.geometry.Vector v4 = new pl.edu.agh.to2.model.geometry.Vector(p1, 100, 0);
        List<pl.edu.agh.to2.model.geometry.Vector> l3 = Collections.singletonList(v3);
        List<pl.edu.agh.to2.model.geometry.Vector> l4 = Collections.singletonList(v4);
        Exercise e3 = new Exercise(l3);
        Exercise e4 = new Exercise(l4);
        assertFalse(e3.equals(e4));
        assertFalse(e4.equals(e3));
    }

    @Test
    void testVectorsPass() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 0);
        Point p3 = new Point(10, -10);
        Point p4 = new Point(0, -10);
        pl.edu.agh.to2.model.geometry.Vector e1 = new pl.edu.agh.to2.model.geometry.Vector(p1, 90, 10);
        pl.edu.agh.to2.model.geometry.Vector e2 = new pl.edu.agh.to2.model.geometry.Vector(p2, 180, 10);
        pl.edu.agh.to2.model.geometry.Vector e3 = new pl.edu.agh.to2.model.geometry.Vector(p3, 270, 10);
        pl.edu.agh.to2.model.geometry.Vector e4 = new pl.edu.agh.to2.model.geometry.Vector(p4, 0, 10);
        Exercise ex = new Exercise(Arrays.asList(e1, e2, e3, e4));

        Point p5 = new Point(5, 0);
        pl.edu.agh.to2.model.geometry.Vector v1 = new pl.edu.agh.to2.model.geometry.Vector(p1, 90, 6);
        pl.edu.agh.to2.model.geometry.Vector v2 = new pl.edu.agh.to2.model.geometry.Vector(p5, 90, 5);
        pl.edu.agh.to2.model.geometry.Vector v3 = new pl.edu.agh.to2.model.geometry.Vector(p1, 180, 10);
        pl.edu.agh.to2.model.geometry.Vector v4 = new pl.edu.agh.to2.model.geometry.Vector(p3, 0, 10);
        pl.edu.agh.to2.model.geometry.Vector v5 = new pl.edu.agh.to2.model.geometry.Vector(p3, 270, 10);
        DisjointVectorsCollection set = new DisjointVectorsCollection(Arrays.asList(v1, v2, v3, v4, v5));
        Set<pl.edu.agh.to2.model.geometry.Vector> vectors = set.getVectorsSet();
        assertTrue(ex.vectorsPass(Arrays.asList(v1, v2, v3, v4, v5)));
        //square

        Point p6 = new Point(0, 0);
        pl.edu.agh.to2.model.geometry.Vector e5 = new pl.edu.agh.to2.model.geometry.Vector(p6, 90, 10);
        Exercise ex2 = new Exercise(Collections.singletonList(e5));

        Point p7 = new Point(10, 0);
        Point p8 = new Point(5, 0);
        pl.edu.agh.to2.model.geometry.Vector v6 = new pl.edu.agh.to2.model.geometry.Vector(p7, 270, 5);
        pl.edu.agh.to2.model.geometry.Vector v7 = new pl.edu.agh.to2.model.geometry.Vector(p8, 270, 5);
        DisjointVectorsCollection set2 = new DisjointVectorsCollection(Arrays.asList(v6, v7));
        assertTrue(ex2.vectorsPass(Arrays.asList(v6, v7)));
        //line from two lines

        double _5sqrt2 = Math.sqrt(2) * 5;
        Point p9 = new Point(5, 0);
        pl.edu.agh.to2.model.geometry.Vector e6 = new pl.edu.agh.to2.model.geometry.Vector(p1, 90, 5);
        pl.edu.agh.to2.model.geometry.Vector e7 = new pl.edu.agh.to2.model.geometry.Vector(p9, 180, 5);
        pl.edu.agh.to2.model.geometry.Vector e8 = new pl.edu.agh.to2.model.geometry.Vector(p1, 135, _5sqrt2);
        Exercise ex3 = new Exercise(Arrays.asList(e6, e7, e8));

        Point p10 = new Point(5, -5);
        pl.edu.agh.to2.model.geometry.Vector v8 = new pl.edu.agh.to2.model.geometry.Vector(p10, 315, _5sqrt2);
        pl.edu.agh.to2.model.geometry.Vector v9 = new pl.edu.agh.to2.model.geometry.Vector(p9, 270, 5);
        pl.edu.agh.to2.model.geometry.Vector v10 = new Vector(p10, 0, 5);
        DisjointVectorsCollection set3 = new DisjointVectorsCollection(Arrays.asList(v8, v9, v10));
        assertTrue(ex3.vectorsPass(Arrays.asList(v8, v9, v10)));
        //triangle

    }
}