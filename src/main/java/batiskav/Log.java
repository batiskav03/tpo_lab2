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
public class Log {
    private Ln ln;

    public Log() {
        this.ln = new Ln();
    }
    public double calc(double pow, double x, double eps) {

        return ln.calc(x, eps) / ln.calc(pow, eps);
    }

    public void writeResultToCSV(double pow, double from, double to, double step, double eps, String outputFilePath) {
        try {
            BufferedWriter out = Files.newBufferedWriter(Paths.get(outputFilePath));
            try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
                for (double i = from; i < to; i += step) {
                    double res = calc(pow, i, eps);
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
