package pl.edu.agh.to2.presenter;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import pl.edu.agh.to2.model.ExerciseGrade;
import pl.edu.agh.to2.parsers.CommandParser;

import java.util.LinkedList;
import java.util.List;

public class CommandLinePresenter {
    @FXML
    private TextArea commandsInput;

    private SimpleObjectProperty<List> commands;
    private SimpleObjectProperty<ExerciseGrade> exerciseGrade;

    public CommandLinePresenter() {
        commands = new SimpleObjectProperty<List>();
        exerciseGrade = new SimpleObjectProperty<ExerciseGrade>();
    }

    public void clear() {
        onReset();
    }

    @FXML
    private void onReset() {
        commandsInput.clear();
        commands.setValue(new LinkedList<>());
    }

    @FXML
    private void onSubmit() {
        try {
            CommandParser parser = new CommandParser(commandsInput.getText());
            commands.setValue(parser.parseCommands());
        } catch (IllegalArgumentException e) {
            commandsInput.setText(e.getMessage());
        }
    }

    public SimpleObjectProperty<List> commands() {
        return commands;
    }
}
