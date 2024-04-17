package batiskav;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Sin sin = new Sin();
        Cos cos = new Cos();
        Ln ln = new Ln();
        Log log = new Log();
        Csc csc = new Csc();
        Cot cot = new Cot();
        System.out.println(cot.calc(10, 0.0001));
    }
}