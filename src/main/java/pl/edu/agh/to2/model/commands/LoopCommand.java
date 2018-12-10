package pl.edu.agh.to2.model.commands;

import pl.edu.agh.to2.model.Board;
import java.util.List;

public class LoopCommand extends Command {
    private List<Command> commands;
    private int loops;

    public LoopCommand(List<Command> commands, int loops) {
        this.commands = commands;
        this.loops = loops;
    }

    @Override
    public void execute(Board board) {
        super.execute(board);
        for(int i = 0; i < loops; i++) {
            for(Command c : commands)
                c.execute(board);
        }
    }

    @Override
    public int getCommandsNumber() {
        return 1 +
                commands.stream()
                        .mapToInt(Command::getCommandsNumber)
                        .sum();
    }
}
