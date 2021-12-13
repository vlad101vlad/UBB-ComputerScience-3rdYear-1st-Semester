package en.ubbcluj.info.util;

import en.ubbcluj.info.domain.Polynomial;
import en.ubbcluj.info.service.PolynomialOperations;

public class TestMethods {
    public TestMethods() {
    }

    public void testAll(){
        testPolynomialOperation();
        testPolynomicalSequenciallyMultiplication();
        testMultiplyKaratsuba();
    }

    private void testPolynomialOperation(){
        Polynomial polynomialA = new Polynomial(5, DummyDataGenerator.generateCoefficientGrade5());
        Polynomial polynomialB = new Polynomial(3, DummyDataGenerator.generateCoefficientsGrade3());

        Polynomial polynomialC = PolynomialOperations.add(polynomialA, polynomialB);

        System.out.println("");
    }

    private void testPolynomicalSequenciallyMultiplication(){
        Polynomial polynomialA = new Polynomial(5, DummyDataGenerator.generateCoefficientGrade5());
        Polynomial polynomialB = new Polynomial(3, DummyDataGenerator.generateCoefficientsGrade3());

        Polynomial polynomialC = PolynomialOperations.multiplySequencially(polynomialA, polynomialB);

        System.out.println("");
    }

    private void testMultiplyKaratsuba(){
        Polynomial polynomialA = new Polynomial(5, DummyDataGenerator.generateCoefficientGrade5());
        Polynomial polynomialB = new Polynomial(3, DummyDataGenerator.generateCoefficientsGrade3());

        Polynomial polynomialC = PolynomialOperations.multiplyKaratsuba(polynomialA, polynomialB);

        System.out.println("");
    }
}
