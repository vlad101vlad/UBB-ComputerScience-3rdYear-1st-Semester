package ro.ubb.cluj.test;

import ro.ubb.cluj.domain.*;
import ro.ubb.cluj.service.Operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestFunctions {

    public TestFunctions() {
    }

    public void test() throws Exception {
        test_initDescendentConfiguration();
        test_AdvanceOperation();
        test_ExpandOperations();
        test_CanExpand();
        test_MomentaryInsuccess();
        test_canGoBack();
        test_goBack();
        test_anotherTry();
        test_AnotherTry_error();
    }

    private void test_initDescendentConfiguration() throws Exception {
        DescendantConfiguration descendantConfiguration = new DescendantConfiguration();
        myAssert(descendantConfiguration.getInputIndex() == -1);
        myAssert(descendantConfiguration.getInputStack() != null);
        myAssert(descendantConfiguration.getParsingState() == ParsingState.NONE);
        myAssert(descendantConfiguration.getWorkingStack() != null);
    }

    private void test_AdvanceOperation() throws Exception {
        List<String> inputSequence = new ArrayList<>(Arrays.asList("a", "a", "b", "c"));

        DescendantConfiguration descendantConfiguration = new DescendantConfiguration();
        descendantConfiguration.setInputIndex(0);
        descendantConfiguration.getInputStack().push("a");

        myAssert(Operations.OperationChecker.canAdvance(descendantConfiguration, inputSequence));
        Operations.advance(descendantConfiguration);
        myAssert(descendantConfiguration.getInputIndex() == 1);
        myAssert(descendantConfiguration.getInputStack().empty());

        descendantConfiguration.getInputStack().push("b");
        descendantConfiguration.getInputStack().push("a");

        myAssert(Operations.OperationChecker.canAdvance(descendantConfiguration, inputSequence));
        Operations.advance(descendantConfiguration);
        myAssert(descendantConfiguration.getInputIndex() == 2);
        myAssert(descendantConfiguration.getWorkingStack().peek().equals("a"));
        myAssert(descendantConfiguration.getInputStack().peek().equals("b"));

        Operations.advance(descendantConfiguration);
        myAssert(descendantConfiguration.getInputIndex() == 3);
        myAssert(descendantConfiguration.getInputStack().empty());
        myAssert(descendantConfiguration.getWorkingStack().peek().equals("b"));
    }

    public void test_ExpandOperations() throws Exception{
        GrammarModel grammarModel = new GrammarModel();
        grammarModel.setNonTerminals(Arrays.asList("S", "B"));
        Production production = new Production("S", new ArrayList<>(Arrays.asList("a", "S", "a", "B")));
        grammarModel.setProductions(new ArrayList<>(Arrays.asList(production)));

        DescendantConfiguration descendantConfiguration = new DescendantConfiguration();
        descendantConfiguration.getInputStack().push("S");

        Operations.expand(descendantConfiguration, grammarModel);
        myAssert(descendantConfiguration.getWorkingStack().peek() instanceof NonterminalAndProduction);
        myAssert(((NonterminalAndProduction) descendantConfiguration.getWorkingStack().peek()).getNonTerminal().equals("S"));
        myAssert(((NonterminalAndProduction) descendantConfiguration.getWorkingStack().peek()).getProductionIndex() == 0);
        myAssert(descendantConfiguration.getInputStack().pop().equals("a"));
        myAssert(descendantConfiguration.getInputStack().pop().equals("S"));
        myAssert(descendantConfiguration.getInputStack().pop().equals("a"));
        myAssert(descendantConfiguration.getInputStack().pop().equals("B"));
    }

    public void test_CanExpand() throws Exception {
        GrammarModel grammarModel = new GrammarModel();
        grammarModel.setNonTerminals(Arrays.asList("S", "B"));

        DescendantConfiguration descendantConfiguration = new DescendantConfiguration();
        descendantConfiguration.getInputStack().push("S");

        myAssert(Operations.OperationChecker.canExpand(descendantConfiguration, grammarModel));

        descendantConfiguration.getInputStack().pop();
        descendantConfiguration.getInputStack().push("random");

        myAssert(!Operations.OperationChecker.canExpand(descendantConfiguration, grammarModel));
    }

    public void test_MomentaryInsuccess() throws Exception {
        DescendantConfiguration descendantConfiguration = new DescendantConfiguration();
        descendantConfiguration.setInputIndex(0);
        descendantConfiguration.getInputStack().push("a");

        int initialSizeWorkingStack = descendantConfiguration.getWorkingStack().size();
        int initialSizeInputStack = descendantConfiguration.getInputStack().size();

        Operations.momentaryInsuccess(descendantConfiguration);

        myAssert(descendantConfiguration.getParsingState() == ParsingState.BACK_STATE);
        myAssert(descendantConfiguration.getInputStack().size() == initialSizeInputStack);
        myAssert(descendantConfiguration.getWorkingStack().size() == initialSizeWorkingStack);
    }

    public void test_goBack() throws Exception {
        DescendantConfiguration descendantConfiguration = new DescendantConfiguration();
        descendantConfiguration.setInputIndex(1);
        descendantConfiguration.getWorkingStack().push("a");

        Operations.goBack(descendantConfiguration);
        myAssert(descendantConfiguration.getInputStack().peek().equals("a"));
        myAssert(descendantConfiguration.getWorkingStack().size() == 0);
        myAssert(descendantConfiguration.getInputIndex() == 0);
    }

    public void test_canGoBack() throws Exception {
        DescendantConfiguration descendantConfiguration = new DescendantConfiguration();
        descendantConfiguration.getWorkingStack().push("a");
        descendantConfiguration.getWorkingStack().push(new NonterminalAndProduction("S", 1));

        myAssert(!Operations.OperationChecker.canGoBack(descendantConfiguration));

        descendantConfiguration.getWorkingStack().pop();
        myAssert(Operations.OperationChecker.canGoBack(descendantConfiguration));
    }

    public void test_anotherTry() throws Exception {
        DescendantConfiguration descendantConfiguration = new DescendantConfiguration();
        descendantConfiguration.getWorkingStack().push(new NonterminalAndProduction("S", 0));

        GrammarModel grammarModel = new GrammarModel();
        grammarModel.setInitialNonTerminal("V");
        grammarModel.setNonTerminals(new ArrayList<>(Arrays.asList("S")));

        Production production1 = new Production("S", new ArrayList<>(Arrays.asList("a", "S", "b", "S")));
        production1.getProductionRule().forEach(element -> descendantConfiguration.getInputStack().push(element));

        Production production2 = new Production("S", new ArrayList<>(Arrays.asList("c", "d")));
        grammarModel.setProductions(new ArrayList<>(Arrays.asList(production1, production2)));

        Operations.anotherTry(descendantConfiguration, grammarModel);
        NonterminalAndProduction nonterminalAndProductionAfterTry
                = (NonterminalAndProduction) descendantConfiguration.getWorkingStack().peek();
        myAssert(nonterminalAndProductionAfterTry.getNonTerminal().equals("S"));
        myAssert(nonterminalAndProductionAfterTry.getProductionIndex() == 1);
        myAssert(descendantConfiguration.getParsingState() == ParsingState.NORMAL_STATE);

        myAssert(descendantConfiguration.getInputStack().peek().equals("c"));
        myAssert(descendantConfiguration.getInputStack().size() == 2);

        Operations.anotherTry(descendantConfiguration, grammarModel);
        myAssert(descendantConfiguration.getParsingState() == ParsingState.BACK_STATE);
        myAssert(descendantConfiguration.getWorkingStack().size() == 0);
        myAssert(descendantConfiguration.getInputStack().peek().equals("S"));
    }

    void test_AnotherTry_error() throws Exception {
        DescendantConfiguration descendantConfiguration = new DescendantConfiguration();
        descendantConfiguration.getWorkingStack().push(new NonterminalAndProduction("V", 0));
        descendantConfiguration.setInputIndex(0);
        descendantConfiguration.setParsingState(ParsingState.BACK_STATE);


        GrammarModel grammarModel = new GrammarModel();
        grammarModel.setInitialNonTerminal("V");
        Production production = new Production("V", new ArrayList<>(Arrays.asList("a b")));
        production.getProductionRule().forEach(productionRule -> descendantConfiguration.getInputStack().push(productionRule));
        grammarModel.setProductions(new ArrayList<>(Arrays.asList(production)));
        grammarModel.setNonTerminals(new ArrayList<>(Arrays.asList("V", "S")));

        int initialLenght = descendantConfiguration.getInputStack().size();
        Operations.anotherTry(descendantConfiguration, grammarModel);
        myAssert(descendantConfiguration.getParsingState() == ParsingState.ERROR_STATE);

        myAssert(descendantConfiguration.getInputStack().size() == initialLenght-production.getProductionRule().size());
    }

    private boolean myAssert(boolean condition) throws Exception {
        if(condition)
            return true;
        throw new Exception("Assertion expception: ");
    }
}
