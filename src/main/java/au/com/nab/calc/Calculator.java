package au.com.nab.calc;

import au.com.nab.calc.model.Entry;
import au.com.nab.calc.model.Operand;
import au.com.nab.calc.operator.NabBinaryOperator;
import au.com.nab.calc.operator.NabUnaryOperator;
import au.com.nab.calc.operator.Operator;
import au.com.nab.calc.util.CalculatorHelper;
import au.com.nab.calc.view.Display;
import com.sun.javafx.binding.StringFormatter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Observable;
import java.util.StringJoiner;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static au.com.nab.calc.Constants.REGEX_OPERAND_SEPARATOR;
import static au.com.nab.calc.operator.NabBinaryOperator.Precedence;

/**
 * The main class that actually calculates operands based on provided operations. An instance of this class must be built using the
 * {@link Calculator.Builder} class.
 * <p>
 * Providing operands and operations can be done via two different approaches.
 * <p>
 * 1- By providing a String containing operands and operations. Calculator parses this {@link String} and matches each
 * {@link Operator} using its provided {@link Operator#symbol}.
 * <code>
 * cal
 * </code>
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public class Calculator<T extends Operand<?>> extends Observable {

    /**
     * A logger JUST used to log steps done for calculating result.
     */
    private static final Logger LOGGER = Logger.getLogger(Calculator.class.getName());

    /**
     * A {@link List} holding a stream of operands and operators.
     */
    private final List<Entry> entryStack;

    /**
     * A regex for validating input expressions created based on type of {@link Operand}s available.
     */
    private final Pattern validatorRegex;

    /**
     * A map used to find operators using their {@link Operator#symbol}.
     */
    private final Map<String, Operator> operators = new HashMap<>();

    /**
     * Constructor that uses a {@link Builder} to create a {@link Calculator}
     *
     * @param builder containing configuration for creating a {@link Calculator}
     */
    private Calculator(Builder<T> builder) {
        addObserver(builder.display);

        this.entryStack = new LinkedList<>();

        // Sample of Regex for validating based on +, -, *, / and sqrt: "-?\\d*(\\s*[-+/*sqrt]\\s*-?\\d*(?:\\.\\d+)?)+"
        StringJoiner regexJoiner = new StringJoiner("", "-?\\d*(\\s*[", "]\\s*-?\\d*(?:\\.\\d+)?)+");

        // Creating a regex for validating inputs based on provided operators.
        builder.operators
                .forEach(x -> {
                            try {
                                Operator operator = ((Operator) x.getMethod("instance").invoke(null));
                                String symbol = operator.getSymbol();
                                this.operators.put(symbol, operator);
                                regexJoiner.add(symbol);
                            } catch (Exception e) {
                                throw new RuntimeException("Failed to instantiate Operator", e);
                            }
                        }
                );
        validatorRegex = Pattern.compile(regexJoiner.toString());
    }

    /**
     * Adds an {@link Entry} to calculator. This entry can be an operand like a number or an operation like a Multiply(*)
     *
     * @param entry the operand or operation.
     */
    public void addEntry(Entry entry) {
        entryStack.add(entry);
    }

    /**
     * Clears the state of the {@link Calculator}. Removes all entries in calculator stack
     */
    public void clear() {
        entryStack.clear();
    }

    /**
     * Used for running Binary operator fund
     *
     * @param operator the operator
     * @param operand1 first operand
     * @param operand2 second operand
     * @return the result
     */
    private T calculate(BinaryOperator<T> operator, T operand1, T operand2) {
        return operator.apply(operand1, operand2);
    }

    /**
     * Used for running Unary operator fund
     *
     * @param operator the operator
     * @param operand  the operand
     * @return the result
     */
    private T calculate(UnaryOperator<T> operator, T operand) {
        return operator.apply(operand);
    }

    /**
     * Accepts a {@link String} containing operands and {@link Operator#symbol}s, parses and calculates the result using the operands
     * provided.
     *
     * @param mathExpression a {@link String} containing operands and {@link Operator#symbol}s
     * @return the result of calculation.
     * @throws CalculatorException if an error happens during calculation.
     */
    public T calculate(String mathExpression) throws CalculatorException {
        clear();
        feedExpression(mathExpression);
        return calculate();
    }

    /**
     * Walks through the entry stack and calculates the result.
     *
     * @return the result of calculation.
     * @throws CalculatorException if an error happens during calculation.
     */
    @SuppressWarnings("unchecked") // The only left entry will be a T
    public T calculate() throws CalculatorException {
        try {
            LOGGER.finer(entryStack.stream().map(Object::toString)
                    .collect(Collectors.joining(" ")));

            // Walk through all unary operations and solve them
            while (CalculatorHelper.containsOperation(entryStack, NabUnaryOperator.class)) {
                calculateUnaryOperations();
                LOGGER.finer(entryStack.stream().map(Object::toString)
                        .collect(Collectors.joining(" ")));
            }

            // Walk through all binary operations with high precedence and solve them
            while (CalculatorHelper.containsOperation(entryStack, NabBinaryOperator.class, Precedence.HIGH)) {
                calculateBinaryOperations(Precedence.HIGH);
                LOGGER.finer(entryStack.stream().map(Object::toString)
                        .collect(Collectors.joining(" ")));
            }

            // Walk through all binary operations with low precedence and solve them
            while (CalculatorHelper.containsOperation(entryStack, NabBinaryOperator.class, Precedence.LOW)) {
                calculateBinaryOperations(Precedence.LOW);
                LOGGER.finer(entryStack.stream().map(Object::toString)
                        .collect(Collectors.joining(" ")));
            }

            setChanged();
            notifyObservers(entryStack.get(0));
        } catch (ArithmeticException | NumberFormatException e) {
            throw new CalculatorException(StringFormatter.format("Can not continue calculation because of error: '%s'."
                    , e.getMessage()).getValue());
        }
        return (T) entryStack.get(0);
    }


    @SuppressWarnings("unchecked") // Hopefully we know what we are doing ;)
    private void calculateBinaryOperations(Precedence precedence) {
        for (ListIterator<Entry> iter = entryStack.listIterator(); iter.hasNext(); ) {
            Entry entry = iter.next();
            if (entry instanceof NabBinaryOperator
                    && precedence.equals(((NabBinaryOperator) entry).getPrecedence())) {
                iter.remove();                          // Remove Operator
                T firstOperand = (T) iter.previous();   // Get first operand
                iter.remove();                          // Remove first operand
                iter.set(calculate((NabBinaryOperator) entry, firstOperand, (T) iter.next()));
            }
        }
    }

    @SuppressWarnings("unchecked") // Hopefully we know what we are doing ;)
    private void calculateUnaryOperations() {
        for (ListIterator<Entry> iter = entryStack.listIterator(); iter.hasNext(); ) {
            Entry entry = iter.next();
            if (entry instanceof NabUnaryOperator) {
                iter.previous();
                iter.remove();
                iter.set(calculate((NabUnaryOperator) entry, (T) iter.next()));

            }
        }
    }

    /**
     * Sets up the expression for calculator to process.
     *
     * @param mathExpression the expression containing operands and operators.
     * @throws CalculatorException if any error happens during the execution.
     */
    public void feedExpression(String mathExpression) throws CalculatorException {

        mathExpression = mathExpression.trim();

        if (!validatorRegex.matcher(mathExpression).matches()) {
            throw new CalculatorException("Expression is not valid. The expression must match '"
                    + "<Operand> <Operator> <Operand> ... Ex: 1 + 2 * -1'");
        }

        clear();

        // Pars whole expression and convert each element to appropriate entry model
        List<String> entries = new LinkedList<>(
                Arrays.asList(
                        mathExpression.split(REGEX_OPERAND_SEPARATOR)));
        try {
            entries.stream()
                    .map(String::trim)
                    .filter(x -> x.length() != 0)
                    .forEach(x ->
                            this.addEntry(operators.containsKey(x) ? (Entry) operators.get(x) : CalculatorHelper.getOperand(x))
                    );
        } catch (IllegalArgumentException e) {
            throw new CalculatorException("Expression is not valid. The expression must match '"
                    + "<Operand> <Operator> <Operand> ... Ex: 1 + 2 * -1'");
        }
    }

    /**
     * A builder for creating an instance of {@link Calculator}.
     *
     * @param <T> type of operands supported by created calculator.
     */
    public static final class Builder<T extends Operand<?>> {

        private Display display;

        private List<Class<? extends Operator>> operators;

        private Builder() {
            operators = new LinkedList<>();
        }

        /**
         * Start of builder.
         *
         * @param display an instance of {@link Display} providing functionality for displaying calculation results.
         * @param <T>     type of operands supported by created calculator.
         * @return and instance of this builder to follow the chain.
         */
        static public <T extends Operand<?>> Calculator.Builder<T> withDisplay(Display display) {
            Calculator.Builder<T> builder = new Calculator.Builder<>();
            builder.display = Objects.requireNonNull(display);
            return builder;
        }

        /**
         * Adds a supported operator to created calculator.
         *
         * @param operator an operator to be supported by this Calculator.
         * @return
         */
        public Builder<T> withOperator(Class<? extends Operator> operator) {
            operators.add(Objects.requireNonNull(operator));
            return this;
        }

        /**
         * Builds an instance of {@link Calculator}.
         *
         * @return an instance of {@link Calculator}.
         */
        public Calculator<T> build() {
            if (operators.size() == 0) {
                throw new NullPointerException("Define at least one operator.");
            }
            return new Calculator<>(this);
        }
    }
}
