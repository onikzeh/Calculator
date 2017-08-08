package au.com.nab.calc.operator;

import au.com.nab.calc.Calculator;
import au.com.nab.calc.model.Operand;
import au.com.nab.calc.view.MuteDisplay;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.math.BigDecimal;
import java.util.Collection;

import static au.com.nab.calc.helper.TestData.TEST_DATA_COMPLEX;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests complex scenarios for expressions with all operators.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 31/7/17
 */
@RunWith(Parameterized.class)
public class CalculatorTest_all_operator_complex {

    private static Calculator<Operand<BigDecimal>> calculator;

    @Parameter
    public String expression;

    @Parameter(value = 1)
    public double expected;

    @Parameters
    public static Collection<Object[]> data() throws Exception {
        return TEST_DATA_COMPLEX;
    }

    @BeforeClass
    public static void setUp() throws Exception {
        // Setup a Calculator with support for BigDecimal operands and setting Plus Operator
        calculator = Calculator.Builder.<Operand<BigDecimal>>
                withDisplay(new MuteDisplay())  // We don't need a display for testing
                .withOperator(Minus.class)       // Add supported Operator
                .withOperator(Plus.class)       // Add supported Operator
                .withOperator(Multiply.class)       // Add supported Operator
                .withOperator(Divide.class)       // Add supported Operator
                .withOperator(SquareRoot.class)       // Add supported Operator
                .build();
    }

    @Test
    public void calculate() throws Exception {
        assertThat(
                calculator.calculate(expression).getValue().doubleValue(),
                is(expected));
    }

}