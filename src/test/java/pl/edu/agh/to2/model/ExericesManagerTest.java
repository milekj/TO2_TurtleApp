package pl.edu.agh.to2.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.parser.ExerciseParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ExericesManagerTest {
    private static ExericesManager manager;
    private static String exerciseFilePathname;
    private static List<Exercise> expectedExercises;

    @BeforeAll
    static void setUpFAll() {
        try {
            File tmpExerciseFile = Files.createTempFile(null, null).toFile();
            tmpExerciseFile.deleteOnExit();
            Writer writer = new FileWriter(tmpExerciseFile);
            writer.write("np 100 pw 90 np 50 lw 90 /lw 90 np 10 /np 100 pw 90 lw 90");
            writer.close();
            exerciseFilePathname = tmpExerciseFile.getPath();
            setUpExpectedExercises();
        } catch (IOException e) {
            fail(e);
        }
    }

    private static void setUpExpectedExercises() {
        try {
            String exercisesText = Files.lines(Paths.get(exerciseFilePathname))
                    .collect(Collectors.joining("\n"));
            ExerciseParser parser = new ExerciseParser(exercisesText);
            expectedExercises = parser.getAllExercises();
        } catch (IOException e) {
            fail(e);
        }
    }

    @BeforeEach
    void setUpSingleTest(){
        manager = new ExericesManager(exerciseFilePathname);
    }

    @Test
    void testMoveToNext() {
        Exercise expected = expectedExercises.get(0);
        assertEquals(expected, manager.moveToNext());
        expected = expectedExercises.get(1);
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