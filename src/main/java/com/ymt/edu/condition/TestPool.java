package com.ymt.edu.condition;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/1
 */
public class TestPool implements Runnable {

    public static void main(String[] args) {
        ExecutorService executorService = MyThreadPoolExecutor.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executorService.execute(new TestPool());
            Future<?> submit = executorService.submit(new TestPool());
            try {
                submit.get();

//                FutureTask futureTask = new FutureTask<>();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
