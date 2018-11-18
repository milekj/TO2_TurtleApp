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
import pl.edu.agh.to2.model.Point;
import pl.edu.agh.to2.model.Vector;
import pl.edu.agh.to2.model.VectorsSet;
import pl.edu.agh.to2.turtle.Turtle;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class BoardPresenter implements Initializable {
    @FXML
    private Canvas canvas;

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


        //--- Only for test purposes
        Turtle turtle = new Turtle();
        turtle.setAngleDegrees(0);

        VectorsSet vectors = new VectorsSet();
        vectors.add(new Vector(new Point(0, 0), 0, 50));
        vectors.add(new Vector(new Point(0, 50), 90, 50));
        vectors.add(new Vector(new Point(50, 50), 180, 50));
        vectors.add(new Vector(new Point(50, 0), 270, 50));

        render(turtle, vectors.getVectorsSet());
        //---
    }

    public void render(Turtle turtle, Set<Vector> vectors) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Vector vector : vectors) {
            Point start = vector.getStartPoint();
            Point end = vector.getEndPoint();
            gc.strokeLine(translateX(start.getX()), translateY(start.getY()), translateX(end.getX()), translateY(end.getY()));
        }

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

        gc.drawImage(rotatedImage, turtleX, turtleY);
    }

    private double translateX(BigDecimal value) {
        return value.doubleValue() + canvas.getWidth() / 2;
    }

    private double translateY(BigDecimal value) {
        return -1 * value.doubleValue() + canvas.getHeight() / 2;
    }
}
