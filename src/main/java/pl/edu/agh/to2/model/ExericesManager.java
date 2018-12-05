package pl.edu.agh.to2.model;

import pl.edu.agh.to2.parser.ExerciseParser;

import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExericesManager {
    private LinkedList<Exercise> exercises;
    private ListIterator<Exercise> iterator;
    private Exercise current;

    public ExericesManager(String filePath) {
        try {
            exercises = getExercisesFromFile(filePath);
            iterator = exercises.listIterator();
        } catch (IOException e) {
            throw new IllegalArgumentException("IO error while reading exercises from file ", e);
        }
    }

    public Exercise getCurrent() {
        return current;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public boolean hasPrevious() {
        return iterator.hasPrevious();
    }

    public Exercise moveToNext() {
        if(hasNext())
            current = iterator.next();
        return current;
    }

    public Exercise moveToPrevious() {
        if(!hasNext())
            iterator.previous();
        if(hasPrevious()) {
            current = iterator.previous();
        }
        return current;
    }

    private LinkedList<Exercise> getExercisesFromFile(String filePath) throws IOException{
            Path path = Paths.get(filePath);
            String text = Files.lines(path)
                            .collect(Collectors.joining("\n"));
            ExerciseParser parser = new ExerciseParser(text);
            return parser.getAllExercises();
    }
}
