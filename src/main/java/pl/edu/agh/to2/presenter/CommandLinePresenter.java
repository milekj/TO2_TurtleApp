package pl.edu.agh.to2.presenter;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.stage.Popup;
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
            showErrorPopup(e.getMessage());
        }
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    private void showErrorPopup(String message) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Syntax error");
        a.setGraphic(null);
        a.setHeaderText(null);
        a.setContentText(message);
        a.show();
    }
}
