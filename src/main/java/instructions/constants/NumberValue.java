package instructions.constants;

import com.squareup.moshi.Json;
import instructions.Instruction;
import instructions.variables.GlobalVariables;
import robson.JavaFile;

public class NumberValue extends Instruction {
    @Json(name = "wartosc")
    private final double value;

    public NumberValue(double value) {
        this.value = value;
    }

    public double execute(GlobalVariables globalVariables) {
        return value;
    }

    public void toJava(JavaFile javaFile, int index) {
        javaFile.setFunction(index,
                "return " + value + ";\n");
    }
}
