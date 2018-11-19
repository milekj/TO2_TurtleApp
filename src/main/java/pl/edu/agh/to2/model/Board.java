package pl.edu.agh.to2.model;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import pl.edu.agh.to2.parser.Command;

import java.util.LinkedList;
import java.util.List;

public class Board implements ObservableValue<Board>{
    private Turtle turtle;
    private VectorsSet vectors;
    private Exercise exercise;

    private List<ChangeListener<? super Board>> listeners;

    public Board(Exercise exercise) {
        turtle = new Turtle();
        vectors = new VectorsSet();
        this.exercise = exercise;
        listeners = new LinkedList<>();
    }

    public Turtle getTurtle() {
        return turtle;
    }

    public VectorsSet getVectors() {
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
                    break;
                case LW:
                    turtle.addToAngleDegrees(-c.getNumber());
                    break;
                case PW:
                    turtle.addToAngleDegrees(c.getNumber());
                    break;
            }
            notifyListeners();
        }
    }

    public void clear() {
        turtle = new Turtle();
        vectors = new VectorsSet();
        notifyListeners();
    }

    public boolean isExercisePassed() {
        return exercise.vectorsPass(vectors.getVectorsSet());
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
    }

    @Override
    public void removeListener(InvalidationListener listener) {
    }
}
