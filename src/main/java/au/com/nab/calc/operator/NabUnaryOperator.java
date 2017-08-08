package au.com.nab.calc.operator;

import au.com.nab.calc.model.Entry;
import au.com.nab.calc.model.Operand;

import java.util.function.UnaryOperator;

/**
 * Parent class for operators which need one operand. An example would be a square root.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public abstract class NabUnaryOperator<T extends Operand> extends Operator implements UnaryOperator<T>, Entry {

    /**
     * The constructor which requires a symbol for this operator.
     *
     * @param symbol the symbol representing this operation.
     */
    NabUnaryOperator(String symbol) {
        super(symbol);
    }

}
