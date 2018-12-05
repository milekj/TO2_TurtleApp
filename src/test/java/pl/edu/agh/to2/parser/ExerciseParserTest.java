package pl.edu.agh.to2.parser;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.Exercise;
import pl.edu.agh.to2.model.geometry.Point;
import pl.edu.agh.to2.model.geometry.Vector;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExerciseParserTest {
    @Test
    void testGetAllExercises() {
        String singleExerciseText = "np 100 pw 90\nnp 100 pw 90";
        ExerciseParser pa1 = new ExerciseParser(singleExerciseText);
        Point p1 = new Point(0, 0);
        Point p2 = new Point(100, -100);
        Vector v1 = new Vector(p1, 90, 100);
        Vector v2 = new Vector(p2, 0, 100);
        Exercise expected = new Exercise(Arrays.asList(v1, v2), 4);
        List<Exercise> actual = pa1.getAllExercises();
        assertEquals(1, actual.size());
        assertEquals(expected, actual.get(0));

        String manyExercisesText = "np 100 pw 90 /np 50 lw 90 /np 10 pw 90";
        ExerciseParser pa2 = new ExerciseParser(manyExercisesText);
        Point p3 = new Point(0, 0);
        Vector v3 = new Vector(p3, 90, 100);
        Vector v4 = new Vector(p3, 90, 50);
        Vector v5 = new Vector(p3, 90, 10);
        List<Exercise> expectedList = Arrays.asList(new Exercise(Collections.singletonList(v3), 2),
                                        new Exercise(Collections.singletonList(v4), 2),
                                        new Exercise(Collections.singletonList(v5), 2));
        List<Exercise> actualList = pa2.getAllExercises();
        assertEquals(expectedList, actualList);

        String invalidExercise = "certainly not a valid exercise";
        ExerciseParser pa3 = new ExerciseParser(invalidExercise);
        pa3.getAllExercises();
        assertEquals(1, pa3.getParseErrors().size());
    }
}