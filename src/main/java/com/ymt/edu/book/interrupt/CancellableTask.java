package com.ymt.edu.book.interrupt;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/7
 */
public interface CancellableTask<T> extends Callable<T> {
    void cancel();

    RunnableFuture<T> newTask();

}
