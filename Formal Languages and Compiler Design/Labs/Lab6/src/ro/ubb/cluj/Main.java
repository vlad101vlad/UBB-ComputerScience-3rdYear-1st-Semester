package ro.ubb.cluj;

import ro.ubb.cluj.domain.GrammarModel;
import ro.ubb.cluj.service.GrammarReader;
import ro.ubb.cluj.service.Menu;
import ro.ubb.cluj.test.TestFunctions;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        final String GRAMMAR_FILE_PATH = "src/ro/ubb/cluj/input/g1.txt";

        GrammarReader grammarReader = new GrammarReader(GRAMMAR_FILE_PATH);
        GrammarModel grammarModel = grammarReader.readGrammar();

        TestFunctions testFunctions = new TestFunctions();
        testFunctions.test();
//
//        Menu menu = new Menu(grammarModel);
//        menu.run();



        System.out.println("~end of program~");
    }
}
