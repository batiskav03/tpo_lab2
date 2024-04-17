package batiskav;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Cos {

    private Sin sin;
    public Cos() {
        this.sin = new Sin();
    }

    public double calc(double x, double eps) {
        x = sin.processArg(x);
        if (x > Math.PI / 2 || x < -Math.PI / 2) {
            return -1 * Math.sqrt(1 - sin.calc(x, eps) * sin.calc(x, eps));
        }

        return Math.sqrt(1 - sin.calc(x, eps) * sin.calc(x, eps));
    }
}
