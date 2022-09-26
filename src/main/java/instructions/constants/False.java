package instructions.constants;

import instructions.Instruction;
import instructions.converter.Converter;
import instructions.variables.GlobalVariables;
import robson.JavaFile;

public class False extends Instruction {
    public double execute(GlobalVariables globalVariables) {
        return Converter.booleanToDouble(false);
    }

    public void toJava(JavaFile javaFile, int index) {
        javaFile.setFunction(index,
                "return 0;\n");
    }
}
