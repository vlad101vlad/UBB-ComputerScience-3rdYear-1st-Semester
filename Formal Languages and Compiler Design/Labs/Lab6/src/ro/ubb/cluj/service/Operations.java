package ro.ubb.cluj.service;

import ro.ubb.cluj.domain.*;

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

    public static void momentaryInsuccess(DescendantConfiguration descendantConfiguration){
        descendantConfiguration.setParsingState(ParsingState.BACK_STATE);
    }

    public static void goBack(DescendantConfiguration descendantConfiguration){
        String badTerminal = (String) descendantConfiguration.getWorkingStack().pop();

        descendantConfiguration.setInputIndex(descendantConfiguration.getInputIndex() - 1);
        descendantConfiguration.getInputStack().push(badTerminal);
    }

    public static void anotherTry(DescendantConfiguration descendantConfiguration, GrammarModel grammarModel) throws Exception {
        NonterminalAndProduction nonterminalAndProduction
                = (NonterminalAndProduction) descendantConfiguration.getWorkingStack().peek();

        int currentProductionIndex = nonterminalAndProduction.getProductionIndex();
        String nonTerminal = nonterminalAndProduction.getNonTerminal();

        List<Production> productionsForNonterminal = grammarModel.getProductionsForNonterminal(nonTerminal);
        Production currentProduction = productionsForNonterminal.get(currentProductionIndex);

        if(currentProductionIndex + 1 < productionsForNonterminal.size()){
            Production nextProduction = productionsForNonterminal.get(currentProductionIndex + 1);

            descendantConfiguration.getWorkingStack().pop();
            currentProduction.getProductionRule().forEach(production
                    -> descendantConfiguration.getInputStack().pop());

            descendantConfiguration.getWorkingStack().push(
                    new NonterminalAndProduction(nonTerminal, currentProductionIndex+1)
            );
            for(int index = nextProduction.getProductionRule().size() - 1; index >= 0; index--){
                String currentTerminal = nextProduction.getProductionRule().get(index);
                descendantConfiguration.getInputStack().push(currentTerminal);
            }

            descendantConfiguration.setParsingState(ParsingState.NORMAL_STATE);
        }else{
            currentProduction.getProductionRule().forEach(production
                    -> descendantConfiguration.getInputStack().pop());
            descendantConfiguration.getInputStack().push(nonTerminal);
            descendantConfiguration.getWorkingStack().pop();
            descendantConfiguration.setParsingState(ParsingState.BACK_STATE);

            if(
                    descendantConfiguration.getInputStack().peek().equals(grammarModel.getInitialNonTerminal())
                    && descendantConfiguration.getInputIndex() == 0
            ){
                descendantConfiguration.setParsingState(ParsingState.ERROR_STATE);
                descendantConfiguration.getInputStack().pop();
            }
        }

    }



    public static class OperationChecker{
        public static boolean canAdvance(DescendantConfiguration descendantConfiguration,
                                         List<String> inputSequence){
            String nextNonTerminal = inputSequence.get(descendantConfiguration.getInputIndex());

            return descendantConfiguration.getInputStack().peek().equals(nextNonTerminal);
        }

        public static boolean canExpand(DescendantConfiguration descendantConfiguration, GrammarModel grammarModel){
            String nextInInputStack = descendantConfiguration.getInputStack().peek();

            return grammarModel.getNonTerminals().contains(nextInInputStack);
        }

        public static boolean canGoBack(DescendantConfiguration descendantConfiguration){
            Object lastElementWorkingStack = descendantConfiguration.getWorkingStack().peek();

            return lastElementWorkingStack instanceof String;
        }
    }

}
