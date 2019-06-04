package com.ymt.edu.book;

import java.util.Collections;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/2
 */
public class ThreadDemo implements Runnable{

    public static void main(String[] args) {
//        Collections.unmodifiableMap();

//        Collections.synchronizedList()
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
