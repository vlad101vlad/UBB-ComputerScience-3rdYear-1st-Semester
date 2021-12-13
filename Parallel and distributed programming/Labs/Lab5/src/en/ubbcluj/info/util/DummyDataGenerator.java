package en.ubbcluj.info.util;

import en.ubbcluj.info.domain.Polynomial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DummyDataGenerator {
    public static List<Integer> generateCoefficientGrade5(){
        return new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
    }

    public static List<Integer> generateCoefficientsGrade3(){
        return new ArrayList<>(Arrays.asList(3, 2, 1));
    }

    public static Polynomial generatePolynomialOfGrade(int grade){
        Random random = new Random();

        List<Integer> coefficients = new ArrayList<>();
        for(int index = 0; index < grade; index++)
            coefficients.add(random.nextInt(20));
        return new Polynomial(grade, coefficients);
    }


}
