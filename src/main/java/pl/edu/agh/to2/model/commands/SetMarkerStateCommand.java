package pl.edu.agh.to2.model.commands;

import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.EMarkerState;

public class SetMarkerStateCommand extends Command {
    private EMarkerState stateToSet;

    public SetMarkerStateCommand(EMarkerState stateToSet) {
        this.stateToSet = stateToSet;
    }

    @Override
    public void execute(Board board) {
        super.execute(board);
        turtle.setMarkerState(stateToSet);
    }
}
