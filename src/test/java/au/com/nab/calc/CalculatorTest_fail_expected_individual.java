package au.com.nab.calc;

import au.com.nab.calc.model.Operand;
import au.com.nab.calc.operator.Divide;
import au.com.nab.calc.operator.Minus;
import au.com.nab.calc.operator.Multiply;
import au.com.nab.calc.operator.Plus;
import au.com.nab.calc.operator.SquareRoot;
import au.com.nab.calc.view.MuteDisplay;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

/**
 * Tests Calculator against error scenarios.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 31/7/17
 */
@RunWith(JUnit4.class)
public class CalculatorTest_fail_expected_individual {

    private static Calculator<Operand<BigDecimal>> calculator;

    @Test(expected = NullPointerException.class)
    public void withOperator_must_not_accept_null() throws Exception {
        calculator = Calculator.Builder.<Operand<BigDecimal>>
                withDisplay(new MuteDisplay())  // We don't need a display for testing
                .withOperator(null)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void without_operator_must_not_build() throws Exception {
        calculator = Calculator.Builder.<Operand<BigDecimal>>
                withDisplay(new MuteDisplay())  // We don't need a display for testing
                .build();
    }

    @Test(expected = CalculatorException.class)
    public void must_not_accept_undefined_operator_plus() throws CalculatorException {
        // Setup a Calculator with support for BigDecimal operands and setting Plus Operator
        calculator = Calculator.Builder.<Operand<BigDecimal>>
                withDisplay(new MuteDisplay())  // We don't need a display for testing
                .withOperator(Plus.class)       // Add supported Operator
                .build();

        calculator.calculate("1 - 2");
    }

    @Test(expected = CalculatorException.class)
    public void must_not_accept_undefined_operator_minus() throws CalculatorException {
        // Setup a Calculator with support for BigDecimal operands and setting Plus Operator
        calculator = Calculator.Builder.<Operand<BigDecimal>>
                withDisplay(new MuteDisplay())  // We don't need a display for testing
                .withOperator(Minus.class)       // Add supported Operator
                .build();

        calculator.calculate("1 + 2");
    }

    @Test(expected = CalculatorException.class)
    public void must_not_accept_undefined_operator_multiply() throws CalculatorException {
        // Setup a Calculator with support for BigDecimal operands and setting Plus Operator
        calculator = Calculator.Builder.<Operand<BigDecimal>>
                withDisplay(new MuteDisplay())  // We don't need a display for testing
                .withOperator(Multiply.class)       // Add supported Operator
                .build();

        calculator.calculate("1 + 2");
    }

    @Test(expected = CalculatorException.class)
    public void must_not_accept_undefined_operator_divide() throws CalculatorException {
        // Setup a Calculator with support for BigDecimal operands and setting Plus Operator
        calculator = Calculator.Builder.<Operand<BigDecimal>>
                withDisplay(new MuteDisplay())  // We don't need a display for testing
                .withOperator(Divide.class)       // Add supported Operator
                .build();

        calculator.calculate("1 * 2");
    }

    @Test(expected = CalculatorException.class)
    public void must_not_accept_undefined_operator_devide_missing() throws CalculatorException {
        // Setup a Calculator with support for BigDecimal operands and setting all Operators
        calculator = Calculator.Builder.<Operand<BigDecimal>>
                withDisplay(new MuteDisplay())  // We don't need a display for testing
                .withOperator(Minus.class)       // Add supported Operator
                .withOperator(Plus.class)       // Add supported Operator
                .withOperator(Multiply.class)       // Add supported Operator
                .withOperator(SquareRoot.class)       // Add supported Operator
                .build();

        calculator.calculate("1 / 2 + 5 * 78 - 7");
    }

    @Test(expected = CalculatorException.class)
    public void must_not_accept_undefined_operator_plus_missing() throws CalculatorException {
        // Setup a Calculator with support for BigDecimal operands and setting all Operators
        calculator = Calculator.Builder.<Operand<BigDecimal>>
                withDisplay(new MuteDisplay())  // We don't need a display for testing
                .withOperator(Minus.class)       // Add supported Operator
                .withOperator(Multiply.class)       // Add supported Operator
                .withOperator(Divide.class)       // Add supported Operator
                .withOperator(SquareRoot.class)       // Add supported Operator
                .build();

        calculator.calculate("1 / 2 + 5 * 78 - 7");
    }


}