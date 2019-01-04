package pl.edu.agh.to2.commands;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.EMarkerState;
import pl.edu.agh.to2.model.Turtle;
import pl.edu.agh.to2.model.geometry.Point;
import pl.edu.agh.to2.model.geometry.Vector;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForwardCommandTest {

    @Test
    void testExecute() {
        Board board = new Board();
        Turtle turtle = board.getTurtle();
        ForwardCommand command = new ForwardCommand(board, 10);
        board.executeCommands(Collections.singletonList(command));
        Point expectedPosition = new Point(10, 0);
        Vector newVector = new Vector(new Point(0, 0), 90, 10);
        List<Vector> expectedVectors = Collections.singletonList(newVector);
        assertEquals(expectedPosition, turtle.getPosition());
        assertEquals(expectedVectors, board.getVectors());
        assertEquals(1, command.getCommandsNumber());

        board = new Board();
        turtle = board.getTurtle();
        turtle.setMarkerState(EMarkerState.UP);
        command = new ForwardCommand(board, 10);
        board.executeCommands(Collections.singletonList(command));
        expectedPosition = new Point(10, 0);
        assertEquals(expectedPosition, turtle.getPosition());
        assertTrue(board.getVectors().isEmpty());
        assertEquals(1, command.getCommandsNumber());
    }
}