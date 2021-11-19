package ro.ubb.cluj.domain;

import java.util.List;

public class Production {
    private String nonTerminal;
    private List<String> productionRule;

    public Production(String nonTerminal, List<String> productionRule) {
        this.nonTerminal = nonTerminal;
        this.productionRule = productionRule;
    }

    public Production() {
    }

    public String getNonTerminal() {
        return nonTerminal;
    }

    public void setNonTerminal(String nonTerminal) {
        this.nonTerminal = nonTerminal;
    }

    public List<String> getProductionRule() {
        return productionRule;
    }

    public void setProductionRule(List<String> productionRule) {
        this.productionRule = productionRule;
    }

    @Override
    public String toString() {
        final String[] toBePrinted = new String[1];
        toBePrinted[0] = "";

        toBePrinted[0] += this.getNonTerminal() + " -> ";
        this.getProductionRule().forEach(production -> toBePrinted[0] += production + " ");

        return toBePrinted[0];
    }
}
