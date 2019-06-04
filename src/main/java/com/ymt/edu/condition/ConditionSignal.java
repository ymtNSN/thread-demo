package com.ymt.edu.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/5/26
 */
public class ConditionSignal extends Thread{
    private Lock lock;
    private Condition condition;

    public ConditionSignal(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }
    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("signal before");
            condition.signal();
            System.out.println("signal after");
        } finally {
            lock.unlock();
        }
    }
}
