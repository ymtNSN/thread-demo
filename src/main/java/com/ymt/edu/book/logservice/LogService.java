package com.ymt.edu.book.logservice;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Description: 可靠的取消操作
 * @Author: yangmingtian
 * @Date: 2019/6/7
 */
public class LogService {

    private final BlockingQueue<String> queue;
    private final LoggerThread logger;
    private final PrintWriter writer;
    private boolean isShutdown;
    private int reservations;

    public LogService(Writer writer) {
        this.queue = new LinkedBlockingDeque<String>(5);
        this.writer = (PrintWriter) writer;
        this.logger = new LoggerThread();
    }

    public void start() {
        logger.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
        }
        logger.interrupt();
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown) {
                throw new IllegalStateException("。。。");
            }
            ++reservations;
        }
        queue.put(msg);
    }

    private class LoggerThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (LogService.this) {
                            if (isShutdown && reservations == 0) {
                                break;
                            }
                        }
                        String msg = queue.take();
                        synchronized (LogService.this) {
                            --reservations;
                        }
                        writer.println(msg);
                    } catch (InterruptedException e) {
                    }
                }
            } finally {
                writer.close();
            }
        }
    }
}