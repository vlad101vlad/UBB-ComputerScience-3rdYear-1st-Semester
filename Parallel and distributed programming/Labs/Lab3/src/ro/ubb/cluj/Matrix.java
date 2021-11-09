package ro.ubb.cluj;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private int NO_COLUMNS;
    private int NO_ROWS;
    private List<List<Integer>> matrix;

    public Matrix(int NO_ROWS, int NO_COLUMNS) {
        this.NO_COLUMNS = NO_COLUMNS;
        this.NO_ROWS = NO_ROWS;

        this.matrix = new ArrayList<>();
        for(int index = 0; index < NO_ROWS; index++){
            this.matrix.add(new ArrayList<>());
        }
    }

    public Integer get(int row, int column){
        return matrix.get(row-1).get(column-1);
    }
    public List<Integer> getRow(int rowIndex){
        return this.matrix.get(rowIndex-1);
    }

    @Override
    public String toString() {
        StringBuilder toBeShown = new StringBuilder();

        for(int rowIndex = 1; rowIndex <= NO_ROWS; rowIndex++){
            for(int colIndex = 1; colIndex <= NO_COLUMNS; colIndex++){
                Integer element = this.get(rowIndex, colIndex);
                String currentElement = String.format("%1$4s", element.toString());
                toBeShown.append(currentElement).append(" ");
            }
            toBeShown.append("\n");
        }

        return toBeShown.toString();
    }
}
