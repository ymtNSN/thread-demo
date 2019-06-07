package com.ymt.edu.book.logservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 使用ExecutorService的日志服务
 * @Author: yangmingtian
 * @Date: 2019/6/7
 */
public class LogService01 {
    private final ExecutorService exec = Executors.newSingleThreadExecutor();

    public void start() {

    }

    public void stop() {
        try {
            exec.shutdown();
            exec.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
