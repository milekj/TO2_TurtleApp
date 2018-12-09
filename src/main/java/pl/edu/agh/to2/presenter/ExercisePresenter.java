package pl.edu.agh.to2.presenter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import pl.edu.agh.to2.model.ExerciseGrade;

public class ExercisePresenter {
    @FXML
    private Label result;

    public void onExercise(ExerciseGrade exerciseGrade) {
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
}
