package instructions;

import instructions.variables.GlobalVariables;
import robson.JavaFile;

public abstract class Instruction {
    public abstract double execute(GlobalVariables globalVariables);

    public abstract void toJava(JavaFile javaFile, int index);
}
