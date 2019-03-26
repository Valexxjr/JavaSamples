package Entities;

/**
 *  The {@code Fraction} class represents fraction
 *  with enumerator and denumenator
 */
public class Fraction {
    private long enumerator, denumenator;
    /**
     * Getter for enumerator of fraction
     * @return enumerator
     */
    public long getEnumerator() {
        return enumerator;
    }
    /**
     * Getter for denumenator of fraction
     * @return denumenator
     */
    public long getDenumenator() {
        return denumenator;
    }

    /**
     * Initializes a newly created {@code Fraction} object with value 0/1 = 0
     */
    public Fraction() {
        enumerator = 0;
        denumenator = 1;
    }
    /**
     * Initializes a newly created {@code Fraction} object with
     * parameters
     * @param enumerator
     *          enumerator of fraction
     * @param denumenator
     *          denumenator of fraction
     */
    public Fraction(long enumerator, long denumenator) {
        this.enumerator = enumerator;
        this.denumenator = denumenator;
    }
    /**
     * Initializes a newly created {@code Fraction} object with
     * copy of parameter
     * @param f
     *          fraction to clone
     */
    public Fraction(Fraction f) {
        this.enumerator = f.enumerator;
        this.denumenator = f.denumenator;
    }
    /**
     * Returns the result of enumerator divided by denumenator
     * @return double equivalent of fraction
     */
    public double doubleValue() {
        return (double) this.enumerator / this.denumenator;
    }

    @Override
    public String toString() {
        return enumerator + "/" + denumenator;
    }
}
