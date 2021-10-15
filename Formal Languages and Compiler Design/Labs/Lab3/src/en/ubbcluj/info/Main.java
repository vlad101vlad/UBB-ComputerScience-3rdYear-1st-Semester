package en.ubbcluj.info;

public class Main {

    public static void main(String[] args) {
	// write your code here
        SymbolTable symbolTable = new SymbolTable(29);
        System.out.println(symbolTable.addToTable("vlad", "rares"));
        System.out.println(symbolTable.addToTable("raducu", "rares"));
        System.out.println(symbolTable.addToTable("vlad", "rares"));
    }
}
