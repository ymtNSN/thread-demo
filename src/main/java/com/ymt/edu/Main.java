package com.ymt.edu;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/5/20
 */
public class Main {
    public synchronized void demo(){

    }
    public void demo2(){
        synchronized (this){

        }
    }

    public synchronized static void demo3(){

    }

    public void demo4(){
        synchronized(Main.class){

        }
    }
}
