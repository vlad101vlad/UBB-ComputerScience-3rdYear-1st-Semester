package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {
    public static List<Integer> generateVectorElements(int VECTOR_SIZE){
        List<Integer> generatedVector = new ArrayList<>();

        for(int index = 1; index <= VECTOR_SIZE; index++){
            int randomValue = new Random().nextInt(20);
            generatedVector.add(randomValue);
        }

        return generatedVector;
    }
}
