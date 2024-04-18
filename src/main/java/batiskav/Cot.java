package batiskav;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    public double writeResultToCSV(double x, double eps, Writer out) {
        double res = calc(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Wrong filename");
        }
        return res;
    }

    public void writeResultToCSV(double from, double to, double step, double eps, String outputFilePath) {
        try {
            BufferedWriter out = Files.newBufferedWriter(Paths.get(outputFilePath));
            try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
                for (double i = from; i < to; i += step) {
                    double res = calc(i, eps);
                    printer.printRecord(i, res);
                }

            } catch (IOException e) {
                System.out.println("Wrong filename");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
