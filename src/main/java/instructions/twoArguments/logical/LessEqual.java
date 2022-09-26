package instructions.twoArguments.logical;

import instructions.Instruction;
import instructions.converter.Converter;
import instructions.twoArguments.InstructionTwoArguments;
import instructions.variables.GlobalVariables;
import robson.JavaFile;

public class LessEqual extends InstructionTwoArguments {
    public LessEqual(Instruction argument1, Instruction argument2) {
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    public double execute(GlobalVariables globalVariables) {
        return Converter.booleanToDouble(argument1.execute(globalVariables) <= argument2.execute(globalVariables));
    }

    public void toJava(JavaFile javaFile, int index) {
        int newIndex = javaFile.getLastFunctionIndex() + 1;
        javaFile.addFunction(2);
        javaFile.setFunction(index,
                "return " + javaFile.executeFunction(newIndex) + " <= " +
                        javaFile.executeFunction(newIndex + 1) + " ? 1 : 0;\n");
        argument1.toJava(javaFile, newIndex);
        argument2.toJava(javaFile, newIndex + 1);
    }
}
