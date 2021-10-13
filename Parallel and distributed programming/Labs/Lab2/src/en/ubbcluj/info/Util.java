package en.ubbcluj.info;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Util {
    public static Vector generateVectorData(int dimension){
        Vector vector = new Vector();
        int MIN_RANDOM = 1;
        int MAX_RANDOM = 20;

        List<Integer> parameters = new ArrayList<>();
        for(int index = 1; index <= dimension; index++){
            int randomParameter = (int) (Math.random() * (MAX_RANDOM - MIN_RANDOM) + MIN_RANDOM);
            parameters.add(randomParameter);
        }

        vector.setElements(parameters);
        return vector;
    }
}
