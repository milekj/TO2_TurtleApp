package pl.edu.agh.to2.model.geometry;

import java.math.BigDecimal;
import java.util.Objects;
import static java.lang.Math.*;
import static pl.edu.agh.to2.model.geometry.Utilities.FULL_ANGLE_DEGREES;
import static pl.edu.agh.to2.model.geometry.Utilities.compareAsBigDecimals;
import static pl.edu.agh.to2.model.geometry.Utilities.newBigDecimal;

public class Vector {
    private Point start;
    private Point end;
    private double length;
    private int angleDegrees;

    public Vector(Point start, int angleDegrees, double length) {
        this.start = start;
        this.end = null;
        this.length = length;
        this.angleDegrees = angleDegrees % FULL_ANGLE_DEGREES;
        if (angleDegrees >= FULL_ANGLE_DEGREES / 2)
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
            double deltaX = sin(angleRadians) * length;
            double deltaY = cos(angleRadians) * length;
            double endX = start.getX() + deltaX;
            double endY = start.getY() + deltaY;
            end = new Point(endX, endY);
        }
        return end;
    }

    private Vector mergeToThisOnly(Vector v2) {
        if (angleDegrees != v2.angleDegrees)
            return null;
        double startDistancesSum = sumPointDistancesToVectorEnds(v2.start);
        if (compareAsBigDecimals(startDistancesSum, length) == 0) {
            double endDistancesSum = sumPointDistancesToVectorEnds(v2.getEndPoint());
            if (compareAsBigDecimals(endDistancesSum, length) == 0)
                return this;
            double addedLength = getEndPoint().getDistance(v2.getEndPoint());
            double totalNewLength = length + addedLength;
            return new Vector(start, angleDegrees, totalNewLength);
        }
        return null;
    }

    private double sumPointDistancesToVectorEnds(Point point) {
        double startDistance = start.getDistance(point);
        double endDistance = getEndPoint().getDistance(point);
        return startDistance + endDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Vector))
            return false;
        Vector vector = (Vector) o;
        return angleDegrees == vector.angleDegrees &&
                compareAsBigDecimals(length, vector.length) == 0 &&
                start.equals(vector.start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, angleDegrees, newBigDecimal(length));
    }

    @Override
    public String toString() {
        return "Vector[" + start +
                ", " + angleDegrees + " degrees" +
                ", " + length + "]";
    }
}
