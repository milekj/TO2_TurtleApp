package pl.edu.agh.to2.model;

import pl.edu.agh.to2.parser.Command;
import pl.edu.agh.to2.turtle.Turtle;

import java.util.List;

public class Board {
    private Turtle turtle;
    private VectorsSet vectors;

    public Board() {
        turtle = new Turtle();
        vectors = new VectorsSet();
    }

    public Turtle getTurtle() {
        return turtle;
    }

    public VectorsSet getVectors() {
        return vectors;
    }

    public void update(List<Command> commands) {
        // TODO
    }
}
