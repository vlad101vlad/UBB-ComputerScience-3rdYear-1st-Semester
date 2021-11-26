package ro.ubb.cluj.domain;

public class NonterminalAndProduction {
    private String nonTerminal;
    private Integer productionIndex;

    public NonterminalAndProduction(String nonTerminal, Integer productionIndex) {
        this.nonTerminal = nonTerminal;
        this.productionIndex = productionIndex;
    }

    public String getNonTerminal() {
        return nonTerminal;
    }

    public void setNonTerminal(String nonTerminal) {
        this.nonTerminal = nonTerminal;
    }

    public Integer getProductionIndex() {
        return productionIndex;
    }

    public void setProductionIndex(Integer productionIndex) {
        this.productionIndex = productionIndex;
    }

    @Override
    public String toString() {
        return "NonterminalAndProduction{" +
                "nonTerminal='" + nonTerminal + '\'' +
                ", productionIndex=" + productionIndex +
                '}';
    }
}
