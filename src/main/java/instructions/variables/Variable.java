package instructions.variables;

import com.squareup.moshi.Json;
import instructions.Instruction;
import robson.JavaFile;

public class Variable extends Instruction {
    @Json(name = "nazwa")
    private final String variableName;

    public Variable(String variableName) {
        this.variableName = variableName;
    }

    public double execute(GlobalVariables globalVariables) {
        return globalVariables.getValue(variableName);
    }

    public void toJava(JavaFile javaFile, int index) {
        javaFile.addVariable(variableName);
        javaFile.setFunction(index,
                "return " + variableName + ";\n");
    }
}
