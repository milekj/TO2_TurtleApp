package pl.edu.agh.to2.presenter;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.Exercise;
import pl.edu.agh.to2.model.ExerciseGrade;
import pl.edu.agh.to2.model.ExericesManager;

public class ExercisePresenter {
    private ExericesManager manager;
    private SimpleObjectProperty<Exercise> exercise;

    public ExercisePresenter() {
        exercise = new SimpleObjectProperty<Exercise>();
    }

    public void setExercisesManager(ExericesManager manager) {
        this.manager = manager;
        exercise.setValue(manager.getCurrent());
        updateButtons();
    }

    @FXML
    private Label result;

    @FXML
    private Button prev;

    @FXML
    private Button next;

    public void onBoardChange(ExerciseGrade exerciseGrade) {
        Color color;
        switch (exerciseGrade) {
            case UNSOLVED:
                color = Color.RED;
                break;
            case SOLVED:
                color = Color.YELLOW;
                break;
            case IDEAL:
                color = Color.GREEN;
                break;
            default:
                color = Color.WHITE;
        }

        result.setText(exerciseGrade.toString());
        result.setTextFill(color);
    }

    @FXML
    private void onPrev() {
        exercise.setValue(manager.moveToPrevious());
        updateButtons();
    }

    @FXML
    private void onNext() {
        exercise.setValue(manager.moveToNext());
        updateButtons();
    }

    private void updateButtons() {
        prev.setDisable(!manager.hasPrevious());
        next.setDisable(!manager.hasNext());
    }

    public SimpleObjectProperty<Exercise> exercise() {
        return exercise;
    }
}
