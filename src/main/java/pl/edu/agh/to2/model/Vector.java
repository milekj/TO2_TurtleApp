package pl.edu.agh.to2.model;

import java.math.BigDecimal;
import java.util.Objects;
import static java.lang.Math.*;

public class Vector {
    private Point start;
    private Point end;
    private BigDecimal length;
    private int angleDegrees;

    public Vector(Point start, int angleDegrees, double length) {
        this.start = start;
        this.end = null;
        this.length = Utilities.newBigDecimal(length);
        this.angleDegrees = angleDegrees;
        if (angleDegrees >= 180)
            normalizeAngle();
    }

    public Vector mergeIfOverlapping(Vector other) {
        Vector merged = mergeToThisOnly(other);
        return (merged != null) ? merged : other.mergeToThisOnly(this);
    }

    private void normalizeAngle() {
        start = getEndPoint();
        end = null;
        angleDegrees -= 180;
    }

    public Point getStartPoint() {
        return start;
    }

    public Point getEndPoint() {
        if (end == null) {
            double angleRadians = toRadians(angleDegrees);
            BigDecimal deltaX = Utilities.newBigDecimal(sin(angleRadians) * length.doubleValue());
            BigDecimal deltaY = Utilities.newBigDecimal(cos(angleRadians) * length.doubleValue());
            BigDecimal startX = start.getX();
            BigDecimal startY = start.getY();
            BigDecimal endX = startX.add(deltaX);
            BigDecimal endY = startY.add(deltaY);
            end = new Point(endX, endY);
        }
        return end;
    }

    private Vector mergeToThisOnly(Vector v2) {
        if (angleDegrees != v2.angleDegrees)
            return null;
        BigDecimal startDistancesSum = sumPointDistancesToVectorEnds(v2.start);
        if (startDistancesSum.compareTo(length) == 0) {
            BigDecimal endDistancesSum = sumPointDistancesToVectorEnds(v2.getEndPoint());
            if (endDistancesSum.compareTo(length) == 0)
                return this;
            BigDecimal addedLength = getEndPoint().getDistance(v2.getEndPoint());
            BigDecimal totalNewLength = length.add(addedLength);
            return new Vector(start, angleDegrees, totalNewLength.doubleValue());
        }
        return null;
    }

    private BigDecimal sumPointDistancesToVectorEnds(Point point) {
        BigDecimal startDistance = start.getDistance(point);
        BigDecimal endDistance = getEndPoint().getDistance(point);
        return startDistance.add(endDistance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Vector))
            return false;
        Vector vector = (Vector) o;
        return angleDegrees == vector.angleDegrees &&
                length.compareTo(vector.length) == 0 &&
                start.equals(vector.start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, angleDegrees, length);
    }

    @Override
    public String toString() {
        return "Vector[" + start +
                ", " + angleDegrees + " degrees" +
                ", " + length + "]";
    }
}
