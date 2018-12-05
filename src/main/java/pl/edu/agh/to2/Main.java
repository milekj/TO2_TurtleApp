package pl.edu.agh.to2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.ExericesManager;
import pl.edu.agh.to2.presenter.MainPresenter;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Turtle App");

        ExericesManager manager = new ExericesManager("ex");
        manager.moveToNext();
        manager.moveToNext();
        manager.moveToNext();
        Board board = new Board(manager.getCurrent());

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Main.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();

            MainPresenter mainPresenter = loader.getController();
            mainPresenter.setBoard(board);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
