package ro.ubb.cluj;

import ro.ubb.cluj.domain.FAModel;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        ReadFA readFA = new ReadFA("src/ro/ubb/cluj/input/FA.in");
        FAModel faModel = readFA.readInformation();

        System.out.println("end");
    }
}
