package pl.edu.agh.to2.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Point {
    private final BigDecimal x;
    private final BigDecimal y;

    public Point(double x, double y) {
        this.x = Utilities.newBigDecimal(x);
        this.y = Utilities.newBigDecimal(y);
    }

    public Point(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public BigDecimal getDistance(Point other) {
        BigDecimal deltaX = x.subtract(other.x);
        BigDecimal deltaY = y.subtract(other.y);
        double deltaXSquare = deltaX.multiply(deltaX).doubleValue();
        double deltaYSquare = deltaY.multiply(deltaY).doubleValue();
        return Utilities.newBigDecimal(Math.sqrt(deltaXSquare + deltaYSquare));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Point))
            return false;
        Point point = (Point) o;
        return x.compareTo(point.x) == 0  &&
                y.compareTo(point.y) == 0;
    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point(" + x +
                ", " + y +
                ')';
    }
}
