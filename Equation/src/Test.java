import Entities.ComplexNumber;
import Entities.Fraction;
import Equations.*;

import java.util.Random;
/**
 * The class {@code Test} demonstrates capabilities of
 * {@code QuadraticEquation, ComplexQuadraticEquation and FractureQuadraticEquation} classes
 */
public class Test {
    /**
     * Object of class {@code Random} that is used for coefficients generation
     */
    private static final Random rand = new Random();

    private static double rangeRandom(double rangeMin, double rangeMax) {
        return rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
    }
    /**
     * That constant defines the size of test array
     */
    private static final int VOLUME = 15;

    public static void main(String[] args) {
        QuadraticEquation[] arrQE = generateEquations(VOLUME);
        double minimalRoot = Double.POSITIVE_INFINITY;
        double maximalRoot = Double.NEGATIVE_INFINITY;
        System.out.println("Generated equations:");
        for (QuadraticEquation qe : arrQE) {
            System.out.println(qe);
            for (double root : qe.findRoots()) {
                if (root < minimalRoot)
                    minimalRoot = root;
                if (root > maximalRoot)
                    maximalRoot = root;
            }
        }
        System.out.println("\nMinimal root: " + minimalRoot + "\nMaximal root: " + maximalRoot);

        testFractionEquation();
        testComplexEquation();
    }
    /**
     * Generates the array of Quadratic equations with random coefficients
     * @param volume
     *         the number of equations in array
     * @return array of random quadratic equations
     */
    public static QuadraticEquation[] generateEquations(int volume) {
        QuadraticEquation[] res = new QuadraticEquation[volume];
        for (int i = 0; i < volume; i++) {
            res[i] = new QuadraticEquation(rangeRandom(-10, 10),
                    rangeRandom(-10, 10), rangeRandom(-10, 10));
        }
        return res;
    }
    /**
     * This method tests capabilities of {@code FractionQuadraticEquation} class
     */
    public static void testFractionEquation(){
        System.out.println("\nExample of Fraction Quadratic Equation:");
        FractionQuadraticEquation fqe = new FractionQuadraticEquation(new Fraction(rand.nextInt(1000) - 500, rand.nextInt(1000)),
                new Fraction(rand.nextInt(1000) - 500, rand.nextInt(1000)),
                new Fraction(rand.nextInt(1000) - 500, rand.nextInt(1000)));
        System.out.println(fqe + "\nRoots:");
        for(double root: fqe.findRoots()) {
            System.out.println(root);
        }
        System.out.println("Extremum: " + fqe.findExtremum());
        System.out.println("Interval of increasing: (" + fqe.findIncreasingInterval()[0] +
                "; " + fqe.findIncreasingInterval()[1] + ")");
        System.out.println("Interval of decreasing: (" + fqe.findDecreasingInterval()[0] +
                "; " + fqe.findDecreasingInterval()[1] + ")");
    }
    /**
     * This method tests capabilities of {@code ComplexQuadraticEquation} class
     */
    public static void testComplexEquation(){
        System.out.println("\nExample of Complex Quadratic Equation:");
        ComplexQuadraticEquation cqe = new ComplexQuadraticEquation(
                new ComplexNumber(rangeRandom(-10, 10), rangeRandom(-10, 10)),
                new ComplexNumber(rangeRandom(-10, 10), rangeRandom(-10, 10)),
                new ComplexNumber(rangeRandom(-10, 10), rangeRandom(-10, 10)));
        System.out.println(cqe + "\nRoots:");
        for(ComplexNumber root: cqe.findRoots()) {
            System.out.println(root);
        }
        System.out.println("Extremum: " + cqe.findExtremum());
    }
}
