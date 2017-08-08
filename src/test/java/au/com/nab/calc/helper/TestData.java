package au.com.nab.calc.helper;

import java.util.Arrays;
import java.util.List;

/**
 * Provides test data for tets.
 *
 * @author <a href="mailto:kevin.onik@auspost.com.au">Kevin Onik</a>
 * @version 1.0 created on 30/7/17
 */
public final class TestData {

    public static List<Object[]> TEST_DATA_MINUS = Arrays.asList(new Object[][]{
            {"10 - -5", 15},
            {"-10 - 5", -15},
            {"-1 - -1", 0},
            {"-1 - 0", -1},
            {"0 - 0", 0},
            {"5 - 2", 3},
            {"5 - 2 - 3", 0},
            {"5 - 2 - 3 - 5", -5},
            {"5 - 2 - 3 - 5 - 10", -15},
            {"5 - 2 - 3 - 5 - 10 - -20", 5}
    });
    public static List<Object[]> TEST_DATA_PLUS = Arrays.asList(new Object[][]{
            {"1 + 1", 2},
            {"-1 + 1", 0},
            {"-1 + -1", -2},
            {"0 + 0", 0},
            {"0 + -1", -1},
            {"5 + 2", 7},
            {"5 + 2 + 3", 10},
            {"5 + 2 + 3 + 5", 15},
            {"5 + 2 + 3 + 5 + 10", 25},
            {"5 + 2 + 3 + 5 + 10 + -20", 5}
    });
    public static List<Object[]> TEST_DATA_MULTIPLY = Arrays.asList(new Object[][]{
            {"10 * -5", -50},
            {"-10 * 5", -50},
            {"-1 * -1", 1},
            {"-1 * 0", 0},
            {"0 * 0", 0},
            {"5 * 2", 10},
            {"5 * 2 * 3", 30},
            {"5 * 2 * 3 * 5", 150},
            {"5 * 2 * 3 * 5 * 10", 1500},
            {"5 * 2 * 3 * 5 * 10 * -20", -30000}
    });
    public static List<Object[]> TEST_DATA_DIVIDE = Arrays.asList(new Object[][]{
            {"10 / -5", -2},
            {"-10 / 5", -2},
            {"-1 / -1", 1},
            {"-1 / 1", -1},
            {"0 / 1", 0},
            {"5 / 2", 2.5},
            {"50 / 2 / 5", 5},
            {"50 / 2 / 5 / 5", 1},
            {"50 / 2 / 5 / 5 / 10", 0.1},
            {"10 / 10 / 10 / 0.1 ", 1}
    });
    public static List<Object[]> TEST_DATA_SQRT = Arrays.asList(new Object[][]{
            {"sqrt 1", 1},
            {"sqrt 25", 5},
            {"sqrt 9", 3},
            {"sqrt 3", 1.7320508075688772}
    });
    public static List<Object[]> TEST_DATA_COMPLEX = Arrays.asList(new Object[][]{
            {"1 + 10 +  10 / -5 + 3 * 3 / 3 + sqrt 9", 15},
            {"-5 * 2 +  sqrt 25  +  10 / 2 * 3 + -10", 0},
            {"-1 + 1 + 10 / 10 - 0 - 0 + sqrt 9 * -1 + 2", 0},
            {"-1 / 1 +  1  *  1 + sqrt 1 * sqrt 1", 1},
            {"0 / 1 + 0 * 1 + 0    / -1", 0},
            {"1 - 1 + sqrt 64 - sqrt 64", 0},
            {"1 + 1 - 1 - 1 + 0 * 100 + 100 * 0", 0},
            {"10 * 10      -      sqrt 10000", 0},
            {"1 + 1.5 * -1 - 1.5", -2},
            {"-10 / 10 + 10 / -10 + 1 + 1 + -1 + 1 * -1 + 0 + 0 + sqrt 25 - sqrt 25 + 100 + 2", 100}
    });

    private TestData() {
    }

}
