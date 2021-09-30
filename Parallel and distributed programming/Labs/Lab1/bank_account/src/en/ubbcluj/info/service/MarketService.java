package en.ubbcluj.info.service;

import en.ubbcluj.info.domain.Account;
import en.ubbcluj.info.domain.Operation;

import java.io.IOException;
import java.util.List;

public class MarketService {
    private final List<Account> accountList;
    private final List<Operation> operationList;
    private final LoggerService loggerService;

    public MarketService(List<Account> accountList, List<Operation> operationList, LoggerService loggerService) {
        this.accountList = accountList;
        this.operationList = operationList;
        this.loggerService = loggerService;
    }

    public void runSequentialy() throws IOException {
        logStartData();

        for(Operation operation: operationList){
            Account source = accountList.stream()
                .filter(account -> account.getId() == operation.getSourceID()).findFirst().get();
            Account destination = accountList.stream()
                .filter(account -> account.getId() == operation.getDestinationID()).findFirst().get();
            
            if(source.getBalance() - operation.getAmount() >= 0){
                source.setBalance(source.getBalance() - operation.getAmount());
                destination.setBalance(destination.getBalance() + operation.getAmount());

                source.getLOGS().add(operation);
                destination.getLOGS().add(operation);

                logOperation(operation);
                logAccountsBalance();
            }
        }

        loggerService.closeLogWriter();
    }

    public void run(){

    }



    private void logStartData() throws IOException {
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
