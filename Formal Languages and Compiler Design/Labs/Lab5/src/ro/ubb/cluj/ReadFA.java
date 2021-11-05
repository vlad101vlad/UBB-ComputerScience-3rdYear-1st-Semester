package ro.ubb.cluj;

import ro.ubb.cluj.domain.FAModel;
import ro.ubb.cluj.domain.Transition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReadFA {
    private final String fileName;


    public ReadFA(String fileName) {
        this.fileName = fileName;
    }

    public FAModel readInformation() throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        String initialState = "";
        List<String> finalStates = new ArrayList<>();
        List<Transition> transitions = new ArrayList<>();


        int lineCounter = 0;
        while(scanner.hasNextLine()){
            if(lineCounter == 0){
                initialState = scanner.nextLine();
            }
            if(lineCounter == 1){
                String[] f_states = scanner.nextLine().split("\\s+");
                Arrays.stream(f_states).forEach(state -> finalStates.add(state));
            }
            if(lineCounter > 1){
                String[] transitionElements = scanner.nextLine().split("\\s+");
                if(transitionElements.length == 3){
                    Transition transition = new Transition(
                            transitionElements[0], transitionElements[1], transitionElements[2]
                    );
                    transitions.add(transition);
                }
            }

            lineCounter++;
        }

        FAModel faModel = new FAModel(initialState, finalStates, transitions);
        return faModel;
    }
}
