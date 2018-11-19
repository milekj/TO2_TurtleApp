package pl.edu.agh.to2.model;

//import com.sun.deploy.config.VerboseDefaultConfig;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.*;

//import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseTest {

    @Test
    void testVectorsPass() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 0);
        Point p3 = new Point(10, -10);
        Point p4 = new Point(0, -10);
        Vector e1 = new Vector(p1, 90, 10);
        Vector e2 = new Vector(p2, 180, 10);
        Vector e3 = new Vector(p3, 270, 10);
        Vector e4 = new Vector(p4, 0, 10);
        Exercise ex = new Exercise(new HashSet<>(Arrays.asList(e1, e2, e3, e4)));

        Point p5 = new Point(5, 0);
        Vector v1 = new Vector(p1, 90, 6);
        Vector v2 = new Vector(p5, 90, 5);
        Vector v3 = new Vector(p1, 180, 10);
        Vector v4 = new Vector(p3, 0, 10);
        Vector v5 = new Vector(p3, 270, 10);
        VectorsSet set = new VectorsSet(Arrays.asList(v1, v2, v3, v4, v5));
        Set<Vector> vectors = set.getVectorsSet();
        assertTrue(ex.vectorsPass(vectors));
        //square

        Point p6 = new Point(0, 0);
        Vector e5 = new Vector(p6, 90, 10);
        Exercise ex2 = new Exercise(Collections.singleton(e5));

        Point p7 = new Point(10, 0);
        Point p8 = new Point(5, 0);
        Vector v6 = new Vector(p7, 270, 5);
        Vector v7 = new Vector(p8, 270, 5);
        VectorsSet set2 = new VectorsSet(Arrays.asList(v6, v7));
        assertTrue(ex2.vectorsPass(set2.getVectorsSet()));
        //line from two lines

        double _5sqrt2 = Math.sqrt(2) * 5;
        Point p9 = new Point(5, 0);
        Vector e6 = new Vector(p1, 90, 5);
        Vector e7 = new Vector(p9, 180, 5);
        Vector e8 = new Vector(p1, 135, _5sqrt2);
        Exercise ex3 = new Exercise(new HashSet<>(Arrays.asList(e6, e7, e8)));

        Point p10 = new Point(5, -5);
        Vector v8 = new Vector(p10, 315, _5sqrt2);
        Vector v9 = new Vector(p9, 270, 5);
        Vector v10 = new Vector(p10, 0, 5);
        VectorsSet set3 = new VectorsSet(Arrays.asList(v8, v9, v10));
        assertTrue(ex3.vectorsPass(set3.getVectorsSet()));
        //triangle

    }
}