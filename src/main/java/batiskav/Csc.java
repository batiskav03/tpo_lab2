package batiskav;

public class Csc {
    private Sin sin;

    public Csc(){
        this.sin = new Sin();
    }
    public double calc(double x, double eps) {

        return 1 / sin.calc(x, eps);
    }
}
