package au.com.nab.calc;

/**
 * Constants used in program.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public final class Constants {

    /**
     * number of zeroes needed after . for showing numbers.
     */
    public static final int DEFAULT_NUMBER_PRECISION = 2;

    /**
     * A regex to separate operands from operators while using the String input.
     */
    public static final String REGEX_OPERAND_SEPARATOR = "(?<=\\d+[.])(?=\\D)|(?=\\d+[.])(?<=\\D)|\\s+";

    private Constants() {
    }

}
