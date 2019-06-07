package com.ymt.edu.book.interrupt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/5
 */
public class PrimeGenerator implements Runnable {

    private List<BigInteger> primes = new ArrayList<>();


    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled;

    public PrimeGenerator(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()) {
//            p = p.nextProbablePrime();
                queue.put(p = p.nextProbablePrime());
                synchronized (this) {
                    primes.add(p);
                }
            }
        } catch (InterruptedException e) {
            // 允许线程退出
//            e.printStackTrace();
        }
    }

    public void cancel() {
        interrupt();
//        cancelled = true;
    }

    private void interrupt() {
        Thread.currentThread().interrupt();
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<BigInteger>(primes);
    }

    public static void main(String[] args) throws InterruptedException {
//        PrimeGenerator primeGenerator = new PrimeGenerator(null);
//        new Thread(primeGenerator).start();
//        try {
//            SECONDS.sleep(1);
//        } finally {
//            primeGenerator.cancel();
//        }
//        System.out.println(primeGenerator.get());
////        return primeGenerator.get();
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1);
        getTask(queue);
    }

    public static int getTask(BlockingQueue<Integer> queue) {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    System.out.println(2 / 0);
                    return queue.take();
                } catch (InterruptedException e) {
                    interrupted = true;
                    // 重新尝试
                    break;
                } catch (Exception e) {
                    interrupted = true;
                    break;
                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
        return 0;
    }

}
