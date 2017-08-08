package au.com.nab.calc.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;

import static au.com.nab.calc.Constants.DEFAULT_NUMBER_PRECISION;

/**
 * An Operand based on {@link BigDecimal}.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public class BigDecimalOperand extends Operand<BigDecimal> {

    public BigDecimalOperand(BigDecimal value) {
        super(value);
    }

    public BigDecimalOperand(String value) {
        this(new BigDecimal(value));
    }

    public BigDecimalOperand(double value) {
        this(new BigDecimal(value));
    }

    public BigDecimalOperand(int value) {
        this(new BigDecimal(value));
    }

    @Override
    public String toString() {
        char[] chars = new char[DEFAULT_NUMBER_PRECISION];
        Arrays.fill(chars, '#');
        return new DecimalFormat(chars.length == 0 ? "" : "." + new String(chars)).format(getValue().doubleValue());
    }
}
