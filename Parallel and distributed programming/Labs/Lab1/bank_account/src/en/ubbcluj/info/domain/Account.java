package en.ubbcluj.info.domain;

import java.util.List;
import java.util.ArrayList;


/**
 * This class is used for representing an account from the problem statement
 *
 */
public class Account {
    private int id;
    private int balance;
    private final int initialBalance;
    private List<Operation> LOGS;

    /**
     * @param id - unique id for the account
     * @param balance - amount of money on that account
     */
    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
        this.initialBalance = balance;
        this.LOGS = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Operation> getLOGS() {
        return LOGS;
    }

    public void setLOGS(List<Operation> LOGS) {
        this.LOGS = LOGS;
    }

    public boolean checkIntegrity(){
        int difference = 0;

        for(Operation operation: this.LOGS){
            if(operation.getSourceID() == this.getId())
                difference += operation.getAmount();
            if(operation.getDestinationID() == this.getId())
                difference -= operation.getAmount();
        }
        return this.initialBalance == this.getBalance() + difference;
    }

    @Override
    public String toString() {
        return "Account{" +
            "\n\tid=" + id +
            ", \n\tbalance=" + balance +
            ", \n\tLOGS=" + LOGS +
            "\n}\n";
    }

    public int getInitialBalance() {
        return initialBalance;
    }
}
