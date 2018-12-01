package pl.edu.agh.to2.model;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.geometry.DisjointVectorsCollection;
import pl.edu.agh.to2.model.geometry.Point;
import pl.edu.agh.to2.model.geometry.Vector;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DisjointVectorsCollectionTest {
    @Test
    void testAdd() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(5 , 0);
        pl.edu.agh.to2.model.geometry.Vector v1 = new pl.edu.agh.to2.model.geometry.Vector(p1, 90, 10);
        pl.edu.agh.to2.model.geometry.Vector v2 = new pl.edu.agh.to2.model.geometry.Vector(p2, 90, 10);
        pl.edu.agh.to2.model.geometry.Vector v3 = new pl.edu.agh.to2.model.geometry.Vector(p1, 180, 10);
        DisjointVectorsCollection set1 = new DisjointVectorsCollection();
        set1.add(v1);
        set1.add(v2);
        set1.add(v3);
        pl.edu.agh.to2.model.geometry.Vector v4 = new pl.edu.agh.to2.model.geometry.Vector(p1, 90, 15);
        pl.edu.agh.to2.model.geometry.Vector v5 = new pl.edu.agh.to2.model.geometry.Vector(p1, 180, 10);
        Set<pl.edu.agh.to2.model.geometry.Vector> expected = new HashSet<>(Arrays.asList(v4, v5));
        Set<pl.edu.agh.to2.model.geometry.Vector> actual = set1.getVectorsSet();
        assertEquals(expected, actual);
        //two overlapping vectors, one perpendicular

        Point p3 = new Point(11, 0);
        Point p4 = new Point(21, 0);
        Point p5 = new Point(0, 0);
        pl.edu.agh.to2.model.geometry.Vector v6 = new pl.edu.agh.to2.model.geometry.Vector(p3, 90, 10);
        pl.edu.agh.to2.model.geometry.Vector v7 = new pl.edu.agh.to2.model.geometry.Vector(p4, 90, 10);
        pl.edu.agh.to2.model.geometry.Vector v8 = new pl.edu.agh.to2.model.geometry.Vector(p5, 90, 50);
        List<pl.edu.agh.to2.model.geometry.Vector> vectors2 = Arrays.asList(v6, v7, v8);
        DisjointVectorsCollection set2 = new DisjointVectorsCollection(vectors2);
        expected = new HashSet<>(Collections.singletonList(v8));
        actual = set2.getVectorsSet();
        assertEquals(expected, actual);
        //two short disjoint vectors, one long containing both of them

        Point p6 = new Point(15, 0);
        Point p7 = new Point(5, 0);
        Point p8 = new Point(0, 0);
        pl.edu.agh.to2.model.geometry.Vector v9 = new pl.edu.agh.to2.model.geometry.Vector(p8, 90, 10);
        pl.edu.agh.to2.model.geometry.Vector v10 = new pl.edu.agh.to2.model.geometry.Vector(p6, 90, 10);
        pl.edu.agh.to2.model.geometry.Vector v11 = new pl.edu.agh.to2.model.geometry.Vector(p7, 90 ,15);
        DisjointVectorsCollection set3 = new DisjointVectorsCollection(Arrays.asList(v9, v10, v11));
        pl.edu.agh.to2.model.geometry.Vector v12 = new Vector(p8, 90, 25);
        expected = new HashSet<>(Collections.singletonList(v12));
        actual = set3.getVectorsSet();
        assertEquals(expected, actual);
        //two short disjoint vectors, one long overlapping with both of them
    }

}