package en.ubbcluj.info.domain;

import java.util.List;
import java.util.ArrayList;

public class Account {
    private int id;
    private int balance;
    private List<Operation> LOGS;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
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

    @Override
    public String toString() {
        return "Account{" +
            "\n\tid=" + id +
            ", \n\tbalance=" + balance +
            ", \n\tLOGS=" + LOGS +
            "\n}\n";
    }
}
