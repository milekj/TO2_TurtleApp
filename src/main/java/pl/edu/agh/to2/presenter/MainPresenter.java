package pl.edu.agh.to2.presenter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.ExerciseGrade;

public class MainPresenter {
    @FXML
    private BoardPresenter boardController;

    @FXML
    private ExercisePresenter exerciseController;

    @FXML
    private CommandLinePresenter commandLineController;

    @FXML
    public void initialize() {
        commandLineController.exerciseGrade().addListener(new ChangeListener<ExerciseGrade>() {
            @Override
            public void changed(ObservableValue<? extends ExerciseGrade> observable, ExerciseGrade oldValue, ExerciseGrade newValue) {
                exerciseController.onExercise(newValue);
            }
        });
    }

    public void setBoard(Board board) {
        boardController.setBoard(board);
        commandLineController.setBoard(board);
    }

}
