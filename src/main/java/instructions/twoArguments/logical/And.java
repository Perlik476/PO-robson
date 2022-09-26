package instructions.twoArguments.logical;

import instructions.converter.Converter;
import instructions.Instruction;
import instructions.twoArguments.InstructionTwoArguments;
import instructions.variables.GlobalVariables;
import robson.JavaFile;

public class And extends InstructionTwoArguments {
    public And(Instruction argument1, Instruction argument2) {
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    public double execute(GlobalVariables globalVariables) {
        return Converter.booleanToDouble(
                Converter.doubleToBoolean(argument1.execute(globalVariables)) &&
                        Converter.doubleToBoolean(argument2.execute(globalVariables)));
    }

    public void toJava(JavaFile javaFile, int index) {
        int newIndex = javaFile.getLastFunctionIndex() + 1;
        javaFile.addFunction(2);
        javaFile.setFunction(index,
                "return (" + javaFile.executeFunction(newIndex) + " !=0 && " +
                        javaFile.executeFunction(newIndex + 1) + " !=0) ? 1 : 0;\n");
        argument1.toJava(javaFile, newIndex);
        argument2.toJava(javaFile, newIndex + 1);
    }
}
