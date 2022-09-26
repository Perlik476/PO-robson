package testing;

import exceptions.BladWykonania;
import exceptions.NieprawidlowyProgram;
import robson.Robson;

/**
 * Klasa odpowiedzialna za testowanie.
 */

public class Tester {
    private boolean printInfo = true;

    private final Test[] tests = {
            new Test("tests/example.json", 15),
            new Test("tests/fibonacci.json", 55),
            new Test("tests/suma.json", 2284453),
            new Test("tests/potegowanie.json", 2097152),
            new Test("tests/euklides.json", 2940),
    };

    private boolean checkTest(Test test, Robson robson) {
        print("Testing " + test.getFilepath() + ": ");
        try {
            robson.fromJson(test.getFilepath());
        } catch (NieprawidlowyProgram nieprawidlowyProgram) {
            print("Program in JSON is incorrect.\n");
            return false;
        }
        double result;

        try {
            result = robson.wykonaj();
            if (result == test.getResult()) {
                print("OK\n");
                return true;
            }
            else {
                print("WRONG OUTPUT: " + result + " instead of " + test.getResult() + "\n");
                return false;
            }
        } catch (BladWykonania bladWykonania) {
            print("ERROR\n");
            return false;
        }
    }

    private void tryToJava(Test test, Robson robson) {
        robson.toJava("tests/toJava.java");
    }

    private void print(String string) {
        if (printInfo) {
            System.out.print(string);
        }
    }

    /**
     * Funkcję należy wywołać na obiekcie klasy Tester.
     * @param printInfo : czy wypisywać informacje na standardowe wyjście
     * @return : czy wszystkie testy się powiodły?
     */
    public boolean run(boolean printInfo) {
        this.printInfo = printInfo;
        print("Running tests...\n");

        Robson robson = new Robson();
        int counter = 0;
        for (Test test : tests) {
            counter += checkTest(test, robson) ? 1 : 0;
            tryToJava(test, robson);
        }

        print(counter + "/" + tests.length + " tests passed.\n");

        return counter == tests.length;
    }
}
