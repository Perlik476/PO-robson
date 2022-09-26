package instructions;

import com.squareup.moshi.Json;
import instructions.converter.Converter;
import instructions.variables.GlobalVariables;
import robson.JavaFile;

public class While extends Instruction {
    @Json(name = "warunek")
    private final Instruction condition;
    @Json(name = "blok")
    private final Instruction block;

    public While(Instruction condition, Block block) {
        this.condition = condition;
        this.block = block;
    }

    public double execute(GlobalVariables globalVariables) {
        while (Converter.doubleToBoolean(condition.execute(globalVariables))) {
            block.execute(globalVariables);
        }
        return 0;
    }

    public void toJava(JavaFile javaFile, int index) {
        int newIndex = javaFile.getLastFunctionIndex() + 1;
        javaFile.addFunction(2);
        javaFile.setFunction(index,
                "while (" + javaFile.executeFunction(newIndex) + " != 0) { \n" +
                        "    " + javaFile.executeFunction(newIndex + 1) + "; \n" +
                        "}\n" +
                        "return 0;\n");
        condition.toJava(javaFile, newIndex);
        block.toJava(javaFile, newIndex + 1);
    }
}
