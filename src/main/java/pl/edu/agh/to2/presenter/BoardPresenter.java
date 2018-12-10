package pl.edu.agh.to2.presenter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.EMarkerState;
import pl.edu.agh.to2.model.geometry.Point;
import pl.edu.agh.to2.model.geometry.Vector;
import pl.edu.agh.to2.model.Turtle;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BoardPresenter implements Initializable {
    private static double turtleLinesAlpha = 1.0;
    private static double exerciseLinesAlpha = 0.25;
    private static double turtleUpAlpha = 0.5;

    @FXML
    private Canvas canvas;

    private Board board;

    private GraphicsContext gc;
    private Image turtleImg;

    public BoardPresenter() {
        canvas = new Canvas();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        turtleImg = new Image(getClass().getResourceAsStream("../resources/turtle.png"));
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
    }

    public void setBoard(Board board) {
        this.board = board;
        board.addListener((boardValue, oldBoard, newBoard) -> render());
        render();
    }

    private void render() {
        Turtle turtle = board.getTurtle();
        List<Vector> vectors = board.getVectors();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setGlobalAlpha(exerciseLinesAlpha);
        renderVectors(board.getExercise().getVectors());

        gc.setGlobalAlpha(turtleLinesAlpha);
        renderVectors(vectors);


        double turtleX = translateX(turtle.getPosition().getX()) - 12;
        double turtleY = translateY(turtle.getPosition().getY()) - 14;

        ImageView iv = new ImageView(turtleImg);

        Rotate r = new Rotate();
        r.setPivotX(turtleX);
        r.setPivotY(turtleY);
        iv.getTransforms().add(r);
        r.setAngle(turtle.getAngleDegrees());

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);

        if(turtle.getMarkerState() == EMarkerState.UP)
            gc.setGlobalAlpha(turtleUpAlpha);
        else
            gc.setGlobalAlpha(turtleLinesAlpha);
        gc.drawImage(rotatedImage, turtleX, turtleY);
    }

    private void renderVectors(Iterable<Vector> vectors) {
        for (Vector vector : vectors) {
            Point start = vector.getStartPoint();
            Point end = vector.getEndPoint();
            gc.strokeLine(translateX(start.getX()), translateY(start.getY()), translateX(end.getX()), translateY(end.getY()));
        }
    }

    private double translateX(double value) {
        return value + canvas.getWidth() / 2;
    }

    private double translateY(double value) {
        return -1 * value + canvas.getHeight() / 2;
    }
}
