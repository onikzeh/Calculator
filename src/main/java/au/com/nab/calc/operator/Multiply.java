package au.com.nab.calc.operator;

import au.com.nab.calc.model.BigDecimalOperand;

import java.math.BigDecimal;

/**
 * An {@link Operator} supporting Multiply math operation for {@link BigDecimal} numbers.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public class Multiply extends NabBinaryOperator<BigDecimalOperand> {

    private static final Multiply INSTANCE = new Multiply();

    /**
     * Make this private to limit creating a new instance for each Operand.
     */
    private Multiply() {
        super("*", Precedence.HIGH);
    }

    /**
     * Returns an instance of this class supporting multiply functionality.
     *
     * @return an instance of this class.
     */
    public static Multiply instance() {
        return INSTANCE;
    }

    /**
     * THe logic that implements a multiply operation.
     *
     * @param firstOperand  first operand
     * @param secondOperand second operand
     * @return outcome of multiply operation.
     */
    @Override
    public BigDecimalOperand apply(BigDecimalOperand firstOperand, BigDecimalOperand secondOperand) {
        return new BigDecimalOperand(firstOperand.getValue().multiply(secondOperand.getValue()));
    }
}
