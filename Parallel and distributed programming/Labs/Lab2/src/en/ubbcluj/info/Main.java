package en.ubbcluj.info;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int VECTOR_DIMENSION = 5;

        Vector first = Util.generateVectorData(VECTOR_DIMENSION);
        Vector second = Util.generateVectorData(VECTOR_DIMENSION);
        AtomicInteger product = new AtomicInteger(0);
        AtomicInteger sum = new AtomicInteger(0);
        AtomicBoolean available = new AtomicBoolean(true);

        Lock lock = new ReentrantLock();
        Condition conditionVariable = lock.newCondition();


        Thread thread1 = new Thread(new Producer(first, second, product, lock, conditionVariable, available));
        Thread thread2 = new Thread(new Consumer(product, sum));
        thread1.start();
        thread2.start();

    }
}
