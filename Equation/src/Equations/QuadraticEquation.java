package Equations;

/**
 * The class {@code QuadraticEquation} contains methods for performing
 * basical tasks with Quadratic equation: finding roots, extremums
 * and intervals of increasing and decreasing
 *
 * @author Alexander Valai
 */

public class QuadraticEquation {
    /**
     * coefficients of Quadratic equation
     */
    protected double a, b, c;
    /**
     * Getter for first coefficient
     * @return a
     */
    public double getA() {
        return a;
    }
    /**
     * Getter for second coefficient
     * @return b
     */
    public double getB() {
        return b;
    }
    /**
     * Getter for third coefficient
     * @return c
     */
    public double getC() {
        return c;
    }

    /**
     * Initializes a newly created {@code QuadraticEquation} object so that
     * it represents a default Quadratic equation.
     */
    public QuadraticEquation() {
        a = 1;
        b = 0;
        c = 0;
    }

    /**
     * Initializes a newly created {@code QuadraticEquation} object
     * with coefficients specified in parameters
     *
     * @param a first coefficient, <strong>not 0</strong>
     * @param b second coefficient
     * @param c third coefficient
     * @throws IllegalArgumentException if the first parameter is 0 - the equation will not be Quadratic
     */
    public QuadraticEquation(double a, double b, double c) throws IllegalArgumentException {
        if (a == 0)
            throw new IllegalArgumentException();
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Finds the roots of Quadratic equation in rational numbers
     *
     * @return array with roots of equation
     */
    public double[] findRoots() {
        double D = b * b - 4 * a * c;
        if (D > 0) {
            double results[] = new double[2];
            results[0] = (-b + Math.sqrt(D)) / 2;
            results[1] = (-b - Math.sqrt(D)) / 2;
            return results;
        } else if (D == 0) {
            double results[] = new double[1];
            results[0] = -b / 2;
            return results;
        } else
            return new double[0];
    }

    /**
     * Finds the extremum of Quadratic equation
     *
     * @return extremum
     */
    public double findExtremum() {
        return -b / 2 * a;
    }

    /**
     * Finds the interval of increasing
     *
     * @return the pair of {@code double} values: start and end of interval
     */
    public double[] findIncreasingInterval() {
        double interval[] = new double[2];
        if (a > 0) {
            interval[0] = this.findExtremum();
            interval[1] = Double.POSITIVE_INFINITY;
        } else {
            interval[0] = Double.NEGATIVE_INFINITY;
            interval[1] = this.findExtremum();
        }
        return interval;
    }

    /**
     * Finds the interval of decreasing
     *
     * @return the pair of {@code double} values: start and end of interval
     */
    public double[] findDecreasingInterval() {
        double interval[] = new double[2];
        if (a > 0) {
            interval[0] = Double.NEGATIVE_INFINITY;
            interval[1] = this.findExtremum();
        } else {
            interval[0] = this.findExtremum();
            interval[1] = Double.POSITIVE_INFINITY;
        }
        return interval;
    }

    @Override
    public String toString() {
        return "Quadratic Equation: a = " + a + " b = " + b + " c = " + c;
    }

}
