package robson;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Klasa, w której przechowywany jest generowane plik wykonywalny w Javie.
 */
public class JavaFile {
    private final String filename;
    private final ArrayList<String> variables = new ArrayList<>();
    private final ArrayList<String> functions = new ArrayList<>();
    private int lastFunctionIndex = -1;

    public JavaFile(String filepath) {
        Path path = Paths.get(filepath);
        String temp = path.getFileName().toString();

        String[] strings = temp.split("\\.");
        if (strings.length != 2) {
            System.err.println("Filename should be '*.java' and not contain more than one dot.");
        }

        filename = strings[0];
    }

    public void addVariable(String name) {
        if (!variables.contains(name)) {
            variables.add(name);
        }
    }

    public void addFunction(int numberOfFunctions) {
        lastFunctionIndex += numberOfFunctions;
        for (int i = 0; i < numberOfFunctions; i++) {
            functions.add("");
        }
    }

    public int getLastFunctionIndex() {
        return lastFunctionIndex;
    }

    public void setFunction(int index, String string) {
        functions.set(index, string);
    }

    public String executeFunction(int index) {
        return "function" + index + "()";
    }

    private String addTabs(String string, int number) {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(string);
        while(scanner.hasNextLine()) {
            stringBuilder.append("    ".repeat(Math.max(0, number)));
            stringBuilder.append(scanner.nextLine()).append("\n");
        }
        return stringBuilder.toString();
    }

    public String getString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("public class ").append(filename).append(" { \n");

        for (String variable : variables) {
            stringBuilder.append("    static double ").append(variable).append(" = 0;\n");
        }

        stringBuilder.append("\n");

        for (int i = 0; i < functions.size(); i++) {
            stringBuilder.append("    static double function").append(i).append("() {\n")
                    .append(addTabs(functions.get(i), 2)).append("    }\n\n");
        }

        stringBuilder.append("    public static void main(String[] args) {\n" +
                "        System.out.println(function0());\n    }\n}");
        return stringBuilder.toString();
    }
}
