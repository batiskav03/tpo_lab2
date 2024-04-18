package batiskav;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Sin sin = new Sin();
        Cos cos = new Cos();
        Ln ln = new Ln();
        Log log = new Log();
        Csc csc = new Csc();
        Cot cot = new Cot();
        Sec sec = new Sec();
        cos.writeResultToCSV(-10, 10,0.5, 0.0000001, "src/main/resources/cos.csv");
        sin.writeResultToCSV(-10, 10,0.5, 0.0000001, "src/main/resources/sin.csv");
        ln.writeResultToCSV(0, 10.5,0.5, 0.0000001, "src/main/resources/ln.csv");
        csc.writeResultToCSV(-10, 10,0.5, 0.0000001, "src/main/resources/csc.csv");
        cot.writeResultToCSV(-10, 10,0.5, 0.0000001, "src/main/resources/cot.csv");
        sec.writeResultToCSV(-10, 10,0.5, 0.0000001, "src/main/resources/sec.csv");
        log.writeResultToCSV(2, 0, 10.5,0.5, 0.0000001, "src/main/resources/log2.csv");
        log.writeResultToCSV(5, 0, 10.5,0.5, 0.0000001, "src/main/resources/log5.csv");
        log.writeResultToCSV(10, 0, 10.5,0.5, 0.0000001, "src/main/resources/log10.csv");

        Function func = new Function(0.0000001,new Csc(), new Cot(), new Sin(), new Sec(), new Cos(), new Log(), new Ln());
        try {
            BufferedWriter out = Files.newBufferedWriter(Paths.get("src/main/resources/function.csv"));
            try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
                for (double i = -10; i < 10; i += 0.5) {
                    double res = func.solve(i);
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