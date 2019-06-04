package com.ymt.edu.condition;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/5/26
 */
public class CountDownLatchTest extends Thread{

    static CountDownLatch countDownLatch = new CountDownLatch(1);


    @Override
    public void run(){
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThreadName:"+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        for(int i = 0;i<1000;i++){
            new CountDownLatchTest().start();
        }
        countDownLatch.countDown();


//        new Thread(() -> {
//            System.out.println("Thread 1");
//        }).start();
//        new Thread(() -> {
//            System.out.println("Thread 2");
//        }).start();
//        new Thread(() -> {
//            System.out.println("Thread 3");
//        }).start();
    }


}
