package pl.edu.agh.to2.commands;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.Turtle;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class RotateCommandTest {
    @Test
    void testExecute() {
        Board board = new Board();
        Turtle turtle = board.getTurtle();
        RotateCommand command = new RotateCommand(board, 90);
        board.executeCommands(Collections.singletonList(command));
        assertEquals(180, turtle.getAngleDegrees());
        assertEquals(1, command.getCommandsNumber());

        command = new RotateCommand(board, -90);
        board.executeCommands(Collections.singletonList(command));
        assertEquals(90, turtle.getAngleDegrees());
        assertEquals(1, command.getCommandsNumber());
    }

}