import Calculator.Calculator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalcTest {

    private Calculator calculator;
    String filepath;

    @Before
    public void setUp() throws Exception {
        calculator = new Calculator();
        filepath = getClass().getResource("numbers.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        assertThat(calculator.calcSum(filepath), is(10));
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        assertThat(calculator.calcMultiply(filepath), is(24));
    }

    @Test
    public void concatenateOfNumbers() throws IOException {
        assertThat(calculator.concatenate(filepath), is("1234"));
    }
}
