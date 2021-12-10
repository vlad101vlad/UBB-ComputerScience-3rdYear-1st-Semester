package ro.ubb.cluj;

import ro.ubb.cluj.domain.GrammarModel;
import ro.ubb.cluj.service.DescendentRecursiveParser;
import ro.ubb.cluj.service.GrammarReader;
import ro.ubb.cluj.service.Menu;
import ro.ubb.cluj.test.TestFunctions;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static List<String> sampleGrammar(){
        List<String> grammar = new ArrayList<>();
        grammar.add("null");
        grammar.add("main");
        grammar.add("{");
        grammar.add("intreg");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("intreg");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("intreg");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("intreg");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("scrie");
        grammar.add(":");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("scrie");
        grammar.add(":");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("scrie");
        grammar.add(":");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("daca");
        grammar.add(":");
        grammar.add("identificator");
        grammar.add(">");
        grammar.add("identificator");
        grammar.add("{");
        grammar.add("identificator");
        grammar.add("=");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("afiseaza");
        grammar.add(":");
        grammar.add("identificator");
        grammar.add(";");
        grammar.add("}");

        return grammar;
    }

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
        List<String> inputSequence = new ArrayList<>(Arrays.asList("a", "a", "c", "b", "c"));
//        List<String> inputSequence = new ArrayList<>(Arrays.asList("c"));
        DescendentRecursiveParser descendentRecursiveParser = new DescendentRecursiveParser(grammarModel, inputSequence);
        descendentRecursiveParser.runParser();


        System.out.println("~end of program~");
    }
}
