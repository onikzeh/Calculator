package au.com.nab.calc.view;

import java.util.Observable;
import java.util.Observer;

/**
 * A base class for adding display functionality to {@link au.com.nab.calc.Calculator}. A subclass must be added to calculator while
 * building it by {@link au.com.nab.calc.Calculator.Builder#withDisplay(Display)}
 * <p>
 * Subclasses need to provide their own implementation for displaying the results.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public abstract class Display implements Observer {

    /**
     * This method will be called whenever {@link au.com.nab.calc.Calculator needs to display result}
     *
     * @param result the actual text sent by {@link au.com.nab.calc.Calculator} to be displayed.
     */
    public abstract void updateDisplay(String result);

    /**
     * This method is called whenever the observed object is changed.
     *
     * @param calculator the observable object.
     * @param result     an argument passed to the <code>notifyObservers</code>
     *                   method.
     */
    public void update(Observable calculator, Object result) {
        updateDisplay(result.toString());
    }
}
