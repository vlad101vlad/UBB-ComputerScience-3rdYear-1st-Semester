package ro.ubb.cluj;

import java.util.List;

public class Matrix {
    private int NO_COLUMNS;
    private int NO_ROWS;
    private List<List<Integer>> matrix;

    public Matrix(int NO_ROWS, int NO_COLUMNS) {
        this.NO_COLUMNS = NO_COLUMNS;
        this.NO_ROWS = NO_ROWS;
    }

    public Integer get(int row, int column){
        return matrix.get(row-1).get(column-1);
    }
}
