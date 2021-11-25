package ro.ubb.cluj.service;

import ro.ubb.cluj.domain.GrammarModel;
import ro.ubb.cluj.domain.Production;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final GrammarModel grammarModel;

    public Menu(GrammarModel grammarModel) {
        this.grammarModel = grammarModel;
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);

        while (true){
            printMenu();

            int choice = scanner.nextInt();
            System.out.println("\n");

            switch (choice){
                case 1:
                    grammarModel.getNonTerminals().forEach(nonTerminal -> System.out.print(nonTerminal + " "));
                    break;
                case 2:
                    grammarModel.getTerminals().forEach(terminal -> System.out.print(terminal + " "));
                    break;
                case 3:
                    grammarModel.getProductions().forEach(System.out::println);
                    break;
                case 4:
                    try{
                        System.out.println("~Insert non-terminal: ");
                        String nonTerminal = scanner.next();

                        List<Production> productionList = grammarModel.getProductionsForNonterminal(nonTerminal);
                        System.out.println("--The productions for the " + nonTerminal + " non terminal are:");
                        productionList.forEach(production -> System.out.println("\t" + production));
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("~Invalid choice!~\n");
                    break;
            }
        }
    }


    private void printMenu(){
        String printMessage = "";
        printMessage += "\n\n";

        printMessage += "1. Set of non terminals\n";
        printMessage += "2. Set of terminals \n";
        printMessage += "3. Set of productions \n";
        printMessage += "4. Productions for a given non terminal\n";
        printMessage += "0 -> Close the app\n";

        printMessage += "\n\n";

        System.out.println(printMessage);
    }
}
