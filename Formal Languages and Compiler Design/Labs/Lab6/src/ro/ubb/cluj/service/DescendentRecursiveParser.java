package ro.ubb.cluj.service;

import ro.ubb.cluj.domain.DescendantConfiguration;
import ro.ubb.cluj.domain.GrammarModel;
import ro.ubb.cluj.domain.ParsingState;

import java.util.List;
import java.util.Stack;

public class DescendentRecursiveParser {
    DescendantConfiguration descendantConfiguration;
    GrammarModel grammarModel;
    List<String> inputSequence;

    public DescendentRecursiveParser(GrammarModel grammarModel, List<String> inputSequence) {
        this.grammarModel = grammarModel;
        this.descendantConfiguration
                = new DescendantConfiguration(ParsingState.NORMAL_STATE, 0, new Stack<>(), new Stack<>());
        this.descendantConfiguration.getInputStack().push(grammarModel.getInitialNonTerminal());
        this.inputSequence = inputSequence;
    }

    public void runParser() throws Exception {
        ParsingState parsingState = this.descendantConfiguration.getParsingState();
        while(parsingState != ParsingState.FINAL_STATE && parsingState != ParsingState.ERROR_STATE){
            if(parsingState == ParsingState.NORMAL_STATE){
                if(Operations.OperationChecker.canSuccess(this.descendantConfiguration, this.inputSequence))
                    Operations.success(this.descendantConfiguration);
                else{
                        if(Operations.OperationChecker.canExpand(this.descendantConfiguration, this.grammarModel))
                            Operations.expand(this.descendantConfiguration, this.grammarModel);
                        else{
                            if(Operations.OperationChecker.canAdvance(descendantConfiguration, this.inputSequence))
                                Operations.advance(this.descendantConfiguration);
                            else Operations.momentaryInsuccess(this.descendantConfiguration);
                        }
                    }
                }

            if(parsingState == ParsingState.BACK_STATE){
                if(Operations.OperationChecker.canGoBack(this.descendantConfiguration))
                    Operations.goBack(this.descendantConfiguration);
                else Operations.anotherTry(this.descendantConfiguration, this.grammarModel);
            }

            parsingState = this.descendantConfiguration.getParsingState();
        }

        if(this.descendantConfiguration.getParsingState() == ParsingState.ERROR_STATE)
            throw new Exception("Sequence cannot be parsed");
        System.out.println("Sequence accepted!");

        TableBuilder tableBuilder = new TableBuilder(this.grammarModel, this.descendantConfiguration.getWorkingStack());
        tableBuilder.buildTable();
    }
}
