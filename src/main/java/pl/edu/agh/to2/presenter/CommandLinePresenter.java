package pl.edu.agh.to2.presenter;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.ExerciseGrade;
import pl.edu.agh.to2.model.commands.Command;
import pl.edu.agh.to2.parser.CommandParser;

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
        commands.setValue(new CommandParser((commandsInput.getText())).parseCommands());

        //exerciseGrade.set(board.getExerciseGrade());

        //only for demo, will be changed later
        //System.out.println(board.getExercise());
        //System.out.println("Is exercise passed?: " + board.getExerciseGrade());
    }

    public SimpleObjectProperty<List> commands() {
        return commands;
    }

    public SimpleObjectProperty<ExerciseGrade> exerciseGrade() {
        return exerciseGrade;
    }
}
