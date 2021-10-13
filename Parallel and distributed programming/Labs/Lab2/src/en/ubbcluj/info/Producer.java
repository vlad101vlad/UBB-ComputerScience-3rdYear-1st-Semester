package en.ubbcluj.info;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable{
    private final Vector a;
    private final Vector b;
    private final int VECTOR_DIMENSION;
    private final AtomicInteger product;

    private Lock lock;
    private Condition conditionVariable;
    private AtomicBoolean available;


    public Producer(Vector a, Vector b, AtomicInteger product, Lock lock, Condition conditionVariable, AtomicBoolean available) {
        this.a = a;
        this.b = b;
        this.product = product;
        this.lock = lock;
        this.conditionVariable = conditionVariable;
        this.available = available;
        VECTOR_DIMENSION = this.a.getElements().size();
    }

    @Override
    public void run() {
        List<Integer> aElements = a.getElements();
        List<Integer> bElements = b.getElements();

        for(int index = 1; index <= VECTOR_DIMENSION; index++){
            lock.lock();

            while(!available.get()){
                try {
                    conditionVariable.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            available.set(false);
            this.product.set(aElements.get(index) * bElements.get(index));
            conditionVariable.signalAll();
        }
    }
}
