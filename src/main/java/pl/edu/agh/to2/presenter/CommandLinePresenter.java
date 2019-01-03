package pl.edu.agh.to2.presenter;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import pl.edu.agh.to2.commands.Command;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.ExerciseGrade;
import pl.edu.agh.to2.parsers.CommandParser;

import java.util.LinkedList;
import java.util.List;

public class CommandLinePresenter {
    @FXML
    private TextArea commandsInput;

    private Board board;
    private SimpleObjectProperty<ExerciseGrade> exerciseGrade;

    public CommandLinePresenter() {
        exerciseGrade = new SimpleObjectProperty<ExerciseGrade>();
    }

    public void clear() {
        onReset();
    }

    @FXML
    private void onReset() {
        board.clear();
    }

    @FXML
    private void onSubmit() {
        try {
            board.clear();
            CommandParser parser = new CommandParser(commandsInput.getText(), board);
            List<Command> commands = parser.parseCommands();
            board.executeCommands(commands);
        } catch (IllegalArgumentException e) {
            commandsInput.setText(e.getMessage());
        }
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
