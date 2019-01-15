package pl.edu.agh.to2.commands;

import pl.edu.agh.to2.model.Board;

import java.util.List;

public class ExecuteProcedureCommand extends Command{
    private String name;

    public ExecuteProcedureCommand(Board board, String name) {
        super(board);
        this.name = name;
    }

    @Override
    public void execute() {
        List<Command> procedureCommands = board.getProcedures().get(name);
        for(Command c : procedureCommands)
            c.execute();
    }
}
