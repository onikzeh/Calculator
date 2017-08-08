package au.com.nab.calc.operator;

import au.com.nab.calc.model.BigDecimalOperand;

import java.math.BigDecimal;

/**
 * An {@link Operator} supporting Square Root math operation for {@link BigDecimal} numbers.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public class SquareRoot extends NabUnaryOperator<BigDecimalOperand> {

    private static final SquareRoot INSTANCE = new SquareRoot();

    /**
     * Make this private to limit creating a new instance for each Operand.
     */
    private SquareRoot() {
        super("sqrt");
    }

    /**
     * Returns an instance of this class supporting square root functionality.
     *
     * @return an instance of this class.
     */
    public static SquareRoot instance() {
        return INSTANCE;
    }

    /**
     * THe logic that implements a square root operation.
     *
     * @param operand the operand
     * @return outcome of square root operation.
     */
    @Override
    public BigDecimalOperand apply(BigDecimalOperand operand) {
        return new BigDecimalOperand(new BigDecimal(Math.sqrt(operand.getValue().doubleValue())));
    }
}
