package pl.edu.agh.to2.commands;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.EMarkerState;
import pl.edu.agh.to2.model.Turtle;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SetMarkerStateCommandTest {
    @Test
    void testExecute() {
        Board board = new Board();
        Turtle turtle = board.getTurtle();
        SetMarkerStateCommand command = new SetMarkerStateCommand(board, EMarkerState.UP);
        board.executeCommands(Collections.singletonList(command));
        assertEquals(EMarkerState.UP, turtle.getMarkerState());
        assertEquals(1, command.getCommandsNumber());

        command = new SetMarkerStateCommand(board, EMarkerState.DOWN);
        board.executeCommands(Collections.singletonList(command));
        assertEquals(EMarkerState.DOWN, turtle.getMarkerState());
        assertEquals(1, command.getCommandsNumber());
    }
}