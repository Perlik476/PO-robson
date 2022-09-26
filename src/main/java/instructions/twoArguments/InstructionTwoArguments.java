package instructions.twoArguments;

import com.squareup.moshi.Json;
import instructions.Instruction;

public abstract class InstructionTwoArguments extends Instruction {
    protected Instruction argument1;
    protected Instruction argument2;
}
