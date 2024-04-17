package batiskav;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Function {
    private double eps;
    private Csc csc;
    private Cot cot;
    private Sin sin;
    private Sec sec;
    private Cos cos;
    private Log log;
    private Ln ln;

    public Function(double eps) {
        this.eps = eps;
        this.cos = new Cos();
        this.sin = new Sin();
        this.cot = new Cot();
        this.ln = new Ln();
        this.log = new Log();
        this.sec = new Sec();
        this.csc = new Csc();
    }



    public double solve(double x) {
        if (x <= 0)
            return ((((Math.pow(csc.calc(x, eps), 2) * cot.calc(x, eps)) + (csc.calc(x, eps) * cot.calc(x, eps))) - ((Math.pow(sin.calc(x, eps), 2)) / sec.calc(x, eps))) - (csc.calc(x, eps) / cos.calc(x, eps)));
        else
            return (Math.pow(((Math.pow((log.calc(5, x, eps) * log.calc(2, x, eps)), 2) * log.calc(2, x, eps))), 3) / Math.pow((((log.calc(10, x, eps) * ln.calc(x, eps)) + ln.calc(x, eps)) * (log.calc(5, x, eps) / log.calc(2, x, eps))), 2));
    }
}
