package au.com.nab.calc.view;

import java.io.PrintStream;

/**
 * Simple subclass of {@link Display} implemented a text based display.
 * This implementation writes whatever {@link au.com.nab.calc.Calculator} passes to it, to a {@link PrintStream}.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public class SimpleDisplay extends Display {

    private PrintStream out;

    /**
     * Constructor that creates an instance of {@link Display} backed with a {@link PrintStream}.
     *
     * @param printStream a {@link PrintStream} that {@link au.com.nab.calc.Calculator} results will be written to
     */
    public SimpleDisplay(PrintStream printStream) {
        this.out = printStream;
    }

    @Override
    public void updateDisplay(String result) {
        out.println(result);
    }
}
