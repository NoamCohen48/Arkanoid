package tools;

import java.util.Random;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class Tools {

    private static final double EP = 0.0001;

    /**
     * compare 2 double numbers.
     *
     * @param a first number.
     * @param b second number.
     * @return 0 if are equal (to epsilon accuracy),
     * 1 if a > b and -1 if a < b.
     */
    public static int compare(double a, double b) {
        // equals
        if (Math.abs(a - b) <= EP) {
            return 0;
        }
        // a is bigger;
        if (a > b) {
            return 1;
        }
        // b is bigger
        return -1;
    }

    /**
     * returns a random number between a and b.
     *
     * @param a    lower bound.
     * @param b    higher bound.
     * @param rand random instance.
     * @return a random int.
     */
    public static int randomIntBound(int a, int b, Random rand) {
        return rand.nextInt(b - a) + a;
    }

    /**
     * compare 2 double numbers.
     *
     * @param a  first number.
     * @param b  second number.
     * @param ep the accuracy.
     * @return 0 if are equal (to epsilon accuracy),
     * 1 if a > b and -1 if a < b.
     */
    public int compare(double a, double b, double ep) {
        // equals
        if (a - b < ep) {
            return 0;
        }
        // a is bigger;
        if (a > b) {
            return 1;
        }
        // b is bigger
        return -1;
    }
}
