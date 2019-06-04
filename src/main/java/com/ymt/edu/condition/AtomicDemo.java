package com.ymt.edu.condition;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/5/29
 */
public class AtomicDemo {
    static int count;
    static AtomicInteger atomicInteger = new AtomicInteger(0);


    static void incr() {
        atomicInteger.incrementAndGet();
        count++;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            new Thread(AtomicDemo::incr).start();
        }

        Integer integer = new Integer(1);

        AtomicInteger atomicInteger = new AtomicInteger(1);

    }
}
