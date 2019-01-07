package pl.edu.agh.to2.presenter;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import pl.edu.agh.to2.model.Exercise;
import pl.edu.agh.to2.model.ExerciseGrade;
import pl.edu.agh.to2.model.ExercisesManager;
import pl.edu.agh.to2.persistence.ExerciseMangerSerializer;

public class ExercisePresenter {
    private ExercisesManager manager;
    private SimpleObjectProperty<Exercise> exercise;

    @FXML
    private Label exerciseName;

    @FXML
    private Label exerciseDescription;

    @FXML
    private ListView<String> exercisesList;

    @FXML
    private Label result;

    @FXML
    private Button prev;

    @FXML
    private Button next;

    public ExercisePresenter() {
        exercise = new SimpleObjectProperty<Exercise>();
    }

    public void setExercisesManager(ExercisesManager manager) {
        this.manager = manager;

        ObservableList list = FXCollections.observableArrayList();
        for (int i = 0; i < manager.getExercisesCount(); i++) {
            list.add("Exercise " + (i + 1));
        }
        exercisesList.setItems(list);

        exercisesList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setExercise((int)newValue);
            }
        });

        setExercise(manager.getCurrent());
    }

    public void setExercise(Exercise exercise) {
        int index = manager.getCurrentIndex();
        this.exercise.setValue(exercise);
        exerciseName.setText("Exercise " + (index + 1));
        exerciseDescription.setText(exercise.getDescription());
        exercisesList.getSelectionModel().select(index);
        exercisesList.scrollTo(index);
        updateButtons();
    }

    public void setExercise(int index) {
        setExercise(manager.moveToIndex(index));
    }

    public void onBoardChange(ExerciseGrade exerciseGrade) {
        saveGrades();

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
        setExercise(manager.moveToPrevious());
    }

    @FXML
    private void onNext() {
        setExercise(manager.moveToNext());
    }

    private void updateButtons() {
        prev.setDisable(!manager.hasPrevious());
        next.setDisable(!manager.hasNext());
    }

    private void saveGrades() {
        ExerciseMangerSerializer.saveExcercises(manager);
    }

    public SimpleObjectProperty<Exercise> exercise() {
        return exercise;
    }
}
