package ro.ubb.cluj.service;

import ro.ubb.cluj.domain.DescendantConfiguration;
import ro.ubb.cluj.domain.GrammarModel;
import ro.ubb.cluj.domain.NonterminalAndProduction;
import ro.ubb.cluj.domain.Production;

import java.util.List;

public class Operations {
    public static void expand(DescendantConfiguration descendantConfiguration, GrammarModel grammarModel) throws Exception {
        String nextNonTerminal = descendantConfiguration.getInputStack().pop();

        NonterminalAndProduction nonterminalAndProduction
                = new NonterminalAndProduction(nextNonTerminal, 0);

        Production toBeExpanded = grammarModel.getProductionsForNonterminal(nextNonTerminal).get(0);
        for(int index = toBeExpanded.getProductionRule().size() - 1; index >= 0; index--){
            String nextElement = toBeExpanded.getProductionRule().get(index);
            descendantConfiguration.getInputStack().push(nextElement);
        }

        descendantConfiguration.getWorkingStack().push(nonterminalAndProduction);
    }

    public static void advance(DescendantConfiguration descendantConfiguration){
        String nextNonTerminal = descendantConfiguration.getInputStack().pop();

        descendantConfiguration.getWorkingStack().push(nextNonTerminal);
        descendantConfiguration.setInputIndex(descendantConfiguration.getInputIndex() + 1);
    }

    public static boolean canAdvance(DescendantConfiguration descendantConfiguration,
                                     List<String> inputSequence){
        String nextNonTerminal = inputSequence.get(descendantConfiguration.getInputIndex());

        return descendantConfiguration.getInputStack().peek().equals(nextNonTerminal);
    }

    public static boolean canExpand(DescendantConfiguration descendantConfiguration, GrammarModel grammarModel){
        String nextInInputStack = descendantConfiguration.getInputStack().peek();

        return grammarModel.getNonTerminals().contains(nextInInputStack);
    }
}
