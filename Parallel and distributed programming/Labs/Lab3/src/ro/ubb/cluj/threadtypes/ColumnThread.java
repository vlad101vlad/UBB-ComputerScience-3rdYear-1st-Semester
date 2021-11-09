package ro.ubb.cluj.threadtypes;

import ro.ubb.cluj.Matrix;
import ro.ubb.cluj.Util;

public class ColumnThread implements Runnable {
    Integer remainder;
    Integer NO_THREADS;
    Matrix resultMatrix;
    Matrix sourceMatrix1;
    Matrix sourceMatrix2;

    public ColumnThread(Integer remainder, Integer NO_THREADS, Matrix resultMatrix, Matrix sourceMatrix1, Matrix sourceMatrix2) {
        this.remainder = remainder;
        this.NO_THREADS = NO_THREADS;
        this.resultMatrix = resultMatrix;
        this.sourceMatrix1 = sourceMatrix1;
        this.sourceMatrix2 = sourceMatrix2;
    }


    @Override
    public void run() {
        for(int colToComputeIndex = 1 + remainder;
            colToComputeIndex <= resultMatrix.getNO_COLUMNS(); colToComputeIndex+= NO_THREADS){
            try {
                Util.computeColumn(
                        resultMatrix,
                        colToComputeIndex,
                        sourceMatrix1, sourceMatrix2
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
