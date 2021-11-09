package ro.ubb.cluj;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        Matrix matrix1 = Util.generateMatrix(9, 5);
        Matrix matrix2 = Util.generateMatrix(5, 9);

        Matrix resultMatrix = new Matrix(9, 9);
        resultMatrix.zeroMatrix();;
        Util.testBasicMultiplication(matrix1, matrix2, resultMatrix);

        System.out.println(matrix1);
        System.out.println(matrix2);
        System.out.println(resultMatrix);
    }
}
