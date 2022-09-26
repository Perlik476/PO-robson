package instructions;


import com.squareup.moshi.Json;
import instructions.variables.GlobalVariables;
import robson.JavaFile;

public class Block extends Instruction {
    @Json(name = "instrukcje")
    private final Instruction[] instructions;

    public Block(Instruction[] instructions) {
        this.instructions = instructions;
    }

    public double execute(GlobalVariables globalVariables) {
        double value = 0;
        for (Instruction instruction : instructions) {
            value = instruction.execute(globalVariables);
        }
        return value;
    }

    public void toJava(JavaFile javaFile, int index) {
        if (instructions.length == 0) {
            javaFile.setFunction(index, "return 0;\n");
        }
        else {
            int newIndex = javaFile.getLastFunctionIndex() + 1;
            javaFile.addFunction(instructions.length);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < instructions.length - 1; i++) {
                stringBuilder.append("" + javaFile.executeFunction(newIndex + i) + ";\n");
            }
            stringBuilder.append("return " + javaFile.executeFunction(newIndex + instructions.length - 1) +";\n");
            javaFile.setFunction(index, stringBuilder.toString());
            for (int i = 0; i < instructions.length; i++) {
                instructions[i].toJava(javaFile, newIndex + i);
            }
        }

        //javaFile.setFunction(1,"xd");
    }
}
