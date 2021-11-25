package ro.ubb.cluj.service;

import ro.ubb.cluj.domain.DescendantConfiguration;
import ro.ubb.cluj.domain.GrammarModel;

import java.util.List;

public class Operations {
    public static void expand(DescendantConfiguration descendantConfiguration,
                              List<String> inputSequence, GrammarModel grammarModel) {

    }

    public static boolean advance(DescendantConfiguration descendantConfiguration,
                               List<String> inputSequence){
        String nextNonTerminal = descendantConfiguration.getInputStack().pop();


        if(!nextNonTerminal.equals(inputSequence.get(descendantConfiguration.getInputIndex()))){
            descendantConfiguration.getInputStack().push(nextNonTerminal);
            return false;
        }

        descendantConfiguration.getWorkingStack().push(nextNonTerminal);
        descendantConfiguration.setInputIndex(descendantConfiguration.getInputIndex() + 1);
        return true;
    }
}
