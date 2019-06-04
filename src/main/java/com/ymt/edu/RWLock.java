package com.ymt.edu;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/5/23
 */
public class RWLock {
    static ReentrantReadWriteLock wrl = new ReentrantReadWriteLock();
    static Lock read = wrl.readLock();
    static Lock write = wrl.writeLock();

    public static void main(String[] args) {
        wrl.readLock();

        wrl.writeLock();
    }
}
