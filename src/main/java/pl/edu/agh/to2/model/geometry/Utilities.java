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

    public static int compareAsBigDecimals(double a, double b) {
        BigDecimal aApprox = newBigDecimal(a);
        BigDecimal bApprox = newBigDecimal(b);
        return aApprox.compareTo(bApprox);
    }
}
