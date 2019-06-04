package com.ymt.edu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/5/23
 */
public class LockTest {
    static Lock lock = new ReentrantLock();
   static Condition condition;


    public static void main(String[] args) throws InterruptedException {
        lock.lock();
            condition.await();
            condition.signal();
        lock.unlock();
    }

}
