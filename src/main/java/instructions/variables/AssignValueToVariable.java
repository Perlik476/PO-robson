package instructions.variables;

import com.squareup.moshi.Json;
import instructions.Instruction;
import robson.JavaFile;

public class AssignValueToVariable extends Instruction {
    @Json(name = "nazwa")
    private final String variableName;
    @Json(name = "wartosc")
    private final Instruction instruction;

    public AssignValueToVariable(String variableName, Instruction instruction) {
        this.variableName = variableName;
        this.instruction = instruction;
    }

    public double execute(GlobalVariables globalVariables) {
        return globalVariables.setValue(variableName, instruction.execute(globalVariables));
    }

    public void toJava(JavaFile javaFile, int index) {
        javaFile.addVariable(variableName);
        int newIndex = javaFile.getLastFunctionIndex() + 1;
        javaFile.addFunction(1);
        javaFile.setFunction(index,
                variableName + " = " + javaFile.executeFunction(newIndex) + ";\n" +
                        "return " + variableName +";\n"
                );
        instruction.toJava(javaFile, newIndex);
    }
}
