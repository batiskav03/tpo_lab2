package batiskav;

public class Cot {
    private Cos cos;
    private Sin sin;

    public Cot() {
        this.cos = new Cos();
        this.sin = new Sin();
    }
    public double calc(double x, double eps) {

        return cos.calc(x, eps) / sin.calc(x, eps);
    }
}
