package ro.ubb.cluj.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Set<String> getSetOfStates(){
        Set<String> setOfStates = new HashSet<>();
        transitionList.forEach(transition -> {
            setOfStates.add(transition.getStartState());
            setOfStates.add(transition.getNextState());
        });

        return setOfStates;
    }

    public Set<String> getAlphabet(){
        Set<String> alphabet = new HashSet<>();
        transitionList.forEach(transition -> alphabet.add(transition.getLiteral()));
        return alphabet;
    }
}
