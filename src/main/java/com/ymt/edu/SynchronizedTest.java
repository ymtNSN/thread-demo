package com.ymt.edu;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/5/25
 */
public class SynchronizedTest {

    public  void test(){
//        synchronized (SynchronizedTest.class){
            System.out.println("ddd");
//        }
        this.notify();

    }
}
