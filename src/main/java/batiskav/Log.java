package batiskav;

public class Log {
    private Ln ln;

    public Log() {
        this.ln = new Ln();
    }
    public double calc(double pow, double x, double eps) {

        return ln.calc(x, eps) / ln.calc(pow, eps);
    }
}
