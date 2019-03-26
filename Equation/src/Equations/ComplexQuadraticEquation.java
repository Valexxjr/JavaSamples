package Equations;

import Entities.ComplexNumber;
/**
 * The class {@code ComplexQuadraticEquation} contains methods for performing
 * basical tasks with Quadratic equation with complex coefficients - finding roots
 * and the extremum
 * @author Alexander Valai
 */
public class ComplexQuadraticEquation {
    /**
     * complex coefficients of Quadratic equation
     */
    private ComplexNumber a, b, c;
    /**
     * Getter for first coefficient
     * @return a
     */
    public ComplexNumber getA() {
        return a;
    }
    /**
     * Getter for second coefficient
     * @return b
     */
    public ComplexNumber getB() {
        return b;
    }
    /**
     * Getter for third coefficient
     * @return c
     */
    public ComplexNumber getC() {
        return c;
    }

    /**
     * Initializes a newly created {@code ComplexQuadraticEquation} object so that
     * it represents a default ComplexQuadratic equation.
     */
    public ComplexQuadraticEquation() {
        a = new ComplexNumber(1, 0);
        b = new ComplexNumber();
        c = new ComplexNumber();
    }
    /**
     * Initializes a newly created {@code ComplexQuadraticEquation} object
     * with coefficients specified in parameters
     *
     * @param a first coefficient, <strong>not 0</strong>
     * @param b second coefficient
     * @param c third coefficient
     */
    public ComplexQuadraticEquation(ComplexNumber a, ComplexNumber b, ComplexNumber c) {
        this.a = new ComplexNumber(a);
        this.b = new ComplexNumber(b);
        this.c = new ComplexNumber(c);
    }
    /**
     * Finds the extremum of Complex Quadratic equation
     * @return extremum - complex number
     */
    public ComplexNumber findExtremum() {
        return b.neg().div(a.mul(2));
    }
    /**
     * Finds the roots of Complex Quadratic equation
     * @return array with roots of equation
     */
    public ComplexNumber[] findRoots() {
        ComplexNumber D = b.mul(b).sub(a.mul(c).mul(4));
        ComplexNumber results[] = new ComplexNumber[2];
        results[0] = (b.neg().add(D.sqrt()[0]).mul(0.5));
        results[1] = (b.neg().add(D.sqrt()[1]).mul(0.5));
        return results;
    }
    @Override
    public String toString() {
        return "Complex Quadratic Equation: a = " + a + " b = " + b + " c = " + c;
    }
}
