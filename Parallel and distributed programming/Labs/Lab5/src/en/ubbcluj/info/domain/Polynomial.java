package en.ubbcluj.info.domain;

import java.util.List;

public class Polynomial {
    private final int grade;
    private final List<Integer> coefficients;

    public Polynomial(int grade, List<Integer> coefficients) {
        this.grade = grade;
        this.coefficients = coefficients;
    }

    @Override
    public String toString() {
        StringBuilder toBePrinted = new StringBuilder();

        for(int index = 0; index < this.grade; index++){
            Integer coefficientAbsoluteValue = this.getCoefficients().get(index);
            String coefficient = coefficientAbsoluteValue > 0 ?
                " +" + String.valueOf(coefficientAbsoluteValue) : " -" + String.valueOf(coefficientAbsoluteValue);

            String powerString = index > 0 ?
                "x^" + String.valueOf(index) : "";

            toBePrinted.append(coefficient).append(powerString);
        }

        return toBePrinted.toString();
    }

    public int getGrade() {
        return grade;
    }

    public List<Integer> getCoefficients() {
        return coefficients;
    }
}
