package en.ubbcluj.info;

import en.ubbcluj.info.domain.Account;
import en.ubbcluj.info.domain.Operation;
import en.ubbcluj.info.service.LoggerService;
import en.ubbcluj.info.service.MarketService;
import en.ubbcluj.info.util.DataGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


import static en.ubbcluj.info.util.ThreadUtil.*;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        //We generate random data for the app
        List<Account> generatedAccounts = DataGenerator.generateBankAccounts();
        List<Operation> generatedOperations = DataGenerator.generateOperations();

        LoggerService loggerService = new LoggerService();
        Iterator<Operation> operationIterator = generatedOperations.iterator();
        System.out.println(operationIterator);


        int NO_OF_THREADS = 100;
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


        generatedAccounts.forEach(System.out::println);

        buildStatistics(generatedAccounts, loggerService, operationIterator);
    }

    public static void buildStatistics(List<Account> generatedAccounts, LoggerService loggerService,
                                       Iterator<Operation> operationIterator) throws IOException {

        List<Integer> NO_OF_THREADS = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144));
        loggerService.createStatistics();
        loggerService.logInfo();
        loggerService.log("For 150 operations:\n");
        for(Integer currentNumberOfThreads: NO_OF_THREADS){
            String statisticLogger = "";
            statisticLogger += "-> Number of threads: " + currentNumberOfThreads;

            List<Thread> threads = new ArrayList<>();
            for(int i = 1; i <= currentNumberOfThreads; i++){
                MarketService market = new MarketService(generatedAccounts, loggerService, operationIterator);
                Thread thread = new Thread(market);
                threads.add(thread);
            }

            long startTime = System.currentTimeMillis();
            startThreads(threads);
            stopThreads(threads);
            long stopTime = System.currentTimeMillis();

            long elapsedTime = stopTime - startTime;
            statisticLogger += " were done in " + elapsedTime + " miliseconds\n";
            loggerService.log(statisticLogger);
        }

        loggerService.closeLogWriter();
    }
}
