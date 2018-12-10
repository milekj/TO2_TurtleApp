package pl.edu.agh.to2.model.geometry;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import static pl.edu.agh.to2.model.geometry.Utilities.*;

public class Point implements Serializable {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(Point other) {
        double deltaX = x - other.x;
        double deltaY = y - other.y;
        double deltaXSquare = deltaX * deltaX;
        double deltaYSquare = deltaY * deltaY;
        return Math.sqrt(deltaXSquare + deltaYSquare);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Point))
            return false;
        Point point = (Point) o;
        return compareAsBigDecimals(x, point.x) == 0  &&
                compareAsBigDecimals(y, point.y) == 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(newBigDecimal(x), newBigDecimal(y));
    }

    @Override
    public String toString() {
        return "Point(" + x +
                ", " + y +
                ')';
    }
}
