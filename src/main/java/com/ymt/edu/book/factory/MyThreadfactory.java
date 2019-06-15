package com.ymt.edu.book.factory;

import java.util.concurrent.ThreadFactory;

/**
 * @Description: 自定义线程工厂
 * @Author: yangmingtian
 * @Date: 2019/6/8
 */
public class MyThreadfactory implements ThreadFactory {
    private final String poolName;

    public MyThreadfactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r, poolName);
    }
}
