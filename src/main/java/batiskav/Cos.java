package batiskav;

import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    public void writeResultToCSV(double from, double to, double step, double eps, String outputFilePath) {
        try {
            BufferedWriter out = Files.newBufferedWriter(Paths.get(outputFilePath));
            try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
                for (double i = from; i <= to; i += step) {
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
