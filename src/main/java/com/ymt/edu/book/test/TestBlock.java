package com.ymt.edu.book.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/9
 */
public class TestBlock {
    public static void testTakeBlocksWhenEmpty() {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
        Thread taker = new Thread() {
            @Override
            public void run() {
                try {
                    int unused = bb.take();
                    fail();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        try {
            taker.start();
            Thread.sleep(1000);
            taker.interrupt();
            taker.join(1000);
            assertFalse(taker.isAlive());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testTakeBlocksWhenEmpty();
    }
}
