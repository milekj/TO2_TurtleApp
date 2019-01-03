package pl.edu.agh.to2.model;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import pl.edu.agh.to2.commands.Command;
import pl.edu.agh.to2.commands.CommandRegistry;
import pl.edu.agh.to2.model.geometry.Vector;

import java.util.LinkedList;
import java.util.List;

public class Board implements ObservableValue<Board>{
    private Turtle turtle;
    private LinkedList<Vector> vectors;
    private Exercise exercise;
    private CommandRegistry commandRegistry;

    private List<ChangeListener<? super Board>> listeners;

    public Board() {
        turtle = new Turtle();
        vectors = new LinkedList<>();
        listeners = new LinkedList<>();
        commandRegistry = new CommandRegistry();
    }

    public Board(Exercise exercise) {
        this();
        this.exercise = exercise;
    }

    public void addVector(Vector vector) {
        vectors.add(vector);
    }

    public ExerciseGrade getExerciseGrade() {
        return exercise.evaluate(vectors, getCommandsNumber());
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
        return commandRegistry.getCommandsNumber();
    }

    public void executeCommands(List<Command> commands) {
        commandRegistry.storeAndExecute(commands);
        notifyListeners();
    }

    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }

    public void clear() {
        turtle = new Turtle();
        vectors = new LinkedList<>();
        commandRegistry = new CommandRegistry();
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
