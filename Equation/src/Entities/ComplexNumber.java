package Entities;
/**
 * The {@code ComplexNumber} class represents complex number
 * and includes methods, performing basical mathematical operations:
 * add, subtract, multiply, divide and etc.
 */
public class ComplexNumber {
    /**
     * real part of Complex Number
     */
    private final double re;
    /**
     * imaginary part of Complex Number
     */
    private final double im;
    /**
     * Getter for real part
     * @return re
     */
    public double getRe() {
        return re;
    }
    /**
     * Getter for imaginary part
     * @return im
     */
    public double getIm() {
        return im;
    }

    /**
     * Initializes a newly created {@code ComplexNumber} object with 0
     */
    public ComplexNumber() {
        this.re = 0;
        this.im = 0;
    }
    /**
     * Initializes a newly created {@code ComplexNumber} object with
     * parameters
     * @param re
     *          real part of Complex Number
     * @param im
     *          imaginary part of Complex Number
     */
    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }
    /**
     * Initializes a newly created {@code ComplexNumber} object with
     * copy of parameter
     * @param c
     *          Complex Number to clone
     */
    public ComplexNumber(ComplexNumber c) {
        this.re = c.re;
        this.im = c.im;
    }
    /**
     * Returns the module of complex number
     * @return double value of mod
     */
    public double mod() {
        return re * re + im * im;
    }
    /**
     * Returns the argument of complex number
     * @return double value of argument
     */
    public double arg() {
        return Math.atan2(re, im);
    }
    /**
     * Returns the conjugate complex number
     * @return conjugate ComplexNumber
     */
    public ComplexNumber conj() {
        return new ComplexNumber(re, -im);
    }
    /**
     * Returns the opposite complex number
     * @return opposite ComplexNumber
     */
    public ComplexNumber neg() {
        return new ComplexNumber(-re, -im);
    }
    /**
     * Returns the sum of complex numbers
     * @param x
     *        second argument of sum
     * @return sum
     */
    public ComplexNumber add(ComplexNumber x) {
        return new ComplexNumber(this.re + x.re, this.im + x.im);
    }
    /**
     * Returns the subtract of complex numbers
     * @param x
     *        second argument of subtract
     * @return subtact
     */
    public ComplexNumber sub(ComplexNumber x) {
        return new ComplexNumber(this.re - x.re, this.im - x.im);
    }
    /**
     * Returns the composition of complex numbers
     * @param b
     *        second argument of composition({@code ComplexNumber})
     * @return composition
     */
    public ComplexNumber mul(ComplexNumber b) {
        return new ComplexNumber(re * b.re - im * b.im, re * b.im + im * b.re);
    }
    /**
     * Returns the composition of complex numbers
     * @param b
     *        second argument of composition({@code double})
     * @return composition
     */
    public ComplexNumber mul(double b) {
        return new ComplexNumber(re * b, im * b);
    }
    /**
     * Returns the division of complex numbers
     * @param b
     *        second argument of division
     * @return division
     */
    public ComplexNumber div(ComplexNumber b) {
        return this.mul(b.conj()).mul(1 / b.mod());
    }
    /**
     * Returns the array of square roots of Complex Number
     * @return 2 square roots
     */
    public ComplexNumber[] sqrt() {
        ComplexNumber[] res = new ComplexNumber[2];
        double r = Math.sqrt(this.mod());
        double theta = this.arg() / 2;
        res[0] = new ComplexNumber(r * Math.cos(theta),r * Math.sin(theta));
        res[1] = new ComplexNumber(r * Math.cos(theta + Math.PI),r * Math.sin(theta + Math.PI));
        return res;
    }

    @Override
    public String toString() {
        return re + " + " + im + "i";
    }
}