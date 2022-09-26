package robson;


import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;
import instructions.Block;
import instructions.If;
import instructions.Instruction;
import instructions.While;
import instructions.constants.False;
import instructions.constants.NumberValue;
import instructions.constants.True;
import instructions.oneArgument.Not;
import instructions.twoArguments.arithmetic.Add;
import instructions.twoArguments.arithmetic.Divide;
import instructions.twoArguments.arithmetic.Multiply;
import instructions.twoArguments.arithmetic.Subtract;
import instructions.twoArguments.logical.*;
import instructions.variables.AssignValueToVariable;
import instructions.variables.Variable;

public class MoshiSettings {
    private static final Moshi moshi = new Moshi.Builder()
            .add(
                    PolymorphicJsonAdapterFactory.of(Instruction.class, "typ")
                            .withSubtype(Add.class, "Plus")
                            .withSubtype(Subtract.class, "Minus")
                            .withSubtype(Multiply.class, "Razy")
                            .withSubtype(Divide.class, "Dzielenie")
                            .withSubtype(NumberValue.class, "Liczba")
                            .withSubtype(And.class, "And")
                            .withSubtype(Or.class, "Or")
                            .withSubtype(Greater.class, ">")
                            .withSubtype(GreaterEqual.class, ">=")
                            .withSubtype(Equal.class, "==")
                            .withSubtype(LessEqual.class, "<=")
                            .withSubtype(Less.class, "<")
                            .withSubtype(If.class, "If")
                            .withSubtype(While.class, "While")
                            .withSubtype(Not.class, "Not")
                            .withSubtype(True.class, "True")
                            .withSubtype(False.class, "False")
                            .withSubtype(Block.class, "Blok")
                            .withSubtype(Variable.class, "Zmienna")
                            .withSubtype(AssignValueToVariable.class, "Przypisanie")
            )
            .build();


    public static Moshi getMoshi() {
        return moshi;
    }

}
