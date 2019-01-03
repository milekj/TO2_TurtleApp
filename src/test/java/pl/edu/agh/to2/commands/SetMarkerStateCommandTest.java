package pl.edu.agh.to2.commands;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.EMarkerState;
import pl.edu.agh.to2.model.Turtle;

import static org.junit.jupiter.api.Assertions.*;

class SetMarkerStateCommandTest {
    @Test
    void testExecute() {
        Board board = new Board();
        Turtle turtle = board.getTurtle();
        SetMarkerStateCommand command = new SetMarkerStateCommand(board, EMarkerState.UP);
        command.execute();
        assertEquals(EMarkerState.UP, turtle.getMarkerState());
        assertEquals(1, command.getCommandsNumber());

        command = new SetMarkerStateCommand(board, EMarkerState.DOWN);
        command.execute();
        assertEquals(EMarkerState.DOWN, turtle.getMarkerState());
        assertEquals(1, command.getCommandsNumber());
    }
}