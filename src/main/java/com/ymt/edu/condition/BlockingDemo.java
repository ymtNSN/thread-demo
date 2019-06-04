package com.ymt.edu.condition;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/5/29
 */
public class BlockingDemo {
    BlockingQueue<String> ab = new ArrayBlockingQueue(10);

    {
        init();
    }

    public void init(){
        new Thread(()->{
            try {
                while (true) {
                    String data = ab.take();
                    System.out.println("receive:" + data);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void addData(String data) throws InterruptedException {
        ab.add(data);
        System.out.println("sendData:"+data);
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingDemo blockingDemo = new BlockingDemo();
        for(int i =1;i<1000;i++){
            blockingDemo.addData("data:"+i);
        }
    }
}
