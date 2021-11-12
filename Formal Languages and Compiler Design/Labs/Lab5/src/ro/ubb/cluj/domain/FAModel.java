package ro.ubb.cluj.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    private List<String> getPossibleNextState(String currentTerminal, String currentState){
        List<String> transitions = new ArrayList<>();

        transitions = this.transitionList.stream()
                .filter(transition -> transition.getStartState().equals(currentState)
                        && transition.getLiteral().equals(currentTerminal))
                .map(transition -> transition.getNextState())
                .collect(Collectors.toList());

        return transitions;
    }


    public boolean isSequenceAcceptedByFa(int index, List<String> sequence, String currentState){
        if(index < sequence.size()){
            String currentTerminal = sequence.get(index);
            List<String> possibleNextState =  getPossibleNextState(currentTerminal, currentState);

            if(possibleNextState.size() < 1){
                return false;
            }

            for(String state: possibleNextState){
                if(this.finalStates.contains(state))
                    return true;
                if(this.isSequenceAcceptedByFa(index + 1, sequence, state))
                    return true;
            }
        }

        return false;
    }
}
