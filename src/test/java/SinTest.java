import batiskav.Function;
import batiskav.Sin;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.*;

public class SinTest {

    private static final double DEFAULT_EPS = 0.0000001;

    @ParameterizedTest
    @CsvFileSource(resources = "sin.csv")
    void testWithMocks(double value, double expected) {
        Sin sin = new Sin();
        assertEquals(sin.calc(value, DEFAULT_EPS), expected);
    }
}
