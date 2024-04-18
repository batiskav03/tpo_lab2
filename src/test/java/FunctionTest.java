import batiskav.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FunctionTest {
    private static final double DEFAULT_EPS = 0.0000001;
    static Sec secMock;
    static Cos cosMock;
    static Sin sinMock;
    static Ln lnMock;
    static Log logMock;
    static Csc cscMock;
    static Cot cotMock;

    @BeforeAll
    static void init() {
        try {
            secMock = Mockito.mock(Sec.class);
            cosMock = Mockito.mock(Cos.class);
            sinMock = Mockito.mock(Sin.class);
            lnMock = Mockito.mock(Ln.class);
            logMock = Mockito.mock(Log.class);
            cscMock = Mockito.mock(Csc.class);
            cotMock = Mockito.mock(Cot.class);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(new FileReader("src/main/resources/sec.csv"));
            for (CSVRecord record : records) {
                when(secMock.calc(Double.parseDouble(record.get(0)), DEFAULT_EPS)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(new FileReader("src/main/resources/cos.csv"));
            for (CSVRecord record : records) {
                when(cosMock.calc(Double.parseDouble(record.get(0)), DEFAULT_EPS)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(new FileReader("src/main/resources/sin.csv"));
            for (CSVRecord record : records) {
                when(sinMock.calc(Double.parseDouble(record.get(0)), DEFAULT_EPS)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(new FileReader("src/main/resources/ln.csv"));
            for (CSVRecord record : records) {
                when(lnMock.calc(Double.parseDouble(record.get(0)), DEFAULT_EPS)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(new FileReader("src/main/resources/log2.csv"));
            for (CSVRecord record : records) {
                when(logMock.calc(2, Double.parseDouble(record.get(0)), DEFAULT_EPS)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(new FileReader("src/main/resources/log5.csv"));
            for (CSVRecord record : records) {
                when(logMock.calc(5, Double.parseDouble(record.get(0)), DEFAULT_EPS)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(new FileReader("src/main/resources/log10.csv"));
            for (CSVRecord record : records) {
                when(logMock.calc(10, Double.parseDouble(record.get(0)), DEFAULT_EPS)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(new FileReader("src/main/resources/csc.csv"));
            for (CSVRecord record : records) {
                when(cscMock.calc(Double.parseDouble(record.get(0)), DEFAULT_EPS)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(new FileReader("src/main/resources/cot.csv"));
            for (CSVRecord record : records) {
                when(cotMock.calc(Double.parseDouble(record.get(0)), DEFAULT_EPS)).thenReturn(Double.valueOf(record.get(1)));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @ParameterizedTest
    @CsvFileSource(resources = "function.csv")
    void testWithMock(double value, double expected) {
        Function function = new Function(DEFAULT_EPS, cscMock, cotMock, sinMock, secMock, cosMock, logMock, lnMock);
        assertEquals(expected, function.solve(value));
    }

    @Test
    void testNaN() {
        Function function = new Function(DEFAULT_EPS);
        assertEquals(Double.NaN, function.solve(1));
    }

}
