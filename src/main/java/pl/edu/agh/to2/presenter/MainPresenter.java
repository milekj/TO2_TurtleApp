package pl.edu.agh.to2.presenter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.Exercise;
import pl.edu.agh.to2.model.ExercisesManager;

import java.util.List;

public class MainPresenter {
    @FXML
    private BoardPresenter boardController;

    @FXML
    private ExercisePresenter exerciseController;

    @FXML
    private CommandLinePresenter commandLineController;

    @FXML
    public void initialize() {
        exerciseController.exercise().addListener(new ChangeListener<Exercise>() {
            @Override
            public void changed(ObservableValue<? extends Exercise> observable, Exercise oldValue, Exercise newValue) {
                boardController.setExercise(newValue);
                commandLineController.clear();
            }
        });
    }

    public void setBoard(Board board) {
        boardController.setBoard(board);
        commandLineController.setBoard(board);
        board.addListener(new ChangeListener<Board>() {
            @Override
            public void changed(ObservableValue<? extends Board> observable, Board oldValue, Board newValue) {
                exerciseController.onBoardChange(newValue.getExerciseGrade());
            }
        });
    }

    public void setExerciseManager(ExercisesManager manager) {
        exerciseController.setExercisesManager(manager);
    }
}
