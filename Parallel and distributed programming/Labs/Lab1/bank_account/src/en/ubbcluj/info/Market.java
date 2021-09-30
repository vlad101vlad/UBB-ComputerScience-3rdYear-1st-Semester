package en.ubbcluj.info;

import java.util.List;

public class Market {
    private List<Account> accountList;
    private List<Operation> operationList;

    public Market(List<Account> accountList, List<Operation> operationList) {
        this.accountList = accountList;
        this.operationList = operationList;
    }

    public void run(){
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
            }
        }
    }
}
