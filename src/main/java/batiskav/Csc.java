package batiskav;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Csc {
    private Sin sin;

    public Csc(){
        this.sin = new Sin();
    }
    public double calc(double x, double eps) {

        return 1 / sin.calc(x, eps);
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
