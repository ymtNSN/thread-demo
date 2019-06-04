package com.ymt.edu.book;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/2
 */
public class RunnableDemo implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "打他");
    }
}
