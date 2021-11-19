package ro.ubb.cluj.domain;

import java.util.List;

public class GrammarModel {
    private String initialNonTerminal;
    private List<String> nonTerminals;
    private List<String> terminals;
    private List<Production> productions;

    public GrammarModel() {
    }

    public GrammarModel(String initialNonTerminal, List<String> nonTerminals, List<String> terminals, List<Production> productions) {
        this.initialNonTerminal = initialNonTerminal;
        this.nonTerminals = nonTerminals;
        this.terminals = terminals;
        this.productions = productions;
    }


    public String getInitialNonTerminal() {
        return initialNonTerminal;
    }

    public void setInitialNonTerminal(String initialNonTerminal) {
        this.initialNonTerminal = initialNonTerminal;
    }

    public List<String> getNonTerminals() {
        return nonTerminals;
    }

    public void setNonTerminals(List<String> nonTerminals) {
        this.nonTerminals = nonTerminals;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public void setProductions(List<Production> productions) {
        this.productions = productions;
    }

    public List<String> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<String> terminals) {
        this.terminals = terminals;
    }
}
