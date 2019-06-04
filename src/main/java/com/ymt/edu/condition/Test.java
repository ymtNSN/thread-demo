package com.ymt.edu.condition;

import java.io.Serializable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/5/26
 */
public class Test {
    public static void main(String[] args) {
       Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new ConditionAwait(lock,condition).start();
        new ConditionSignal(lock,condition).start();
    }
}
