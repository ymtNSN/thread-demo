package com.ymt.edu;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/5/25
 */
public class Park {
    public static void main(String[] args) {
        LockSupport.park(Thread.currentThread());
        LockSupport.unpark(Thread.currentThread());
        Thread thread = Thread.currentThread();
        thread.interrupt();
        System.out.println("bolck");
    }
}
