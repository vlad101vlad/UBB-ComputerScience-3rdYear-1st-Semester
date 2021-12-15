package en.ubbcluj.info.service;

import en.ubbcluj.info.domain.Polynomial;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class PolynomialOperations {
    public static Polynomial add(Polynomial term1, Polynomial term2){
        int size;
        List<Integer> sumCoefficients = new ArrayList<>();

        if(term1.getGrade() >= term2.getGrade()){
            size = term1.getGrade();

            for(int index = 0; index < term2.getGrade(); index++)
                sumCoefficients.add(term1.getCoefficients().get(index) + term2.getCoefficients().get(index));
            for(int index = term2.getGrade(); index < term1.getGrade(); index++)
                sumCoefficients.add(term1.getCoefficients().get(index));
            return new Polynomial(size, sumCoefficients);

        }else{
            size = term2.getGrade();

            for(int index = 0; index < term1.getGrade(); index++)
                sumCoefficients.add(term1.getCoefficients().get(index) + term2.getCoefficients().get(index));
            for(int index = term1.getGrade(); index < term2.getGrade(); index++)
                sumCoefficients.add(term2.getCoefficients().get(index));
            return new Polynomial(size, sumCoefficients);
        }
    }

    public static Polynomial substract(Polynomial desubstractor, Polynomial substractor){
        int size;
        List<Integer> differenceCoefficients = new ArrayList<>();

        if(desubstractor.getGrade() >= substractor.getGrade()){
            size = desubstractor.getGrade();

            for(int index = 0; index < substractor.getGrade(); index++)
                differenceCoefficients
                    .add(desubstractor.getCoefficients().get(index) - substractor.getCoefficients().get(index));
            for(int index = substractor.getGrade(); index < desubstractor.getGrade(); index++)
                differenceCoefficients.add(desubstractor.getCoefficients().get(index));
            return new Polynomial(size, differenceCoefficients);
        }else{
            size = substractor.getGrade();

            for(int index = 0; index < desubstractor.getGrade(); index++)
                differenceCoefficients
                    .add(substractor.getCoefficients().get(index) - desubstractor.getCoefficients().get(index));
            for(int index = desubstractor.getGrade(); index < substractor.getGrade(); index++)
                differenceCoefficients.add(substractor.getCoefficients().get(index));
            return new Polynomial(size, differenceCoefficients);
        }
    }

    public static Polynomial multiplySequencially(Polynomial factor1, Polynomial factor2){
        int size = factor1.getGrade() + factor2.getGrade() - 1;
        if(size < 0)
            size = 0;
        List<Integer> productResult = new ArrayList<>();

        for(int index = 0; index < size; index++)
            productResult.add(0);

        for(int iIndex = 0; iIndex < factor1.getGrade(); iIndex++)
            for(int jIndex = 0; jIndex < factor2.getGrade(); jIndex++){
                Integer factorA = factor1.getCoefficients().get(iIndex);
                Integer factorB = factor2.getCoefficients().get(jIndex);
                productResult.set(iIndex + jIndex, productResult.get(iIndex + jIndex) + factorA * factorB);
            }

        return new Polynomial(size, productResult);
    }

    public static Polynomial shift(Polynomial toBeShifted, int by){
        List<Integer> newCoefficient = new ArrayList<>();
        for(int index = 0; index < by; index++)
            newCoefficient.add(0);

        for(int index = 0; index < toBeShifted.getGrade(); index++)
            newCoefficient.add(toBeShifted.getCoefficients().get(index));
        return new Polynomial(toBeShifted.getGrade() + by, newCoefficient);
    }

    public static Polynomial multiplyKaratsuba(Polynomial polynomialA, Polynomial polynomialB){
        if(polynomialA.getGrade() < 2 || polynomialB.getGrade() < 2)
            return PolynomialOperations.multiplySequencially(polynomialA, polynomialB);

        int cuttingPoint = min(polynomialA.getGrade(), polynomialB.getGrade())/2;

        List<Integer> lowAList = polynomialA.getCoefficients().subList(0, cuttingPoint);
        List<Integer> lowBList = polynomialB.getCoefficients().subList(0, cuttingPoint);
        List<Integer> highAList = polynomialA.getCoefficients().subList(cuttingPoint, polynomialA.getGrade());
        List<Integer> highBList = polynomialB.getCoefficients().subList(cuttingPoint, polynomialB.getGrade());
        Polynomial lowA = new Polynomial(lowAList.size(), lowAList);
        Polynomial lowB = new Polynomial(lowBList.size(), lowBList);
        Polynomial highA = new Polynomial(highAList.size(), highAList);
        Polynomial highB = new Polynomial(highBList.size(), highBList);

        Polynomial result1 = PolynomialOperations.multiplyKaratsuba(lowA, lowB);
        Polynomial result2 = PolynomialOperations.multiplyKaratsuba(PolynomialOperations.add(lowA, highA),
            PolynomialOperations.add(lowB, highB));
        Polynomial result3 = PolynomialOperations.multiplyKaratsuba(highA, highB);

        Polynomial r1 = PolynomialOperations.shift(result3, 2 * cuttingPoint);
        Polynomial r2 =
            PolynomialOperations
                .shift(PolynomialOperations
                    .substract(PolynomialOperations.substract(result2, result3), result1), cuttingPoint);

        return PolynomialOperations.add(PolynomialOperations.add(r1, r2), result1);
    }

    public static void multiplicateParralel(Polynomial factor1, Polynomial factor2, List<Integer> productResult,
                                            int startIndex, int endIndex){
        for(int index = startIndex; index < endIndex; index++){
            if(index > productResult.size())
                break;
            int cursor = 0;
            while(cursor <= index){
                if(cursor < factor1.getGrade() && index - cursor < factor2.getGrade())
                    productResult.set(index,
                        productResult.get(index)
                            + factor1.getCoefficients().get(cursor) * factor2.getCoefficients().get(index - cursor));
                cursor++;
            }
        }

    }



    public static AbstractMap.SimpleEntry<Long, Polynomial> multiplySequenciallyParallel(Polynomial factor1, Polynomial factor2) throws InterruptedException {
        int size = factor1.getGrade() + factor2.getGrade() - 1;
        int NR_THREADS = 10;
        System.out.println("!!! Using: " + NR_THREADS + " NR_THREADS");

        int workPerThread = size / NR_THREADS;

        List<Integer> productCoefficinets = new ArrayList<>();
        for(int i = 0; i < size; i++)
            productCoefficinets.add(0);

        List<Thread> threadList = new ArrayList<>();
        int currentIndex = 0;
        while(currentIndex < size){
            int coefficientsToBeComputed = currentIndex + workPerThread;
            final int finalCurrentIndex = currentIndex;
            threadList.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    PolynomialOperations.multiplicateParralel(factor1, factor2, productCoefficinets, finalCurrentIndex, coefficientsToBeComputed);
                }
            }));
            currentIndex += workPerThread;
        }

        long startTime = System.currentTimeMillis();
        for(Thread currentThread: threadList)
            currentThread.start();
        for(Thread currentThread: threadList)
            currentThread.join();
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        return new AbstractMap.SimpleEntry<Long, Polynomial>(elapsed, new Polynomial(size, productCoefficinets));
    }

    public static Polynomial multiplyKaratsubaParallel(int depth, Polynomial factor1, Polynomial factor2) throws InterruptedException, ExecutionException {
        if(depth > 4)
            return PolynomialOperations.multiplySequencially(factor1, factor2);
        if(factor1.getGrade() < 2 || factor2.getGrade() < 2)
            return PolynomialOperations.multiplySequencially(factor1, factor2);

        int cuttingPoint = min(factor1.getGrade(), factor2.getGrade())/2;
        List<Integer> lowAList = factor1.getCoefficients().subList(0, cuttingPoint);
        List<Integer> lowBList = factor2.getCoefficients().subList(0, cuttingPoint);
        List<Integer> highAList = factor1.getCoefficients().subList(cuttingPoint, factor1.getGrade());
        List<Integer> highBList = factor2.getCoefficients().subList(cuttingPoint, factor2.getGrade());
        Polynomial lowA = new Polynomial(lowAList.size(), lowAList);
        Polynomial lowB = new Polynomial(lowBList.size(), lowBList);
        Polynomial highA = new Polynomial(highAList.size(), highAList);
        Polynomial highB = new Polynomial(highBList.size(), highBList);

        int NO_OF_THREADS = 10;
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(NO_OF_THREADS);

        Future<Polynomial> fresult1 = threadPoolExecutor.submit(new Callable<Polynomial>() {
            @Override
            public Polynomial call() throws Exception {
                return PolynomialOperations.multiplyKaratsubaParallel(depth+1, lowA, lowB);
            }
        });
        Future<Polynomial> fresult2 = threadPoolExecutor.submit(new Callable<Polynomial>() {
            @Override
            public Polynomial call() throws Exception {
                return PolynomialOperations
                    .multiplyKaratsubaParallel(depth+1, PolynomialOperations.add(lowA , highA),
                        PolynomialOperations.add(lowB, highB));
            }
        });
        Future<Polynomial> fresult3 = threadPoolExecutor.submit(new Callable<Polynomial>() {
            @Override
            public Polynomial call() throws Exception {
                return PolynomialOperations.multiplyKaratsubaParallel(depth+1, highA, highB);
            }
        });
        threadPoolExecutor.shutdown();
        //threadPoolExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

        Polynomial result1 = fresult1.get();
        Polynomial result2 = fresult2.get();
        Polynomial result3 = fresult3.get();

        Polynomial r1 = PolynomialOperations.shift(result3, 2 * cuttingPoint);
        Polynomial r2 = PolynomialOperations.shift(
            PolynomialOperations.substract(PolynomialOperations.substract(result2, result3), result1
        ), cuttingPoint);
        return PolynomialOperations.add(PolynomialOperations.add(r1, r2), result1);
    }
}
