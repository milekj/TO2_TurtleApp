package pl.edu.agh.to2.presenter;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.ExerciseGrade;
import pl.edu.agh.to2.model.commands.Command;
import pl.edu.agh.to2.parser.CommandParser;

import java.util.List;

public class CommandLinePresenter {
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    @FXML
    private TextArea commandsInput;

    private SimpleObjectProperty<ExerciseGrade> exerciseGrade;

    public CommandLinePresenter() {
        exerciseGrade = new SimpleObjectProperty<ExerciseGrade>();
    }

    @FXML
    private void onReset() {
        commandsInput.clear();
        board.clear();
    }

    @FXML
    private void onSubmit() {
        List<Command> commands = new CommandParser((commandsInput.getText())).parseCommands();
        board.executeCommands(commands);

        exerciseGrade.set(board.getExerciseGrade());

        //only for demo, will be changed later
        System.out.println(board.getExercise());
        System.out.println("Is exercise passed?: " + board.getExerciseGrade());
    }

    public SimpleObjectProperty<ExerciseGrade> exerciseGrade() {
        return exerciseGrade;
    }
}
