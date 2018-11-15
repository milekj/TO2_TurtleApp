package pl.edu.agh.to2.model;

import java.math.BigDecimal;

public class Utilities {
    private static int decimalPrecision = 4;

    public static BigDecimal newBigDecimal(double val) {
        return new BigDecimal(val).setScale(decimalPrecision, BigDecimal.ROUND_HALF_UP);
    }
}
