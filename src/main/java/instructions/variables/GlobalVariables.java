package instructions.variables;

import java.util.HashMap;

/**
 * Klasa przechowująca zmienne globalne programu.
 */

public class GlobalVariables {
    private final HashMap<String, Double> variables;

    public GlobalVariables() {
        variables = new HashMap<>();
    }

    public double setValue(String variableName, double value) {
        variables.put(variableName, value);
        return getValue(variableName);
    }

    public double getValue(String variableName) {
        if (!variables.containsKey(variableName)) {
            setValue(variableName, 0);
        }
        return variables.get(variableName);
    }

    public void clear() {
        variables.clear();
    }

}

