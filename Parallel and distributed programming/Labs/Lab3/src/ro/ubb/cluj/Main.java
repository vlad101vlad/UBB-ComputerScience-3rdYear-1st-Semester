package ro.ubb.cluj;

import ro.ubb.cluj.threadtypes.ColumnThread;
import ro.ubb.cluj.threadtypes.KthElementThread;
import ro.ubb.cluj.threadtypes.RowThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        Matrix matrix1 = Util.generateMatrix(9, 5);
        Matrix matrix2 = Util.generateMatrix(5, 9);

        Matrix resultMatrix = new Matrix(9, 9);
        resultMatrix.zeroMatrix();
        //Util.testBasicMultiplication(matrix1, matrix2, resultMatrix);


        int NO_THREADS = 3;
        int method = 12;
        List<Thread> threadList = new ArrayList<>();
        for(int i = 0; i < NO_THREADS; i++){
            Runnable toBeRun = null;

            RowThread rowThread = new RowThread(i,NO_THREADS, resultMatrix, matrix1, matrix2);
            ColumnThread columnThread = new ColumnThread(i, NO_THREADS, resultMatrix, matrix1, matrix2);
            KthElementThread kthElementThread = new KthElementThread(i , NO_THREADS, resultMatrix, matrix1, matrix2);
            switch (method){
                case 1:
                    toBeRun = rowThread;
                    break;
                case 2:
                    toBeRun = columnThread;
                    break;
                case 3:
                    toBeRun = kthElementThread;
                    break;
                default:
                    System.out.println("Invalid method chosen");
                    break;
            }

            Thread thread = new Thread(toBeRun);
            thread.start();
            threadList.add(thread);
        }

        for(Thread thread: threadList){
            thread.join();
        }

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(NO_THREADS);
        int threadPoolMethod = 3;
        List<Runnable> runnables = new ArrayList<>();
        for(int i = 0; i < NO_THREADS; i++){
            Runnable runnable = null;

            switch (threadPoolMethod){
                case 1:
                    runnable = new RowThread(i, NO_THREADS, resultMatrix, matrix1, matrix2);
                    break;
                case 2:
                    runnable = new ColumnThread(i, NO_THREADS, resultMatrix, matrix1, matrix2);
                    break;
                case 3:
                    runnable = new KthElementThread(i, NO_THREADS, resultMatrix, matrix1, matrix2);
                    break;
                default:
                    System.out.println("Invalid thread pool method");
                    break;
            }
            runnables.add(runnable);
        }
        for(Runnable runnable: runnables){
            threadPoolExecutor.submit(runnable);
        }




        System.out.println(matrix1);
        System.out.println(matrix2);
        System.out.println(resultMatrix);
    }
}
