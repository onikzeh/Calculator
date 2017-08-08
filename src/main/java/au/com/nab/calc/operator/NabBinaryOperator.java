package au.com.nab.calc.operator;

import au.com.nab.calc.model.Entry;
import au.com.nab.calc.model.Operand;

import java.util.function.BinaryOperator;

/**
 * Parent class for operators which need two operands. An example would be a plus or multiply.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public abstract class NabBinaryOperator<T extends Operand> extends Operator implements BinaryOperator<T>, Entry {

    /**
     * Defines precedence in calculation. For example precedence of a multiply is higher than a plus in calculations.
     */
    private final Precedence precedence;

    /**
     * The constructor which requires a symbol for this operator and its precedence.
     *
     * @param symbol     the symbol representing this operation.
     * @param precedence the precedence in calculation.
     */
    NabBinaryOperator(String symbol, Precedence precedence) {
        super(symbol);
        this.precedence = precedence;
    }

    /**
     * Returns the precedence in calculation.
     *
     * @return the precedence in calculation.
     */
    public Precedence getPrecedence() {
        return precedence;
    }

    /**
     * Enum for defining precedence in calculation.
     */
    public enum Precedence {
        HIGH, LOW
    }
}
