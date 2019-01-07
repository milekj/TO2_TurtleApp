package pl.edu.agh.to2.model;

import pl.edu.agh.to2.parsers.ExerciseParser;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ExercisesManager implements Serializable {
    private List<Exercise> exercises;
    private int current;

    public ExercisesManager(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public int getExercisesCount() { return exercises.size(); }

    public Exercise getCurrent() {
        return exercises.get(current);
    }

    public int getCurrentIndex() { return current; }

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

    public Exercise moveToIndex(int index) {
        if(isInRange(index))
            current = index;
        return getCurrent();
    }

    private boolean isInRange(int i) {
        return i >= 0 && i < exercises.size();
    }
}
