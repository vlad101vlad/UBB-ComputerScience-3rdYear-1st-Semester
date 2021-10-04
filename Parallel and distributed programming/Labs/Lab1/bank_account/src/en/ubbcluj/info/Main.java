package en.ubbcluj.info;

import en.ubbcluj.info.domain.Account;
import en.ubbcluj.info.domain.Operation;
import en.ubbcluj.info.service.LoggerService;
import en.ubbcluj.info.service.MarketService;
import en.ubbcluj.info.util.DataGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import static en.ubbcluj.info.util.ThreadUtil.*;

public class Main {

    public static void main(String[] args) throws IOException {
        //We generate random data for the app
        List<Account> generatedAccounts = DataGenerator.generateBankAccounts();
        List<Operation> generatedOperations = DataGenerator.generateOperations();

        LoggerService loggerService = new LoggerService();
        Iterator<Operation> operationIterator = generatedOperations.iterator();
        System.out.println(operationIterator);


        int NO_OF_THREADS = 5;
        List<Thread> threads = new ArrayList<>();
        for(int i = 1; i <= NO_OF_THREADS; i++){
            MarketService market = new MarketService(generatedAccounts, loggerService, operationIterator);
            Thread thread = new Thread(market);
            threads.add(thread);
        }

        startThreads(threads);
        printAllRunningThreads();
        stopThreads(threads);
        printAllRunningThreads();
        loggerService.closeLogWriter();
    }
}
