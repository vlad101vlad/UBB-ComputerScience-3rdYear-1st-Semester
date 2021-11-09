package ro.ubb.cluj.threadtypes;

import ro.ubb.cluj.Matrix;
import ro.ubb.cluj.Util;

public class RowThread implements Runnable {
    Integer remainder;
    Integer NO_THREADS;
    Matrix resultMatrix;
    Matrix sourceMatrix1;
    Matrix sourceMatrix2;

    public RowThread(Integer remainder, Integer NO_THREADS, Matrix resultMatrix, Matrix sourceMatrix1, Matrix sourceMatrix2) {
        this.remainder = remainder;
        this.NO_THREADS = NO_THREADS;
        this.resultMatrix = resultMatrix;
        this.sourceMatrix1 = sourceMatrix1;
        this.sourceMatrix2 = sourceMatrix2;
    }


    @Override
    public void run() {
        for(int rowToComputeIndex = 1 + remainder;
            rowToComputeIndex <= resultMatrix.getNO_ROWS(); rowToComputeIndex+=NO_THREADS){
            try {
                Util.computeRow(
                        resultMatrix,
                        rowToComputeIndex,
                        sourceMatrix1, sourceMatrix2
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
