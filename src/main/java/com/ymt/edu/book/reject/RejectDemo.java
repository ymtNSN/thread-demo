package com.ymt.edu.book.reject;

import java.util.concurrent.*;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/8
 */
public class RejectDemo {
    private final ExecutorService exec;

    public RejectDemo() {
        this.exec = new ThreadPoolExecutor(1, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(20));
        if (exec instanceof ThreadPoolExecutor) {
            ((ThreadPoolExecutor) exec).setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        } else {
            throw new AssertionError("Oops,bad assumption");
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.
        System.out.println(executorService);
    }
}
