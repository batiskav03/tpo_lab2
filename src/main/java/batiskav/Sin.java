package batiskav;

public class Sin {

    private double fact(double x) {
        if (x == 0) return 1;
        return x * fact(x - 1);
    }

    public double calc(double x, double eps) {
        x = processArg(x);
        int sign = 1;
        double pow = x;
        double result = 0;
        double prevResult = 1;
        double xx = x * x;
        double n = 0;
        while (Math.abs(result - prevResult) > eps) {
            prevResult = result;
            result += sign * pow / fact(2*n + 1);
            pow *= xx;
            sign = -sign;
            n++;
        }
        return result;
    }

    public double processArg(double x) {
        if (x >= 0) {
            while (x > Math.PI * 2) {
                x -= Math.PI * 2;
            }
        } else if (x < 0) {
            while (x < Math.PI * 2) {
                x += Math.PI * 2;
            }
        }

        return x;
    }
}
