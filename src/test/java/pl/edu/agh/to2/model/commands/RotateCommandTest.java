package pl.edu.agh.to2.model.commands;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.Turtle;

import static org.junit.jupiter.api.Assertions.*;

class RotateCommandTest {
    @Test
    void testExecute() {
        Board board = new Board();
        Turtle turtle = board.getTurtle();
        RotateCommand command = new RotateCommand(90);
        command.execute(board);
        assertEquals(180, turtle.getAngleDegrees());
        assertEquals(1, command.getCommandsNumber());

        command = new RotateCommand(-90);
        command.execute(board);
        assertEquals(90, turtle.getAngleDegrees());
        assertEquals(1, command.getCommandsNumber());
    }

}