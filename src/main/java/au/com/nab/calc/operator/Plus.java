package au.com.nab.calc.operator;

import au.com.nab.calc.model.BigDecimalOperand;

import java.math.BigDecimal;

/**
 * An {@link Operator} supporting Plus math operation for {@link BigDecimal} numbers.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public class Plus extends NabBinaryOperator<BigDecimalOperand> {

    private static final Plus INSTANCE = new Plus();

    /**
     * Make this private to limit creating a new instance for each Operand.
     */
    private Plus() {
        super("+", Precedence.LOW);
    }

    /**
     * Returns an instance of this class supporting plus functionality.
     *
     * @return an instance of this class.
     */
    public static Plus instance() {
        return INSTANCE;
    }

    /**
     * THe logic that implements a plus operation.
     *
     * @param firstOperand  first operand
     * @param secondOperand second operand
     * @return outcome of plus operation.
     */
    @Override
    public BigDecimalOperand apply(BigDecimalOperand firstOperand, BigDecimalOperand secondOperand) {
        return new BigDecimalOperand(firstOperand.getValue().add(secondOperand.getValue()));
    }
}
