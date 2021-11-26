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

        myAssert(Operations.canAdvance(descendantConfiguration, inputSequence));
        Operations.advance(descendantConfiguration);
        myAssert(descendantConfiguration.getInputIndex() == 1);
        myAssert(descendantConfiguration.getInputStack().empty());

        descendantConfiguration.getInputStack().push("b");
        descendantConfiguration.getInputStack().push("a");

        myAssert(Operations.canAdvance(descendantConfiguration, inputSequence));
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

        myAssert(Operations.canExpand(descendantConfiguration, grammarModel));

        descendantConfiguration.getInputStack().pop();
        descendantConfiguration.getInputStack().push("random");

        myAssert(!Operations.canExpand(descendantConfiguration, grammarModel));
    }

    private boolean myAssert(boolean condition) throws Exception {
        if(condition)
            return true;
        throw new Exception("Assertion expception: ");
    }
}
