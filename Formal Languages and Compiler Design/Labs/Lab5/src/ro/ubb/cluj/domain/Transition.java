package ro.ubb.cluj.domain;

public class Transition {
    private final String startState;
    private final String nextState;
    private final String literal;

    public Transition(String startState, String nextState, String literal) {
        this.startState = startState;
        this.nextState = nextState;
        this.literal = literal;
    }

    public String getStartState() {
        return startState;
    }

    public String getNextState() {
        return nextState;
    }

    public String getLiteral() {
        return literal;
    }

    @Override
    public String toString() {
        String toBeWritten = "";
        toBeWritten += startState + " -> " + nextState + " :" + literal;

        return toBeWritten;
    }
}
