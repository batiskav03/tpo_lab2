package batiskav;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ln {

    public double calc(double x, double eps) {
        if (x < (double) 0)
            return Double.NaN;
        else if (x == 0) {
            return Double.NEGATIVE_INFINITY;
        }

        double constant = ((x - 1) * (x - 1)) / ((x + 1) * (x + 1));

        double sum = 0;
        double currentValue = (x - 1) / (x + 1);
        int step = 1;
        while (Math.abs(currentValue) > eps / 2) {
            sum += currentValue;
            currentValue = (2 * step - 1) * currentValue * constant / (2 * step + 1);
            step++;
        }
        sum *= 2;
        return sum;
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
