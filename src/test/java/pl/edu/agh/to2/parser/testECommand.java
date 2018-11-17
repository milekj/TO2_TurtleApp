package pl.edu.agh.to2.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by michal on 17.11.18.
 */
public class testECommand {
    @Test
    void testGetCommand() {
        assertEquals(ECommands.NP, ECommands.getCommand("np"));
        assertEquals(ECommands.WS, ECommands.getCommand("ws"));
        assertEquals(ECommands.LW, ECommands.getCommand("lw"));
        assertEquals(ECommands.PW, ECommands.getCommand("pw"));
        assertEquals(ECommands.OPU, ECommands.getCommand("opu"));
        assertEquals(ECommands.POD, ECommands.getCommand("pod"));
    }
    @Test
    void testGetType() {
        assertEquals(ECommands.getType(ECommands.OPU), ECommandType.UNARY);
        assertEquals(ECommands.getType(ECommands.POD), ECommandType.UNARY);
        assertEquals(ECommands.getType(ECommands.NP), ECommandType.BINARY);
        assertEquals(ECommands.getType(ECommands.WS), ECommandType.BINARY);
        assertEquals(ECommands.getType(ECommands.LW), ECommandType.BINARY);
        assertEquals(ECommands.getType(ECommands.PW), ECommandType.BINARY);
    }
}
