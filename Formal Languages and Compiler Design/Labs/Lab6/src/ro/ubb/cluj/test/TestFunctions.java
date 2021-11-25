package ro.ubb.cluj.test;

import ro.ubb.cluj.domain.DescendantConfiguration;
import ro.ubb.cluj.domain.GrammarModel;
import ro.ubb.cluj.domain.ParsingState;
import ro.ubb.cluj.service.Operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestFunctions {

    public TestFunctions() {
    }

    public void test() throws Exception {
        test_initDescendentConfiguration();
        test_ExpandOperation();
    }

    private void test_initDescendentConfiguration() throws Exception {
        DescendantConfiguration descendantConfiguration = new DescendantConfiguration();
        myAssert(descendantConfiguration.getInputIndex() == -1);
        myAssert(descendantConfiguration.getInputStack() != null);
        myAssert(descendantConfiguration.getParsingState() == ParsingState.NONE);
        myAssert(descendantConfiguration.getWorkingStack() != null);
    }

    private void test_ExpandOperation() throws Exception {
        List<String> inputSequence = new ArrayList<>(Arrays.asList("a", "a", "b", "c"));
        DescendantConfiguration descendantConfiguration = new DescendantConfiguration();
        descendantConfiguration.setInputIndex(0);
        descendantConfiguration.getInputStack().push("a");

        myAssert(Operations.advance(descendantConfiguration, inputSequence));
        myAssert(descendantConfiguration.getInputIndex() == 1);

        descendantConfiguration.getInputStack().push("b");
        descendantConfiguration.getInputStack().push("a");

        myAssert(Operations.advance(descendantConfiguration, inputSequence));

        myAssert(Operations.advance(descendantConfiguration, inputSequence));
    }

    private boolean myAssert(boolean condition) throws Exception {
        if(condition)
            return true;
        throw new Exception("Assertion expception: ");
    }
}
