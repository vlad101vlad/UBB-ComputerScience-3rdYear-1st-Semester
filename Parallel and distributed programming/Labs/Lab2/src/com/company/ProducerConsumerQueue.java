package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerQueue<T> {
    private Queue<T> queue; // The internal representation for the ProducerConsumerQueue
    private int maxCapacity = 16; //The default maximum capacity for the queue if not given one by the user
    private ReentrantLock lock = new ReentrantLock(true); //Lock for doing thread safe operations in the queeu

    private Condition notEmpty = lock.newCondition(); // Conditional locks for
    private Condition notFull = lock.newCondition(); // implementing the producer-consumer pattern

    public ProducerConsumerQueue(int size) {
        this.queue = new LinkedList<>();
        this.maxCapacity = size;
    }

    public void produce(T item){
        lock.lock(); //We lock so nobody can interfere while we write data
        try{
            if(queue.size() == this.maxCapacity)
                notFull.await();  //IMPORTANT: If the queue is full, we wait for the consumer to take 1 item so we can add

            queue.add(item);
            System.out.println("PRODUCED: " + item);

            //IMPORTANT: After we added an item to the queue, we signal the consumer that he can consume
            //(the queue is not empty)
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // We unlock
        }
    }

    public T consume(){
        lock.lock();//We lock so nobody can interfere while we write data
        try{
            if(queue.size() == 0){
                notEmpty.await(); //IMPORTANT: If the queue is empty, we wait for the producer to produce some item
            }

            T item = queue.remove();
            System.out.println("CONSUMED: " + item);

            //IMPORTANT: After we consumed an item, we notify the producers that the queue is not full and they can
            // continue to produce more items
            notFull.signalAll();
            return item;
        } catch (InterruptedException e) {
            return null;
        } finally {
            lock.unlock(); // We unlocky
        }
    }
}
