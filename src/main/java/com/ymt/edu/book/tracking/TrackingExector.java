package com.ymt.edu.book.tracking;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 封装ExectorService中的execute方法，跟踪在关闭之后被取消的任务
 * @Author: yangmingtian
 * @Date: 2019/6/8
 */
public class TrackingExector extends AbstractExecutorService {
    private final ExecutorService exec;
    private final Set<Runnable> taskCancelledAtShutdown = Collections.synchronizedSet(new HashSet<Runnable>());

    public TrackingExector(ExecutorService exec) {
        this.exec = exec;
    }

    public List<Runnable> getCancelledTasks() {
        if (!exec.isTerminated()) {
            throw new IllegalStateException("not Terminated");
        }
        return new ArrayList<Runnable>(taskCancelledAtShutdown);
    }

    @Override
    public void execute(final Runnable runnable) {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } finally {
                    if (isShutdown() && Thread.currentThread().isInterrupted()) {
                        taskCancelledAtShutdown.add(runnable);
                    }
                }
            }
        });

    }

    @Override
    public void shutdown() {
        exec.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        taskCancelledAtShutdown.addAll(exec.shutdownNow());
        return new ArrayList<>(taskCancelledAtShutdown);
    }

    @Override
    public boolean isShutdown() {
        return exec.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return exec.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return exec.awaitTermination(timeout, unit);
    }

}
