import batiskav.Ln;
import batiskav.Log;
import batiskav.Sin;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LogTest {

    private static final double DEFAULT_EPS = 0.0000001;
    static Ln lnMock;

    @BeforeAll
    static void init() {
        lnMock = Mockito.mock(Ln.class);
        try {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(new FileReader("src/main/resources/ln.csv"));
            for (CSVRecord record : records) {
                when(lnMock.calc(Double.parseDouble(record.get(0)), DEFAULT_EPS)).thenReturn(Double.valueOf(record.get(1)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "log2.csv")
    void log2MockTest(double value, double expected) {
        Log log2 = new Log(lnMock);
        assertEquals(expected, log2.calc(2, value, DEFAULT_EPS));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "log5.csv")
    void log5MockTest(double value, double expected) {
        Log log5 = new Log(lnMock);
        assertEquals(expected, log5.calc(5, value, DEFAULT_EPS));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "log10.csv")
    void log10MockTest(double value, double expected) {
        Log log10 = new Log(lnMock);
        assertEquals(expected, log10.calc(10, value, DEFAULT_EPS));
    }
}
