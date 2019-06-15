package com.ymt.edu.book.parallel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @Description: 将串行递归转换为并行递归
 * @Author: yangmingtian
 * @Date: 2019/6/8
 */
public class ParallelDemo {

    // 串行递归
    public <T> void sequentialRecursive(List<Node<T>> nodes, Collection<T> results) {
        for (Node<T> n : nodes) {
            results.add(n.compute());
            sequentialRecursive(n.getChildren(), results);
        }
    }

    //串行递归
    public <T> void parallelRecursive(final Executor executor, List<Node<T>> nodes, final Collection<T> results) {
        for (final Node<T> n : nodes) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    results.add(n.compute());
                }
            });
            parallelRecursive(executor, n.getChildren(), results);
        }

    }

    // 等待通过并行方式计算的结果
    public <T> Collection<T> getParallelResults(List<Node<T>> nodes) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Queue<T> resultQueue = new ConcurrentLinkedQueue<>();
        parallelRecursive(exec, nodes, resultQueue);
        exec.shutdown();
        exec.awaitTermination(60L, TimeUnit.SECONDS);
        return resultQueue;
    }

    private class Node<T> {
        private T val;

        public T compute() {
            return null;
        }

        public List<Node<T>> getChildren() {
            return null;
        }
    }
}
