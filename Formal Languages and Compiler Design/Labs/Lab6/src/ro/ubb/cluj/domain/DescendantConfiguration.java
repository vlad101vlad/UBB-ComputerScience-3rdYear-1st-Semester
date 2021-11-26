package ro.ubb.cluj.domain;

import java.util.Stack;

public class DescendantConfiguration {
    private ParsingState parsingState;
    private Integer inputIndex;
    private Integer productionIndex;
    private Stack<Object> workingStack;
    private Stack<String> inputStack;


    public DescendantConfiguration() {
        this.parsingState = ParsingState.NONE;
        this.inputIndex = -1;
        this.productionIndex = -1;
        this.workingStack = new Stack<>();
        this.inputStack = new Stack<>();
    }

    public DescendantConfiguration(ParsingState parsingState, Integer inputIndex,
                                   Integer productionIndex, Stack<Object> workingStack, Stack<String> inputStack) {
        this.parsingState = parsingState;
        this.inputIndex = inputIndex;
        this.productionIndex = productionIndex;
        this.workingStack = workingStack;
        this.inputStack = inputStack;
    }


    public ParsingState getParsingState() {
        return parsingState;
    }

    public void setParsingState(ParsingState parsingState) {
        this.parsingState = parsingState;
    }

    public Integer getInputIndex() {
        return inputIndex;
    }

    public void setInputIndex(Integer inputIndex) {
        this.inputIndex = inputIndex;
    }



    public Stack<Object> getWorkingStack() {
        return workingStack;
    }

    public void setWorkingStack(Stack<Object> workingStack) {
        this.workingStack = workingStack;
    }


    public Integer getProductionIndex() {
        return productionIndex;
    }

    public void setProductionIndex(Integer productionIndex) {
        this.productionIndex = productionIndex;
    }

    public Stack<String> getInputStack() {
        return inputStack;
    }

    public void setInputStack(Stack<String> inputStack) {
        this.inputStack = inputStack;
    }
}
