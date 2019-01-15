package pl.edu.agh.to2.commands;

import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.EMarkerState;
import pl.edu.agh.to2.model.Turtle;

public class SetMarkerStateCommand extends Command {
    private EMarkerState stateToSet;

    public SetMarkerStateCommand(Board board, EMarkerState stateToSet) {
        super(board);
        this.stateToSet = stateToSet;
    }

    @Override
    public void execute() {
        Turtle turtle = board.getTurtle();
        turtle.setMarkerState(stateToSet);
    }
}
