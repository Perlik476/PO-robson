package instructions;

import com.squareup.moshi.Json;
import instructions.converter.Converter;
import instructions.variables.GlobalVariables;
import robson.JavaFile;

public class If extends Instruction {
    @Json(name = "warunek")
    private final Instruction condition;
    @Json(name = "blok_prawda")
    private final Instruction blockWhenTrue;
    @Json(name = "blok_falsz")
    private final Instruction blockWhenFalse;

    public If(Instruction condition, Block blockWhenTrue, Block blockWhenFalse) {
        this.condition = condition;
        this.blockWhenTrue = blockWhenTrue;
        this.blockWhenFalse = blockWhenFalse;
    }

    public If(Instruction condition, Block blockWhenTrue) {
        this.condition = condition;
        this.blockWhenTrue = blockWhenTrue;
        blockWhenFalse = null;
    }

    public double execute(GlobalVariables globalVariables) {
        return Converter.doubleToBoolean(condition.execute(globalVariables)) ?
                blockWhenTrue.execute(globalVariables) : (
                        blockWhenFalse != null ? blockWhenFalse.execute(globalVariables) : 0
        );
    }

    public void toJava(JavaFile javaFile, int index) {
        int newIndex = javaFile.getLastFunctionIndex() + 1;
        javaFile.addFunction(blockWhenFalse == null ? 2 : 3);
        javaFile.setFunction(index,
                "if (" + javaFile.executeFunction(newIndex) +" != 0) { \n" +
                        "    return " + javaFile.executeFunction(newIndex + 1) + "; \n" +
                        "}\n" + (blockWhenFalse == null ? "return 0;" : "" +
                        "else {\n" +
                        "    return " + javaFile.executeFunction(newIndex + 2) + ";\n" +
                        "}\n"));
        condition.toJava(javaFile, newIndex);
        blockWhenTrue.toJava(javaFile, newIndex + 1);
        if (blockWhenFalse != null) {
            blockWhenFalse.toJava(javaFile, newIndex + 2);
        }
    }
}
