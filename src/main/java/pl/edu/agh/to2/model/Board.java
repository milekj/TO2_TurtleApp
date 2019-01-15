package pl.edu.agh.to2.model;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import pl.edu.agh.to2.commands.Command;
import pl.edu.agh.to2.commands.CommandRegistry;
import pl.edu.agh.to2.model.geometry.Vector;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Board implements ObservableValue<Board>{
    private Turtle turtle;
    private List<Vector> vectors;
    private Exercise exercise;
    private CommandRegistry commandRegistry;
    private Map<String, List<Command>> procedures;
    private List<ChangeListener<? super Board>> listeners;

    public Board() {
        turtle = new Turtle();
        vectors = new LinkedList<>();
        procedures = new HashMap<>();
        commandRegistry = new CommandRegistry();
        listeners = new LinkedList<>();
    }

    public Board(Exercise exercise) {
        this();
        this.exercise = exercise;
    }

    public void addVector(Vector vector) {
        vectors.add(vector);
    }

    public void executeCommands(List<Command> commands) {
        commandRegistry.storeAndExecute(commands);
        notifyListeners();
    }

    public void clear() {
        turtle = new Turtle();
        vectors = new LinkedList<>();
        commandRegistry = new CommandRegistry();
        notifyListeners();
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
        procedures.clear();
        clear();
    }

    public List<Vector> getVectors() {
        return vectors;
    }

    public Map<String, List<Command>> getProcedures() {
        return procedures;
    }

    public int getCommandsNumber() {
        return commandRegistry.getCommandsNumber();
    }

    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
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
