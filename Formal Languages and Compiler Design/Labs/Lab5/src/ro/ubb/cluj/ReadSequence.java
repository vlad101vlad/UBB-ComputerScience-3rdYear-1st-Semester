package ro.ubb.cluj;

import ro.ubb.cluj.domain.Transition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReadSequence {
    public static String fileName = "src/ro/ubb/cluj/input/seq.in";

    public static List<List<String>> readSequence() throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        List<List<String>> sequenceList = new ArrayList<>();


        while (scanner.hasNextLine()) {
                List<String> newSequence = new ArrayList<>();
                String[] sequence = scanner.nextLine().split("\\s+");
                Arrays.stream(sequence).forEach(state -> newSequence.add(state));
                sequenceList.add(newSequence);
        }
        scanner.close();
        return sequenceList;
    }
}
