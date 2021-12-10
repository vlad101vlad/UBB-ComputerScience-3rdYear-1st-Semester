package ro.ubb.cluj.service;

import ro.ubb.cluj.domain.GrammarModel;
import ro.ubb.cluj.domain.NonterminalAndProduction;
import ro.ubb.cluj.domain.NonterminalParentIndex;
import ro.ubb.cluj.domain.TableModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class TableBuilder {
    private GrammarModel grammarModel;
    private List<Object> workingStack;
    private List<TableModel> result = new ArrayList<>();
    private int nextNonTerminal = 1;

    public TableBuilder(GrammarModel grammarModel, Stack<Object> workingStack) {
        this.grammarModel = grammarModel;
        this.workingStack = this.fromStackToList(workingStack);
    }

    public void buildTable() throws Exception {
        boolean foundFirst = false;
        int index = 0;

        NonterminalAndProduction nonterminalAndProduction = (NonterminalAndProduction) this.workingStack.get(0);
        List<NonterminalParentIndex> newNonTerminals = new ArrayList<>();

        generateTableRows(nonterminalAndProduction, 0, -1, false, newNonTerminals);

        int iterator = 0;
        while(newNonTerminals.size() > iterator){
            NonterminalParentIndex currentNonterminalParentIndex = newNonTerminals.get(iterator);
            generateTableRows(currentNonterminalParentIndex.getNonterminalAndProduction(),
                currentNonterminalParentIndex.getIndex(), currentNonterminalParentIndex.getParent(), true,
                newNonTerminals);
            iterator++;
        }

        printResult();
    }

    private void printResult(){
        this.result.forEach(System.out::println);
    }

    private void generateTableRows(NonterminalAndProduction nonterminalAndProduction,
                                                           int index,
                                                           int parentIndex, boolean foundFirst,
                                                           List<NonterminalParentIndex> nonterminalParentIndices) throws Exception {
        List<NonterminalParentIndex> row = new ArrayList<>();

        if(!foundFirst){
            addElement(index, nonterminalAndProduction.getNonTerminal(), parentIndex, -1);
            parentIndex = index;
            foundFirst = true;
        }

        List<String> productionExpanded = this.grammarModel
            .getProductionsForNonterminal(nonterminalAndProduction.getNonTerminal())
            .get(nonterminalAndProduction.getProductionIndex()).getProductionRule();
        int leftSiblingIndex = -1;
        for(String element: productionExpanded){
            index++;
            addElement(index, element, parentIndex, leftSiblingIndex);
            if(grammarModel.getNonTerminals().contains(element)){
                NonterminalAndProduction nextNonTerminal = findNextNonTerminal(this.nextNonTerminal);
                if(nextNonTerminal != null){
                    nonterminalParentIndices.add(new NonterminalParentIndex(nextNonTerminal, index, index));
                }
            }
            leftSiblingIndex = index;
        }
        final int finalIndex = index;
        nonterminalParentIndices.forEach(element -> element.setIndex(finalIndex));
    }



    private void addElement(int index, String value, int parent, int leftSibling){
        TableModel tableModel = new TableModel(index, value, parent, leftSibling);
        this.result.add(tableModel);
    }

    private NonterminalAndProduction findNextNonTerminal(int startIndex){
        for(int index = startIndex; index < this.workingStack.size(); index++){
            if(this.workingStack.get(index) instanceof NonterminalAndProduction){
                this.nextNonTerminal = index + 1;
                return (NonterminalAndProduction) this.workingStack.get(index);
            }

        }
        return null;
    }

    private List<Object> fromStackToList(Stack<Object> stack){
        return new ArrayList<>(stack);
    }
}
