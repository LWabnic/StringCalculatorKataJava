package kata;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StringCalculatorTest {

    private StringCalculator calculator;

    @Before
    public void initialize() {
        calculator = new StringCalculator();
    }

    @Test
    public void add_EmptyString_ReturnsZero() {
        int sum = calculator.add("");
        assertEquals(0, sum);
    }

    @Test
    public void add_OneNumber_ReturnsTheNumber() {
        int sum = calculator.add("5");
        assertEquals(5, sum);
    }

    @Test
    public void add_TwoNumbers_ReturnsTheirSum() {
        int sum = calculator.add("5,7");
        assertEquals(12, sum);
    }

    @Test
    public void add_MultipleNumbers_ReturnsTheirSum() {
        int sum = calculator.add("1,2,3,4,5");
        assertEquals(15, sum);
    }

    @Test
    public void add_NewLineDelimiter_ReturnsSum() {
        int sum = calculator.add("1\n2,3");
        assertEquals(6, sum);
    }

    @Test
    public void add_CustomDelimiter_ReturnsSum() {
        int sum = calculator.add("//;\n1;2");
        assertEquals(3, sum);
    }

    @Test
    public void add_NegativeNumber_ThrowsException() {
        try {
            calculator.add("2,-4,3,-5");
            fail("Exception not thrown for negative numbers.");
        } catch (RuntimeException ex) {
            assertEquals("Negatives not allowed: -4,-5", ex.getMessage());
        }
    }

    @Test
    public void add_NumberGreaterThan1000_IgnoresNumber() {
        int sum = calculator.add("1001,2");
        assertEquals(2, sum);
    }

    @Test
    public void add_DelimitersOfAnyLength_ReturnsSum() {
        int sum = calculator.add("//[|||]\n1|||2|||3");
        assertEquals(6, sum);
    }

    @Test
    public void add_DelimitersOfVariousSpecialCharacters_ReturnsSum() {
        // Using "##" and "@@" as delimiters
        int sum = calculator.add("//[##][@@]\n1##2@@3");
        assertEquals(6, sum);
    }

    @Test
    public void add_MultipleDelimitersOfDifferentLengths_ReturnsSum() {
        // Using "***" and "@@" as delimiters
        int sum = calculator.add("//[***][@@]\n1***2@@3");
        assertEquals(6, sum);
    }

    @Test
    public void add_MultipleDelimitersWithMoreComplexPattern_ReturnsSum() {
        // Using "**#*" and "@@@" as delimiters
        int sum = calculator.add("//[**#*][@@@]\n1**#*2@@@3");
        assertEquals(6, sum);
    }
}
