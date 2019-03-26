package Equations;

import Entities.Fraction;

/**
 * The class {@code FractionQuadraticEquation} contains methods for performing
 * basical tasks with Quadratic equation with fraction coefficients: finding roots, extremums
 * and intervals of increasing and decreasing
 * @author Alexander Valai
 */
public class FractionQuadraticEquation extends QuadraticEquation {
    /**
     * Fraction coefficients of Quadratic equation
     */
    private Fraction a_fraction, b_fraction, c_fraction;
    /**
     * Getter for first coefficient
     * @return a
     */
    public Fraction getA_fraction() {
        return a_fraction;
    }
    /**
     * Getter for second coefficient
     * @return b
     */
    public Fraction getB_fraction() {
        return b_fraction;
    }
    /**
     * Getter for third coefficient
     * @return c
     */
    public Fraction getC_fraction() {
        return c_fraction;
    }

    /**
     * Initializes a newly created {@code FractionQuadraticEquation} object so that
     * it represents a default Quadratic equation with fraction coefficients.
     */
    public FractionQuadraticEquation() {
        a_fraction = new Fraction();
        b_fraction = new Fraction();
        c_fraction = new Fraction();
        this.a = a_fraction.doubleValue();
        this.b = b_fraction.doubleValue();
        this.c = c_fraction.doubleValue();
    }
    /**
     * Initializes a newly created {@code FractionQuadraticEquation} object
     * with coefficients specified in parameters
     *
     * @param a first coefficient, <strong>not 0</strong>
     * @param b second coefficient
     * @param c third coefficient
     */
    public FractionQuadraticEquation(Fraction a, Fraction b, Fraction c) {
        a_fraction = new Fraction(a);
        b_fraction = new Fraction(b);
        c_fraction = new Fraction(c);
        this.a = a.doubleValue();
        this.b = b.doubleValue();
        this.c = c.doubleValue();
    }

    @Override
    public String toString() {
        return "Quadratic Equation with Fraction Coefficients: a = " + a_fraction.toString() +
                " b = " + b_fraction.toString() + " c = " + c_fraction.toString();
    }
}
