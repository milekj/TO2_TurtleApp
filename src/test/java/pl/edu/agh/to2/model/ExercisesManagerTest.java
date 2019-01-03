package pl.edu.agh.to2.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.parsers.ExerciseParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ExercisesManagerTest {
    private static ExercisesManager manager;
    private static List<Exercise> expectedExercises;
    private static String exercisesText = "np 100 pw 90 np 50 lw 90 /lw 90 np 10 /np 100 pw 90 lw 90";

    @BeforeAll
    private static void setUpExpectedExercises() {
        ExerciseParser parser = new ExerciseParser(exercisesText);
        expectedExercises = parser.getAllExercises();
    }

    @BeforeEach
    void setUpSingleTest(){
        manager = new ExercisesManager(expectedExercises);
    }

    @Test
    void testMoveToNext() {
        Exercise expected = expectedExercises.get(1);
        assertEquals(expected, manager.moveToNext());
        expected = expectedExercises.get(2);
        assertEquals(expected, manager.moveToNext());
        assertEquals(expected, manager.moveToNext());
    }

    @Test
    void testMoveToPrevious() {
        manager.moveToNext();
        manager.moveToNext();
        manager.moveToNext();
        Exercise expected = expectedExercises.get(1);
        assertEquals(expected, manager.moveToPrevious());
        expected = expectedExercises.get(0);
        assertEquals(expected, manager.moveToPrevious());
        assertEquals(expected, manager.moveToPrevious());
    }

    @Test
    void testHasNext() {
        assertTrue(manager.hasNext());
        manager.moveToNext();
        assertTrue(manager.hasNext());
        manager.moveToNext();
        assertFalse(manager.hasNext());
    }

    @Test
    void testHasPrevious() {
        manager.moveToNext();
        manager.moveToNext();
        manager.moveToNext();
        assertTrue(manager.hasPrevious());
        manager.moveToPrevious();
        assertTrue(manager.hasPrevious());
        manager.moveToPrevious();
        assertFalse(manager.hasPrevious());
    }



}