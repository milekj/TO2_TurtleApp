package pl.edu.agh.to2.model.commands;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.EMarkerState;
import pl.edu.agh.to2.model.Turtle;
import pl.edu.agh.to2.model.geometry.Point;
import pl.edu.agh.to2.model.geometry.Vector;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BackwardCommandTest {

    @Test
    void testExecute() {
        Board board = new Board();
        Turtle turtle = board.getTurtle();
        BackwardCommand command = new BackwardCommand(10);
        command.execute(board);
        Point expectedPosition = new Point(-10, 0);
        Vector newVector = new Vector(new Point(-10, 0), 90, 10);
        List<Vector> expectedVectors = Collections.singletonList(newVector);
        assertEquals(expectedPosition, turtle.getPosition());
        assertEquals(expectedVectors, board.getVectors());
        assertEquals(1, board.getCommandsNumber());
        assertEquals(90, turtle.getAngleDegrees());

        board = new Board();
        turtle = board.getTurtle();
        turtle.setMarkerState(EMarkerState.UP);
        command = new BackwardCommand(10);
        command.execute(board);
        expectedPosition = new Point(-10, 0);
        assertEquals(expectedPosition, turtle.getPosition());
        assertTrue(board.getVectors().isEmpty());
        assertEquals(1, board.getCommandsNumber());
        assertEquals(90, turtle.getAngleDegrees());
    }
}