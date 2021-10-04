package en.ubbcluj.info.service;

import en.ubbcluj.info.domain.Account;
import en.ubbcluj.info.domain.Operation;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MarketService implements Runnable {
    private final List<Account> accountList;
    private final LoggerService loggerService;
    private final Iterator<Operation> operationIterator;
    private Operation workingOperation = null;

    public MarketService(List<Account> accountList, LoggerService loggerService, Iterator<Operation> operationIterator) {
        this.accountList = accountList;
        this.loggerService = loggerService;
        this.operationIterator = operationIterator;
    }

    public void run() {        
        ReentrantLock reentrantLock = new ReentrantLock();
        while(true){
            synchronized (operationIterator){
                if(operationIterator.hasNext()){
                    workingOperation = operationIterator.next();
                }
                else {
                    break;
                }
            }

            Account source = accountList.stream()
                .filter(account -> account.getId() == workingOperation.getSourceID()).findFirst().get();
            Account destination = accountList.stream()
                .filter(account -> account.getId() == workingOperation.getDestinationID()).findFirst().get();

            synchronized (this){
                if(source.getBalance() - workingOperation.getAmount() >= 0){
                    source.setBalance(source.getBalance() - workingOperation.getAmount());
                    destination.setBalance(destination.getBalance() + workingOperation.getAmount());

                    source.getLOGS().add(workingOperation);
                    destination.getLOGS().add(workingOperation);

                    try{
                        logOperation(workingOperation);
                        logAccountsBalance();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }            
        }
    }



    public void logStartData(List<Operation> operationList) throws IOException {
        loggerService.log("\n~Accounts at the start of the market:\n");
        for(Account account: accountList)
            loggerService.log(account.toString());

        loggerService.log("\n~Operations at the start of the market:\n");
        for(Operation operation: operationList)
            loggerService.log(operation.toString());
    }

    private void logOperation(Operation operation) throws IOException {
        String loggMessage = String
            .format("\n\n-> Operation#%s was done: transfered %d money from %d to %d\n",
                operation.getOperationID(), operation.getAmount(), operation.getSourceID(),
                operation.getDestinationID());
        loggMessage += "... executed by Thread: " + Thread.currentThread().getName() + "\n";
        loggMessage += "------------------------------------------------------------\n";
        loggerService.log(loggMessage);
    }

    private void logAccountsBalance() throws IOException {
        StringBuilder accountsLogs = new StringBuilder();
        for(Account account: accountList){
            accountsLogs.append(String.format("*Account#%d: %d$\n", account.getId(), account.getBalance()));
        }
        loggerService.log(accountsLogs.toString());
    }
}
