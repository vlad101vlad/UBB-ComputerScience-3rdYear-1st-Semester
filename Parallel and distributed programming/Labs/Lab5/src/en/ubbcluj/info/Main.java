package en.ubbcluj.info;

import en.ubbcluj.info.domain.Polynomial;
import en.ubbcluj.info.service.PolynomialOperations;
import en.ubbcluj.info.util.DummyDataGenerator;
import en.ubbcluj.info.util.TestMethods;

import java.util.AbstractMap;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
	// write your code here
//        Polynomial polynomialA = new Polynomial(5, DummyDataGenerator.generateCoefficientGrade5());
        Polynomial polynomialA = DummyDataGenerator.generatePolynomialOfGrade(140);
        System.out.println("PolynomialA: " + polynomialA);
        Polynomial polynomialB = DummyDataGenerator.generatePolynomialOfGrade(155);
        System.out.println("PolynomialB: " + polynomialB);

        TestMethods testMethods = new TestMethods();
        testMethods.testAll();



        long start = System.currentTimeMillis();
        Polynomial sequentialProduct = PolynomialOperations.multiplySequencially(polynomialA, polynomialB);
        System.out.println("Sequencially multiplication: " + sequentialProduct);
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("---done in: " + elapsedTime);

        System.out.println("\n\n");
        start = System.currentTimeMillis();
        Polynomial karastzubaSequencially = PolynomialOperations.multiplyKaratsuba(polynomialA, polynomialB);
        System.out.println("Karatsuba multiplication: " + karastzubaSequencially);
        end = System.currentTimeMillis();
        elapsedTime = end - start;
        System.out.println("---done in: " + elapsedTime);

        System.out.println("\n\n");
        AbstractMap.SimpleEntry<Long, Polynomial> simpleEntry = PolynomialOperations.multiplySequenciallyParallel(polynomialA,
            polynomialB);
        elapsedTime = simpleEntry.getKey();
        Polynomial multiplyParallel = simpleEntry.getValue();
        System.out.println("Parallel multiplication: " + multiplyParallel);
        System.out.println("---done in: " + elapsedTime);


        System.out.println("\n\n");
        start = System.currentTimeMillis();
        Polynomial karatsubaParallel = PolynomialOperations.multiplyKaratsubaParallel(1, polynomialA, polynomialB);
        end = System.currentTimeMillis();
        elapsedTime = end-start;
        System.out.println("Parallel karatsuba: " + karatsubaParallel);
        System.out.println("---done in: " + elapsedTime);

        System.out.println("end of program");


    }
}
