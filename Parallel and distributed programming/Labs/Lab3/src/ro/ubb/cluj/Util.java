package ro.ubb.cluj;

import java.util.List;

public class Util {
    public static Matrix generateMatrix(int noRows, int noColumns){
        Matrix matrix = new Matrix(noRows, noColumns);

        for(int i = 1; i <= noRows; i++){
            for(int j = 1; j <= noColumns; j++){
                matrix.getRow(i).add(j);
            }
        }

        return matrix;
    }

    public static Integer multiply(List<Integer> row, List<Integer> column) throws Exception {
        int result = 0;

        if(row.size() != column.size())
            throw new Exception("Error while multiplying row*column, not the same size\n");

        for(int index = 0; index < row.size(); index++)
            result += row.get(index) * column.get(index);

        return result;
    }

    public static void computeElement(
            Matrix resultMatrix,
            int rowResultIndex, int colResultIndex,
            List<Integer> sourceRow, List<Integer> souceCol
    ) throws Exception {
        resultMatrix
                .getRow(rowResultIndex)
                .set(
                        colResultIndex,
                        Util.multiply(sourceRow, souceCol)
                );
    }

    public static void testBasicMultiplication(
            Matrix sourceMatrix1, Matrix sourceMatrix2, Matrix resultMatrix
    ) throws Exception {
        for(int rowIndex = 1; rowIndex <= sourceMatrix1.getNO_ROWS(); rowIndex++){
            List<Integer> sourceRow = sourceMatrix1.getRow(rowIndex);
            for(int colIndex = 1; colIndex <= sourceMatrix2.getNO_COLUMNS(); colIndex++){
                List<Integer> sourceCol = sourceMatrix2.getColumn(colIndex);

                Util.computeElement(
                        resultMatrix,
                        rowIndex, colIndex-1,
                        sourceRow, sourceCol
                );
            }
        }
    }

    public static void computeRow(
            Matrix resultMatrix,
            int rowResultIndex,
            Matrix sourceMatrix1, Matrix sourceMatrix2
    ) throws Exception {
        for(int colResultIndex = 1; colResultIndex <= resultMatrix.getNO_COLUMNS(); colResultIndex++){
            computeElement(
                    resultMatrix,
                    rowResultIndex, colResultIndex-1,
                    sourceMatrix1.getRow(rowResultIndex), sourceMatrix2.getColumn(colResultIndex)
            );
        }
    }

    public static void computeColumn(
            Matrix resultMatrix,
            int colResultIndex,
            Matrix sourceMatrix1, Matrix sourceMatrix2) throws Exception {
                for(int rowResultIndex = 1; rowResultIndex <= resultMatrix.getNO_ROWS(); rowResultIndex++){
                    computeElement(
                            resultMatrix,
                            rowResultIndex, colResultIndex-1,
                            sourceMatrix1.getRow(rowResultIndex), sourceMatrix2.getColumn(colResultIndex)
                    );
                }
    }

}
