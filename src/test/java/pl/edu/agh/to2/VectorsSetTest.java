package pl.edu.agh.to2;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.Point;
import pl.edu.agh.to2.model.Vector;
import pl.edu.agh.to2.model.VectorsSet;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class VectorsSetTest {
    @Test
    void testAdd() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(5 , 0);
        Vector v1 = new Vector(p1, 90, 10);
        Vector v2 = new Vector(p2, 90, 10);
        Vector v3 = new Vector(p1, 180, 10);
        VectorsSet set1 = new VectorsSet();
        set1.add(v1);
        set1.add(v2);
        set1.add(v3);
        Vector v4 = new Vector(p1, 90, 15);
        Set<Vector> expected = new HashSet<>(Arrays.asList(v3, v4));
        Set<Vector> actual = set1.getVectorsSet();
        assertEquals(expected, actual);
        //two overlapping vectors, one perpendicular

        Point p3 = new Point(11, 0);
        Point p4 = new Point(21, 0);
        Vector v5 = new Vector(p3, 90, 10);
        Vector v6 = new Vector(p4, 90, 10);
        Vector v7 = new Vector(p1, 90, 50);
        List<Vector> vectors2 = Arrays.asList(v5, v6, v7);
        VectorsSet set2 = new VectorsSet(vectors2);
        expected = new HashSet<>(Collections.singletonList(v7));
        actual = set2.getVectorsSet();
        assertEquals(expected, actual);
        //two short disjoint vectors, one long merging both of them

        Point p5 = new Point(15, 0);
        Point p6 = new Point(5, 0);
        Vector v8 = new Vector(p1, 90, 10);
        Vector v9 = new Vector(p5, 90, 10);
        Vector v10 = new Vector(p6, 90 ,15);
        VectorsSet set3 = new VectorsSet(Arrays.asList(v8, v9, v10));
        Vector v11 = new Vector(p1, 90, 25);
        expected = new HashSet<>(Collections.singletonList(v11));
        actual = set3.getVectorsSet();
        assertEquals(expected, actual);
    }

}