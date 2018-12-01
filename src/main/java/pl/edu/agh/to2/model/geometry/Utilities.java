package pl.edu.agh.to2.model.geometry;

import pl.edu.agh.to2.model.Exercise;

import java.math.BigDecimal;
import java.util.Arrays;

public class Utilities {
    public static int FULL_ANGLE_DEGREES = 360;
    private static int decimalPrecision = 4;

    public static BigDecimal newBigDecimal(double val) {
        return new BigDecimal(val).setScale(decimalPrecision, BigDecimal.ROUND_HALF_UP);
    }

    public static Exercise getExampleExercise() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(100, -100);
        Vector v1 = new Vector(p1, 90, 100);
        Vector v2 = new Vector(p1, 180, 100);
        Vector v4 = new Vector(p2, 0, 100);
        Vector v5 = new Vector(p2, 270, 100);
        return new Exercise(Arrays.asList(v1, v2, v4, v5));
    }
}
