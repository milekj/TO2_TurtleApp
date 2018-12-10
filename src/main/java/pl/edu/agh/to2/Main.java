package pl.edu.agh.to2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.ExercisesManager;
import pl.edu.agh.to2.presenter.MainPresenter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Turtle App");

        ExercisesManager manager;
        ObjectInputStream ois = null;
        
        try {
            FileInputStream fin = new FileInputStream("grades");
            ois = new ObjectInputStream(fin);
            manager = (ExercisesManager) ois.readObject();
            ois.close();
        } catch (Exception e) {
            //e.printStackTrace();
            manager = new ExercisesManager("ex");
        }

        Board board = new Board(manager.getCurrent());

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Main.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();

            MainPresenter mainPresenter = loader.getController();
            mainPresenter.setBoard(board);
            mainPresenter.setExerciseManager(manager);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
