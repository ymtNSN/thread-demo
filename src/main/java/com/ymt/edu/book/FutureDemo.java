package com.ymt.edu.book;

import java.util.concurrent.*;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/3
 */
public class FutureDemo {
    private final FutureTask<String> future = new FutureTask<>(() -> "ymt");

    private final Thread thread = new Thread(future);

    public static void main(String[] args) throws InterruptedException {
        FutureDemo futureDemo = new FutureDemo();
        futureDemo.start();
        String s = futureDemo.get();
        System.out.println(s);
    }

    public void start() {
        thread.start();
    }

    public String get() throws InterruptedException {
        try {
//            future.get(5,TimeUnit.SECONDS);
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            throw launderThrowable(cause);
        }
    }

    private static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("not unchecked", t);
        }
    }

}
