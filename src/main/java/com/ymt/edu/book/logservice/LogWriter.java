package com.ymt.edu.book.logservice;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Description: 不可靠的方式为日志服务增加关闭支持
 * @Author: yangmingtian
 * @Date: 2019/6/7
 */
public class LogWriter {
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;
    private volatile boolean shutdownRequested;

    public LogWriter(Writer writer) {
        this.queue = new LinkedBlockingDeque<String>(5);
        this.logger = new LoggerThread(writer);
    }

    public void start(){
        logger.start();
    }

    public void log(String msg) throws InterruptedException {
        if(!shutdownRequested){
            queue.put(msg);
        }else {
            throw new IllegalStateException("logger is shut down");
        }
    }

    private class LoggerThread extends Thread {
        private final PrintWriter writer;

        public LoggerThread(Writer writer) {
            this.writer = (PrintWriter) writer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    writer.println(queue.take());
                }
            } catch (InterruptedException e) {

            } finally {
                writer.close();
            }
        }
    }
}
