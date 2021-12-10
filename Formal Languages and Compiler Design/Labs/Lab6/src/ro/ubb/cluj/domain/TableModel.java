package ro.ubb.cluj.domain;

public class TableModel {
    private int index;
    private String info;
    private int parentIndex;
    private int leftIndex;


    public TableModel(int index, String info, int parentIndex, int leftIndex) {
        this.index = index;
        this.info = info;
        this.parentIndex = parentIndex;
        this.leftIndex = leftIndex;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public void setParentIndex(int parentIndex) {
        this.parentIndex = parentIndex;
    }

    public int getLeftIndex() {
        return leftIndex;
    }

    public void setLeftIndex(int leftIndex) {
        this.leftIndex = leftIndex;
    }

    @Override
    public String toString() {
        return "TableModel{" +
            "index=" + (index+1) +
            ", info='" + info + '\'' +
            ", parentIndex=" + (parentIndex+1) +
            ", leftIndex=" + (leftIndex+1) +
            '}';
    }
}
