package pl.edu.agh.to2.commands;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.EMarkerState;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CreateProcedureCommandTest {
    @Test
    void testExecute() {
        Board board = new Board();
        List<Command> procedureCommands = Arrays.asList(new ForwardCommand(board, 100),
                                                        new RotateCommand(board, 90));
        String name = "testProcedure";
        CreateProcedureCommand command = new CreateProcedureCommand(board, name, procedureCommands);
        board.executeCommands(Collections.singletonList(command));
        Map<String, List<Command>> boardProcedures = board.getProcedures();
        assertEquals(procedureCommands, boardProcedures.get(name));
        assertEquals(0, board.getCommandsNumber());

        procedureCommands = Arrays.asList(new BackwardCommand(board, 20), new SetMarkerStateCommand(board, EMarkerState.UP));
        command = new CreateProcedureCommand(board, name, procedureCommands);
        board.executeCommands(Collections.singletonList(command));
        assertEquals(procedureCommands, boardProcedures.get(name));
        assertEquals(0, board.getCommandsNumber());
    }
}