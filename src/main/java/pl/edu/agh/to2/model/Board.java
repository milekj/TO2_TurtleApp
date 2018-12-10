package pl.edu.agh.to2.model;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import pl.edu.agh.to2.model.commands.Command;
import pl.edu.agh.to2.model.geometry.Vector;

import java.util.LinkedList;
import java.util.List;

public class Board implements ObservableValue<Board>{
    private Turtle turtle;
    private LinkedList<Vector> vectors;
    private Exercise exercise;
    private int commandsNumber;

    private List<ChangeListener<? super Board>> listeners;

    public Board() {
        turtle = new Turtle();
        vectors = new LinkedList<>();
        listeners = new LinkedList<>();
        commandsNumber = 0;
    }

    public Board(Exercise exercise) {
        this();
        this.exercise = exercise;
    }

    public void addVector(Vector vector) {
        vectors.add(vector);
    }

    public ExerciseGrade getExerciseGrade() {
        return exercise.evaluate(vectors, commandsNumber);
    }

    public Turtle getTurtle() {
        return turtle;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
        clear();
    }

    public LinkedList<Vector> getVectors() {
        return vectors;
    }

    public int getCommandsNumber() {
        return commandsNumber;
    }

    public void executeCommands(List<Command> commands) {
        if (commands.isEmpty()) {
            clear();
        } else {
            for (Command c : commands) {
                c.execute(this);
                commandsNumber += c.getCommandsNumber();
                notifyListeners();
            }
        }
    }

    private void clear() {
        turtle = new Turtle();
        vectors = new LinkedList<>();
        commandsNumber = 0;
        notifyListeners();
    }

    private void notifyListeners() {
        listeners.forEach(l -> l.changed(this, this, this));
    }

    @Override
    public void addListener(ChangeListener<? super Board> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ChangeListener<? super Board> listener) {
        listeners.remove(listener);
    }

    @Override
    public Board getValue() {
        return this;
    }

    @Override
    public void addListener(InvalidationListener listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        throw new UnsupportedOperationException();
    }
}
