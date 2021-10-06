package en.ubbcluj.info.service;

import en.ubbcluj.info.domain.Account;
import en.ubbcluj.info.domain.Operation;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

public class MarketService implements Runnable {
    private final List<Account> accountList;
    private final LoggerService loggerService;
    private final Iterator<Operation> operationIterator;
    private Operation workingOperation = null;
    private long elapsedFromStart;

    public MarketService(List<Account> accountList, LoggerService loggerService, Iterator<Operation> operationIterator) {
        this.accountList = accountList;
        this.loggerService = loggerService;
        this.operationIterator = operationIterator;
        elapsedFromStart = System.currentTimeMillis();
    }

    /**
     * The core of this application, this methods will be run in each individual thread as follows:
     * -> Each thread has an iterator to the list of operation
     *      -> He wants to see which is the next operation, thus we need to lock the iterator in order to get
     *      consistents results due to concurency
     * -> We check after an amount of time from the start of the thread the consistency of the account (whether
     * the logs from the accounts corespond to the actual balance of the account: we do this by saving the initial
     * balance of each account at the start of the application)
     * -> After this we do the actual transfer from one account to other. Here , we have to lock again the block in
     * which we do this check
     */
    public void run() {
        while(true){
            //Here we lock the iterator because multiple threads will try to access with for reading the next operation
            synchronized (operationIterator){
                if(operationIterator.hasNext()){
                    workingOperation = operationIterator.next();
                }
                else {
                    break;
                }
            }

            if(elapsedFromStart > 500){
                synchronized (accountList){
                    if(!checkAccounts()){
                        System.out.println("Error while checking integrity of the accounts");
                        break;
                    }
                }
                elapsedFromStart = 0;
            }


            Account source = accountList.stream()
                .filter(account -> account.getId() == workingOperation.getSourceID()).findFirst().get();
            Account destination = accountList.stream()
                .filter(account -> account.getId() == workingOperation.getDestinationID()).findFirst().get();

            //Here we lock beacause we could have conncurent operations which may happen on the accounts
            synchronized (this){
                if(source.getBalance() - workingOperation.getAmount() >= 0){
                    source.setBalance(source.getBalance() - workingOperation.getAmount());
                    destination.setBalance(destination.getBalance() + workingOperation.getAmount());

                    source.getLOGS().add(workingOperation);
                    destination.getLOGS().add(workingOperation);

                    try{
                        logOperation(workingOperation);
                        //logAccountsBalance();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }            
        }
    }

    private boolean checkAccounts(){
        for(Account account: this.accountList)
            if(!account.checkIntegrity())
                return false;
        return true;
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
        loggMessage += "... current time: " + LocalTime.now() + "\n";
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
