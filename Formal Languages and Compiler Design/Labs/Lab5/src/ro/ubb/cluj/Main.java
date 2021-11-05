package ro.ubb.cluj;

import ro.ubb.cluj.domain.FAModel;
import ro.ubb.cluj.menu.Menu;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        ReadFA readFA = new ReadFA("src/ro/ubb/cluj/input/FA.in");
        FAModel faModel = readFA.readInformation();


        Menu menu = new Menu(faModel);
        menu.runMenu();
        System.out.println("end");
    }
}
