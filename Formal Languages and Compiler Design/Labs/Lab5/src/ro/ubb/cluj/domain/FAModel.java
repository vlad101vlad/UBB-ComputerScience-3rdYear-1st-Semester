package ro.ubb.cluj.domain;

import java.util.List;

public class FAModel {
    private String initialState;
    private List<String> finalStates;
    private List<Transition> transitionList;

    public FAModel(String initialState, List<String> finalStates, List<Transition> transitionList) {
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.transitionList = transitionList;
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(List<String> finalStates) {
        this.finalStates = finalStates;
    }

    public List<Transition> getTransitionList() {
        return transitionList;
    }

    public void setTransitionList(List<Transition> transitionList) {
        this.transitionList = transitionList;
    }
}
