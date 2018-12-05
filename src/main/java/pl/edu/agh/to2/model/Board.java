package pl.edu.agh.to2.model;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import pl.edu.agh.to2.model.geometry.Point;
import pl.edu.agh.to2.model.geometry.Vector;
import pl.edu.agh.to2.parser.Command;

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

    public Turtle getTurtle() {
        return turtle;
    }

    public LinkedList<Vector> getVectors() {
        return vectors;
    }

    public void update(List<Command> commands) {
        for (Command c : commands) {
            switch (c.getCommand()) {
                case NP:
                    Vector vector = new Vector(turtle.getPosition(), turtle.getAngleDegrees(), c.getNumber());
                    vectors.add(vector);
                    Point newPosition;
                    if (turtle.getPosition().equals(vector.getStartPoint()))
                        newPosition = vector.getEndPoint();
                    else
                        newPosition = vector.getStartPoint();
                    turtle.setPosition(newPosition);
                    commandsNumber++;
                    break;
                case LW:
                    turtle.addToAngleDegrees(-c.getNumber());
                    commandsNumber++;
                    break;
                case PW:
                    turtle.addToAngleDegrees(c.getNumber());
                    commandsNumber++;
                    break;
            }
            notifyListeners();
        }
    }

    public void clear() {
        turtle = new Turtle();
        vectors = new LinkedList<>();
        commandsNumber = 0;
        notifyListeners();
    }

    public ExerciseGrade getExerciseGrade() {
        return exercise.evaluate(vectors, commandsNumber);
    }

    public Exercise getExercise() {
        return exercise;
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
