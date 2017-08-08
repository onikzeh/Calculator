package au.com.nab.calc.model;

/**
 * The base class holding implementation for different types of operands. An operand can be based on any class extending {@link Number}.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public class Operand<E extends Number> implements Entry {

    /**
     * The actual value.
     */
    private final E value;

    /**
     * Constructor by value.
     *
     * @param value the actual value.
     */
    public Operand(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

}
