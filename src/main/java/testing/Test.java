package testing;

public class Test {
    private final String filepath;
    private final double result;

    public Test(String filepath, double result) {
        this.filepath = filepath;
        this.result = result;
    }

    public String getFilepath() {
        return filepath;
    }

    public double getResult() {
        return result;
    }
}
