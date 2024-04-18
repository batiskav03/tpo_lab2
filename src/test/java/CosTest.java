import batiskav.Cos;
import batiskav.Sin;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CosTest {

    private static final double DEFAULT_EPS = 0.0000001;

    @Mock static Sin sinMock;

    @BeforeAll
    static void init() {
        sinMock = Mockito.mock(Sin.class);
        try {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(new FileReader("src/main/resources/sin.csv"));
            for (CSVRecord record : records) {
                when(sinMock.calc(Double.parseDouble(record.get(0)), DEFAULT_EPS)).thenReturn(Double.valueOf(record.get(1)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @ParameterizedTest
    @CsvFileSource(resources = "cos.csv")
    void testWithMocks(double value, double expected) {
        Cos cos = new Cos();
        assertEquals(expected, cos.calc(value, DEFAULT_EPS));
    }



}
