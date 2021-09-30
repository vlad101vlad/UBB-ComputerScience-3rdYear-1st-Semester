package en.ubbcluj.info;

import java.util.List;

public class Main {

    public static void main(String[] args) {
//	// write your code here
//        DataGenerator.generateBankAccounts().forEach(account -> System.out.println(account));
//        DataGenerator.generateOperations().forEach(account -> System.out.println(account));
        List<Account> generatedAccounts = DataGenerator.generateBankAccounts();
        List<Operation> generatedOperations = DataGenerator.generateOperations();

        Market market = new Market(generatedAccounts, generatedOperations);
        market.run();

        generatedAccounts.forEach(System.out::println);


    }
}
