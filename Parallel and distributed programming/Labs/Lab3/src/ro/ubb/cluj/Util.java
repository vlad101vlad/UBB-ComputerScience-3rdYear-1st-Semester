package ro.ubb.cluj;

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
}
