package robson;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import exceptions.BladWykonania;
import exceptions.NieprawidlowyProgram;
import instructions.Instruction;
import instructions.variables.GlobalVariables;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class Robson {
    private Instruction program;
    private final GlobalVariables globalVariables = new GlobalVariables();

    public void fromJson(String filename) throws NieprawidlowyProgram {
        Moshi moshi = MoshiSettings.getMoshi();

        JsonAdapter<Instruction> jsonAdapter = moshi.adapter(Instruction.class);
        String json;
        try {
            File file = new File(filename);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                json = scanner.useDelimiter("\\A").next();
                scanner.close();
                program = jsonAdapter.fromJson(json);
            }
            else {
                System.err.println("File does not exists.");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    
    public void toJson(String filename) {
        Moshi moshi = MoshiSettings.getMoshi();

        JsonAdapter<Instruction> jsonAdapter = moshi.adapter(Instruction.class);
        String json = jsonAdapter.toJson(program);

        try {
            File file = new File(filename);
            file.createNewFile();
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(json);
            myWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void toJava(String filename) {
        JavaFile javaFile = new JavaFile(filename);
        javaFile.addFunction(1);
        program.toJava(javaFile, 0);
        String java = javaFile.getString();
        try {
            File file = new File(filename);
            file.createNewFile();
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(java);
            myWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public double wykonaj() throws BladWykonania {
        globalVariables.clear();
        try {
            return program.execute(globalVariables);
        }
        catch (Exception e) {
            throw new BladWykonania();
        }
    }

    public void setProgram(Instruction program) {
        this.program = program;
    }

}
