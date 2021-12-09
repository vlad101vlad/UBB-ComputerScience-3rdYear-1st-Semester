package ro.ubb.cluj.domain;

public class NonterminalParentIndex {
    private NonterminalAndProduction nonterminalAndProduction;
    private int index;
    private int parent;

    public NonterminalParentIndex(NonterminalAndProduction nonterminalAndProduction, int index, int parent) {
        this.nonterminalAndProduction = nonterminalAndProduction;
        this.index = index;
        this.parent = parent;
    }

    public NonterminalAndProduction getNonterminalAndProduction() {
        return nonterminalAndProduction;
    }

    public void setNonterminalAndProduction(NonterminalAndProduction nonterminalAndProduction) {
        this.nonterminalAndProduction = nonterminalAndProduction;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "NonterminalParentIndex{" +
            "nonterminalAndProduction=" + nonterminalAndProduction +
            ", index=" + index +
            ", parent=" + parent +
            '}';
    }
}
