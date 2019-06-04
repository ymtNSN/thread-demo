package com.ymt.edu.condition;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/5/26
 */
public class CyclibarrierDemo extends Thread {

    public void run() {
        System.out.println("开始分析数据");
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException, TimeoutException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new CyclibarrierDemo());
        new Thread(new DataImportThread(cyclicBarrier, "file1")).start();
        new Thread(new DataImportThread(cyclicBarrier, "file2")).start();
        cyclicBarrier.await(4,TimeUnit.SECONDS);
        new Thread(new DataImportThread(cyclicBarrier, "file1")).start();
        new Thread(new DataImportThread(cyclicBarrier, "file2")).start();
        cyclicBarrier.await(4, TimeUnit.SECONDS);
//        cyclicBarrier.await(4,TimeUnit.SECONDS);
//        cyclicBarrier.await();
//        cyclicBarrier.await();
//        cyclicBarrier.await();
//        new Thread(new DataImportThread(cyclicBarrier, "file3")).start();
    }
}
