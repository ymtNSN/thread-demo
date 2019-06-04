package com.ymt.edu.book;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/3
 */
public class CelluarAutomata {

    private final Board board;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CelluarAutomata(Board board) {
        this.board = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count, new Runnable() {
            @Override
            public void run() {
                board.commitNewValue();
            }
        });
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {
            workers[i] = new Worker(board.getSubBoard(count, i));
        }
    }

    private class Worker implements Runnable {
        private final Board board;


        public Worker(Board board) {
            this.board = board;
        }

        @Override
        public void run() {
            while (!board.hasConverged()){
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    return;
                } catch (BrokenBarrierException e) {
                    return;
                }
            }
        }
    }

    public void start(){
        for (int i = 0; i < workers.length; i++)
            new Thread(workers[i]).start();
        board.waitWork();
    }

    public static void main(String[] args) {

        int count = Runtime.getRuntime().availableProcessors();
        System.out.println(count);
    }

}
