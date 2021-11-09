package ro.ubb.cluj.threadtypes;

import ro.ubb.cluj.Matrix;
import ro.ubb.cluj.Util;

import java.util.List;

public class KthElementThread implements Runnable {
    private final int remainder;
    private final int NO_THREADS;
    private final Matrix resultMatrix;
    private final Matrix sourceMatrix1;
    private final Matrix sourceMatrix2;

    public KthElementThread(int remainder, int NO_THREADS, Matrix resultMatrix, Matrix sourceMatrix1, Matrix sourceMatrix2) {
        this.remainder = remainder;
        this.NO_THREADS = NO_THREADS;
        this.resultMatrix = resultMatrix;
        this.sourceMatrix1 = sourceMatrix1;
        this.sourceMatrix2 = sourceMatrix2;
    }

    @Override
    public void run() {
        for(int counter = this.remainder;
            counter <= resultMatrix.getNO_COLUMNS()*resultMatrix.getNO_ROWS(); counter += NO_THREADS){
            int rowIndex = counter/resultMatrix.getNO_COLUMNS() + 1;
            int colIndex = counter%resultMatrix.getNO_COLUMNS() + 1;

            List<Integer> sourceRow = sourceMatrix1.getRow(rowIndex);
            List<Integer> sourceColumn = sourceMatrix2.getColumn(colIndex);
            try {
                Util.computeElement(
                        resultMatrix,
                        rowIndex, colIndex-1,
                        sourceRow, sourceColumn
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
