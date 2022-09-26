package instructions.oneArgument;

import instructions.Instruction;
import instructions.converter.Converter;
import instructions.variables.GlobalVariables;
import robson.JavaFile;

public class Not extends InstructionOneArgument {
    public Not(Instruction argument) {
        this.argument = argument;
    }

    public double execute(GlobalVariables globalVariables) {
        return Converter.booleanToDouble(!Converter.doubleToBoolean(argument.execute(globalVariables)));
    }

    public void toJava(JavaFile javaFile, int index) {
        int newIndex = javaFile.getLastFunctionIndex() + 1;
        javaFile.addFunction(1);
        javaFile.setFunction(index,
                "return " + javaFile.executeFunction(newIndex) + " == 0 ? 1 : 0;\n");
        argument.toJava(javaFile, newIndex);
    }
}
