package au.com.nab.calc.operator;

/**
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public class Operator {

    /**
     * Symbol that can be used to call the implemented operation.
     */
    private final String symbol;

    public Operator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
