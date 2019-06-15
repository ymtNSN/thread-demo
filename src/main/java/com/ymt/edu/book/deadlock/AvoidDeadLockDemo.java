package com.ymt.edu.book.deadlock;

import javax.naming.InsufficientResourcesException;

/**
 * @Description: 通过锁顺序来避免死锁
 * @Author: yangmingtian
 * @Date: 2019/6/9
 */
public class AvoidDeadLockDemo {
    private static final Object tieLock = new Object();

    public void transferMoney(final Account fromAcct, final Account toAcct, final int amount) throws InsufficientResourcesException {
        class Helper {
            public void transfer() throws InsufficientResourcesException {
                if ((fromAcct.getBalance() - amount) < 0) {
                    throw new InsufficientResourcesException();
                } else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }
        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);
        if (fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }

}
