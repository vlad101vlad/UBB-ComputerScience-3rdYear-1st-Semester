package en.ubbcluj.info;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DataGenerator.generateBankAccounts().forEach(account -> System.out.println(account));
        DataGenerator.generateOperations().forEach(account -> System.out.println(account));
    }
}
