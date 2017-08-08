package au.com.nab.calc.operator;

import au.com.nab.calc.model.BigDecimalOperand;
import au.com.nab.calc.model.Operand;

import java.math.BigDecimal;

/**
 * An {@link Operator} supporting Minus math operation for {@link BigDecimal} numbers.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public class Minus extends NabBinaryOperator<Operand<BigDecimal>> {

    private static final Minus INSTANCE = new Minus();

    /**
     * Make this private to limit creating a new instance for each Operand.
     */
    private Minus() {
        super("-", Precedence.LOW);
    }

    /**
     * Returns an instance of this class supporting minus functionality.
     *
     * @return an instance of this class.
     */
    public static Minus instance() {
        return INSTANCE;
    }

    /**
     * THe logic that implements a subtract operation.
     *
     * @param firstOperand  first operand
     * @param secondOperand second operand
     * @return outcome of subtract operation.
     */
    @Override
    public Operand<BigDecimal> apply(Operand<BigDecimal> firstOperand, Operand<BigDecimal> secondOperand) {
        return new BigDecimalOperand(firstOperand.getValue().subtract(secondOperand.getValue()));
    }

}
