package en.ubbcluj.info;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable{
    private final AtomicInteger product;
    private final AtomicInteger sum;

    public Consumer(AtomicInteger product, AtomicInteger sum) {
        this.product = product;
        this.sum = sum;
    }

    @Override
    public void run() {
        this.sum.addAndGet(product.get());
    }
}
