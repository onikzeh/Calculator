package au.com.nab;

import au.com.nab.calc.Calculator;
import au.com.nab.calc.CalculatorException;
import au.com.nab.calc.model.BigDecimalOperand;
import au.com.nab.calc.model.Operand;
import au.com.nab.calc.operator.Divide;
import au.com.nab.calc.operator.Minus;
import au.com.nab.calc.operator.Multiply;
import au.com.nab.calc.operator.Plus;
import au.com.nab.calc.operator.SquareRoot;
import au.com.nab.calc.view.Display;
import au.com.nab.calc.view.MuteDisplay;

import java.math.BigDecimal;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Sample to show how to use data entry using adding steps individually.
 * Usage would be in a Swing based application that user wants to add calculation steps by pressing some buttons.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public class SampleTwo {

    static {
        // Set up logger to monitor Calculator logs
        Logger rootLogger = Logger.getLogger("");
        for (Handler handler : rootLogger.getHandlers()) {
            handler.setLevel(Level.FINER);
            handler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return record.getMessage() + System.lineSeparator();
                }
            });
        }
        rootLogger.setLevel(Level.FINER);
    }

    public static void main(String[] args) {
        // Setting up a display to be used by Calculator to show results
        Display display = new MuteDisplay();

        // Setup a Calculator with support for BigDecimal operands and setting supported Operators
        Calculator<Operand<BigDecimal>> calculator = Calculator.Builder.<Operand<BigDecimal>>
                withDisplay(display)          // Add display to Calculator
                .withOperator(Minus.class)    // Add supported Operator
                .withOperator(Plus.class)     // Add supported Operator
                .withOperator(Multiply.class) // Add supported Operator
                .withOperator(Divide.class)   // Add supported Operator
                .withOperator(SquareRoot.class)
                .build();

        // Simulate adding operands and operations step by step
        calculator.addEntry(new BigDecimalOperand(1.12345));
        calculator.addEntry(Plus.instance());
        calculator.addEntry(new BigDecimalOperand(12));
        calculator.addEntry(SquareRoot.instance());
        calculator.addEntry(new BigDecimalOperand(9));
        calculator.addEntry(Divide.instance());
        calculator.addEntry(new BigDecimalOperand(60));
        calculator.addEntry(Minus.instance());
        calculator.addEntry(new BigDecimalOperand(9));
        calculator.addEntry(Multiply.instance());
        calculator.addEntry(new BigDecimalOperand(10));
        calculator.addEntry(Plus.instance());
        calculator.addEntry(new BigDecimalOperand(100));
        calculator.addEntry(SquareRoot.instance());
        calculator.addEntry(new BigDecimalOperand(25));

        try {
            System.out.println("Result is: '" + calculator.calculate() + "'");
        } catch (CalculatorException e) {
            System.err.printf("Calculation failed: " + e.getMessage());
        }
    }
}
