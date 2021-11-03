package com.company;


import java.util.List;

/**
 * @author = Raducu Vlad-Rares
 * vlad.raducu@stud.ubbcluj.ro
 */

public class Main {

    public static void main(String[] args) throws InterruptedException {
	// write your code here
        int VECTOR_SIZE  = 10;
        List<Integer> vector1 = Util.generateVectorElements(VECTOR_SIZE);
        List<Integer> vector2 = Util.generateVectorElements(VECTOR_SIZE);
        final Integer[] FINAL_SUM = {0};

        System.out.println("Vector1: ");
        vector1.forEach(element -> System.out.print(element + " "));
        System.out.println("\nVector2: ");
        vector2.forEach(element -> System.out.print(element + " "));
        System.out.println("\n-------------------------------\n");
        ProducerConsumerQueue<Integer> queue = new ProducerConsumerQueue<>(5);

        /**
         * The producer thread:
         * It iterates through the vectors and it computed the product of coresponding indices
         * After that, it "produces" the value to the queue
         */
        final Runnable producer = () -> {
          for(int index = 0; index < VECTOR_SIZE; index++){
                int product = vector1.get(index) * vector2.get(index);
                System.out.println("COMPUTED IN PRODUCER: " + product);
                queue.produce(product);
          }
        };

        /**
         * The consumer thread:
         * It consumes values from the queue when they are available and it adds them to the sum
         */
        final Runnable consumer = () -> {
            while(true){
                int consumedProduct = queue.consume();
                System.out.println("RECEIVED IN CONSUMER: " + consumedProduct);
                FINAL_SUM[0] += consumedProduct;
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(1000);
        System.out.println("FINAL_SUM = " + FINAL_SUM[0]);
        System.out.println("checkpoint");
    }
}
