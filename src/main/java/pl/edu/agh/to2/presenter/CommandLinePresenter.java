package pl.edu.agh.to2.presenter;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.parser.Command;
import pl.edu.agh.to2.parser.CommandParser;

import java.util.List;

public class CommandLinePresenter {
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    @FXML
    private TextArea commandsInput;

    @FXML
    private void onReset() {
        commandsInput.clear();
        board.clear();
    }

    @FXML
    private void onSubmit() {
        List<Command> commands = new CommandParser(commandsInput.getText()).getCommands();
        board.update(commands);

        //only for demo, will be changed later
        System.out.println(board.getExercise());
        System.out.println(board.getVectors().getVectorsSet());
        System.out.println("Is exercise passed?: " + board.isExercisePassed());
    }
}
