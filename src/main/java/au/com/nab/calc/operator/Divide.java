package au.com.nab.calc.operator;

import au.com.nab.calc.Constants;
import au.com.nab.calc.model.BigDecimalOperand;

import java.math.BigDecimal;

/**
 * An {@link Operator} supporting Divide math operation for {@link BigDecimal} numbers.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public class Divide extends NabBinaryOperator<BigDecimalOperand> {

    private static final Divide INSTANCE = new Divide();

    /**
     * Make this private to limit creating a new instance for each Operand.
     */
    private Divide() {
        super("/", Precedence.HIGH);
    }

    /**
     * Returns an instance of this class supporting divide functionality.
     *
     * @return an instance of this class.
     */
    public static Divide instance() {
        return INSTANCE;
    }

    /**
     * THe logic that implements a divide operation.
     *
     * @param firstOperand  operand that needs to be divided to
     * @param secondOperand operand that will be divided by
     * @return outcome of dividing operation.
     */
    @Override
    public BigDecimalOperand apply(BigDecimalOperand firstOperand, BigDecimalOperand secondOperand) {
        return new BigDecimalOperand(firstOperand.getValue().divide(secondOperand.getValue(), Constants.DEFAULT_NUMBER_PRECISION, BigDecimal.ROUND_HALF_UP));
    }
}