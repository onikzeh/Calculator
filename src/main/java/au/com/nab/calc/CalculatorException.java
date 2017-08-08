package au.com.nab.calc;

/**
 * Wraps any error happening during the calculation.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public class CalculatorException extends Exception {

    public CalculatorException(String message) {
        super(message);
    }

    public CalculatorException(String message, Throwable cause) {
        super(message, cause);
    }

}
