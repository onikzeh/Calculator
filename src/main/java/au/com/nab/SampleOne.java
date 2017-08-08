package au.com.nab;

import au.com.nab.calc.Calculator;
import au.com.nab.calc.CalculatorException;
import au.com.nab.calc.model.Operand;
import au.com.nab.calc.operator.Divide;
import au.com.nab.calc.operator.Minus;
import au.com.nab.calc.operator.Multiply;
import au.com.nab.calc.operator.Plus;
import au.com.nab.calc.operator.SquareRoot;
import au.com.nab.calc.view.Display;
import au.com.nab.calc.view.SimpleDisplay;
import com.sun.javafx.binding.StringFormatter;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Sample to show how to use data entry using String input.
 * Usage would be in a other utility classes using this calculator.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public class SampleOne {

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
        Display display = new SimpleDisplay(System.out);

        // Setup a Calculator with support for BigDecimal operands and setting supported Operators
        Calculator<Operand<BigDecimal>> calculator = Calculator.Builder.<Operand<BigDecimal>>
                withDisplay(display)          // Add display to Calculator
                .withOperator(Minus.class)    // Add supported Operator
                .withOperator(Plus.class)     // Add supported Operator
                .withOperator(Multiply.class) // Add supported Operator
                .withOperator(Divide.class)   // Add supported Operator
                .withOperator(SquareRoot.class)
                .build();

        System.out.println(" ::::   Calculator ::::");
        System.out.println("Sample expression:");
        System.out.println();
        System.out.println("-1 + 1 + 10 / 10 - 20 * 2 + sqrt 9 * -1 + 2");
        System.out.println();

        System.out.println("Enter the conversion you need to perform. (x for exit)");
        try (Scanner userInputScanner = new Scanner(System.in)) {

            while (userInputScanner.hasNextLine()) {
                calculator.clear();
                String userInput = userInputScanner.nextLine();
                if (userInput.equals("x")) {
                    System.exit(0);
                }

                try {
                    calculator.feedExpression(userInput);

                    // Result is written to provided display which in this case is System.out
                    calculator.calculate();

                } catch (CalculatorException ex) {
                    System.err.println(
                            StringFormatter.format("Calculation failed with message '%s'.%s"
                                    , ex.getMessage()
                                    , ex.getCause() != null ? "Because of '" + ex.getCause().getMessage() + "'" : "")
                                    .getValue());
                }

                System.out.println("Enter the conversion you need to perform. (x for exit)");
            }
        }
    }
}
