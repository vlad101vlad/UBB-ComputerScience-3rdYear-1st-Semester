package ro.ubb.cluj.menu;

import ro.ubb.cluj.domain.FAModel;

import java.util.Scanner;

public class Menu {
    private final FAModel faModel;

    public Menu(FAModel faModel) {
        this.faModel = faModel;
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
                "4. The set of final states\n\n";

        return description;
    }
}
