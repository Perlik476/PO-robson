package instructions.converter;

public class Converter {
    public static boolean doubleToBoolean(double value) {
        return value != 0;
    }

    public static double booleanToDouble(boolean value) {
        return value ? 1 : 0;
    }
}
