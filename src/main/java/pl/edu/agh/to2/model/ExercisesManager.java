package pl.edu.agh.to2.model;

import pl.edu.agh.to2.parsers.ExerciseParser;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class ExercisesManager implements Serializable {
    private ArrayList<Exercise> exercises;
    private int current;

    public ExercisesManager(String filePath) {
        try {
            exercises = new ArrayList<>(getExercisesFromFile(filePath));
            current = 0;
        } catch (IOException e) {
            throw new IllegalArgumentException("IO error while reading exercises from file ", e);
        }
    }

    public Exercise getCurrent() {
        return exercises.get(current);
    }

    public boolean hasNext() {
        return isInRange(current + 1);
    }

    public boolean hasPrevious() {
        return isInRange(current - 1);
    }

    public Exercise moveToNext() {
        if(hasNext())
            current++;
        return getCurrent();
    }

    public Exercise moveToPrevious() {
        if(hasPrevious())
            current--;
        return getCurrent();
    }

    private LinkedList<Exercise> getExercisesFromFile(String filePath) throws IOException{
            Path path = Paths.get(filePath);
            String text = Files.lines(path)
                            .collect(Collectors.joining("\n"));
            ExerciseParser parser = new ExerciseParser(text);
            return parser.getAllExercises();
    }

    private boolean isInRange(int i) {
        return i >= 0 && i < exercises.size();
    }
}
