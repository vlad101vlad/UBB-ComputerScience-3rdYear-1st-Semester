package ro.ubb.cluj;

import ro.ubb.cluj.domain.GrammarModel;
import ro.ubb.cluj.service.GrammarReader;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        final String GRAMMAR_FILE_PATH = "src/ro/ubb/cluj/input/grammar.in";

        GrammarReader grammarReader = new GrammarReader(GRAMMAR_FILE_PATH);
        GrammarModel grammarModel = grammarReader.readGrammar();

        System.out.println("~end of program~");
    }
}
