package com.ymt.edu;

import java.time.temporal.Temporal;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/5/25
 */
public class InterruptTest {
    static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                   i++;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    return;
                }
            }
            System.out.println(i);
        }, "interruptDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }
}
