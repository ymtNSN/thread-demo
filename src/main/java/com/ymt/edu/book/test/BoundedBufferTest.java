package com.ymt.edu.book.test;

import junit.framework.TestCase;

import static org.junit.Assert.assertTrue;

/**
 * @Description: 基本单元测试
 * @Author: yangmingtian
 * @Date: 2019/6/9
 */
public class BoundedBufferTest extends TestCase {
    void testIsEmptyWhenConstructed() {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        assertTrue(bb.isEmpty());
        assertTrue(bb.isFull());
    }

    void testIsFullWhenConstructed() throws InterruptedException {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            bb.put(i);
        }
        assertTrue(bb.isEmpty());
        assertTrue(bb.isFull());
    }

    public static void main(String[] args) {

    }

}
