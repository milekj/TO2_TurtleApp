package pl.edu.agh.to2.commands;

import pl.edu.agh.to2.model.Board;
import java.util.List;
import java.util.Map;

public class CreateProcedureCommand extends Command {
    private String name;
    private List<Command> commands;

    public CreateProcedureCommand(Board board, String name, List<Command> commands) {
        super(board);
        this.name = name;
        this.commands = commands;
    }

    @Override
    public void execute() {
        Map<String, List<Command>> procedures = board.getProcedures();
        procedures.put(name, commands);
    }

    @Override
    public int getCommandsNumber() {
        return 0;
    }
}
