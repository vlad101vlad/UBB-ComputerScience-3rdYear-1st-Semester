package en.ubbcluj.info;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {
    public static List<Account> generateBankAccounts(){
        List<Account> generatedAccounts = new ArrayList<>();
        int maxAccounts = 10;

        for(int i = 1; i <= maxAccounts; i++)
            generatedAccounts.add(generateBankAccount(i));
        return generatedAccounts;
    }

    public static Account generateBankAccount(int accountID){
        int balance = ThreadLocalRandom.current().nextInt(100, 501);
        return new Account(accountID, balance);
    }

    public static List<Operation> generateOperations(){
        List<Operation> generatedOperations = new ArrayList<>();
        int maxOperations = 15;

        for(int i = 1; i <= maxOperations; i++)
            generatedOperations.add(generateOperation());

        return generatedOperations;
    }

    public static Operation generateOperation(){
        int balanceToTransfer = ThreadLocalRandom.current().nextInt(5,16);
        int sourceId = ThreadLocalRandom.current().nextInt(1,11);

        int destinationId = ThreadLocalRandom.current().nextInt(1, 11);
        while(destinationId == sourceId)
            destinationId = ThreadLocalRandom.current().nextInt(1, 11);

        return new Operation(sourceId, destinationId, balanceToTransfer);
    }
}
