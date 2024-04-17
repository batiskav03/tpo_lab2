package batiskav;

public class Sec {
    private Cos cos;

    public Sec() {
        this.cos = new Cos();
    }
    public double calc(double x, double eps) {

        return 1 / cos.calc(x, eps);
    }
}
