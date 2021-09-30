package en.ubbcluj.info;

import en.ubbcluj.info.domain.Account;
import en.ubbcluj.info.domain.Operation;
import en.ubbcluj.info.service.LoggerService;
import en.ubbcluj.info.service.MarketService;
import en.ubbcluj.info.util.DataGenerator;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
//	// write your code here
//        DataGenerator.generateBankAccounts().forEach(account -> System.out.println(account));
//        DataGenerator.generateOperations().forEach(account -> System.out.println(account));
        List<Account> generatedAccounts = DataGenerator.generateBankAccounts();
        List<Operation> generatedOperations = DataGenerator.generateOperations();
        LoggerService loggerService = new LoggerService();

        MarketService market = new MarketService(generatedAccounts, generatedOperations, loggerService);
        market.runSequentialy();

        generatedAccounts.forEach(System.out::println);


    }
}
