package au.com.nab.calc.util;

import au.com.nab.calc.model.BigDecimalOperand;
import au.com.nab.calc.model.Entry;
import au.com.nab.calc.operator.NabBinaryOperator;
import au.com.nab.calc.operator.Operator;

import java.util.List;

/**
 * A utility class providing functionality required by {@link au.com.nab.calc.Calculator}.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public final class CalculatorHelper {

    /**
     * Utility class should not be instantiated.
     */
    private CalculatorHelper() {
    }

    /**
     * Walks through a {@link List} of {@link Entry}s and looks for an entry of type {@param operatorClass}.
     * If the {@param operatorClass} is of type {@link NabBinaryOperator}, then lookup can be narrowed down by
     * an optional {@link NabBinaryOperator.Precedence}.
     *
     * @param entryStack    a stack containing entries to search on.
     * @param operatorClass the operator class as a search condition to be looking for in stack
     * @param precedence    the precedence of an operator as an optional search condition to be looking for in stack
     * @return true if an entry found in the stack matching the search criteria and false otherwise.
     */
    public static boolean containsOperation(List<Entry> entryStack,
                                            Class<? extends Operator> operatorClass,
                                            NabBinaryOperator.Precedence... precedence) {
        return entryStack.stream()
                .filter(operatorClass::isInstance)
                .anyMatch(x ->
                        precedence.length == 0 || ((NabBinaryOperator) x).getPrecedence().equals(precedence[0])
                );
    }

    /**
     * Creates and returns an operand as an {@link Entry}. Creating the operand here makes {@link au.com.nab.calc.Calculator} decoupled
     * from specific {@link au.com.nab.calc.model.Operand}s.
     *
     * @param entry actual value of {@link au.com.nab.calc.model.Operand}
     * @return an {@link Entry} containing the {@link au.com.nab.calc.model.Operand}.
     */
    public static Entry getOperand(String entry) {
        return new BigDecimalOperand(entry);
    }
}
