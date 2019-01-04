package pl.edu.agh.to2.commands;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.Turtle;
import pl.edu.agh.to2.model.geometry.Point;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExecuteProcedureCommandTest {
    @Test
    void testExecute() {
        Board board = new Board();
        String name = "testProcedure";
        List<Command> procedureCommands = Arrays.asList(new ForwardCommand(board, 100), new RotateCommand(board, 90));
        board.executeCommands(Collections.singletonList(new CreateProcedureCommand(board, name, procedureCommands)));
        Command command = new ExecuteProcedureCommand(board, name);
        board.executeCommands(Collections.singletonList(command));
        Turtle turtle = board.getTurtle();
        assertEquals(new Point(100, 0), turtle.getPosition());
        assertEquals(180, turtle.getAngleDegrees());
        assertEquals(1, board.getCommandsNumber());
    }
}