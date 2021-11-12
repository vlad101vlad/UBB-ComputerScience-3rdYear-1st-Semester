package ro.ubb.cluj.menu;

import ro.ubb.cluj.domain.FAModel;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final FAModel faModel;
    private final List<List<String>> sequenceList;

    public Menu(FAModel faModel, List<List<String>> sequenceList) {
        this.faModel = faModel;
        this.sequenceList = sequenceList;
    }

    public FAModel getFaModel() {
        return faModel;
    }

    public void runMenu(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println(getMenuDescription());

            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    faModel.getSetOfStates().forEach(System.out::println);
                    break;
                case 2:
                    faModel.getAlphabet().forEach(System.out::println);
                    break;
                case 3:
                    faModel.getTransitionList().forEach(System.out::println);
                    break;
                case 4:
                    faModel.getFinalStates().forEach(System.out::println);
                    break;
                case 5:
                    for(List<String> sequence: this.sequenceList){
                        System.out.print("The sequence ");
                        sequence.forEach(string -> System.out.print(string + " "));
                        System.out.print(" is ");
                        boolean result =
                                faModel.isSequenceAcceptedByFa(0, sequence, this.faModel.getInitialState());
                        if(result)
                            System.out.println(" ACCEPTED by the FA.");
                        else
                            System.out.println(" NOT ACCEPTED by the FA");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private final String getMenuDescription(){
        String description = "";
        description += "Press a key for an option:\n" +
                "1. Set of states\n" +
                "2. The alphabet\n" +
                "3. The transitions\n" +
                "4. The set of final states\n" +
                "5. Check if sequence is accepted by the FA\n\n";

        return description;
    }
}
