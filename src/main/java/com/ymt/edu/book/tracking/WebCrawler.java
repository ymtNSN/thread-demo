package com.ymt.edu.book.tracking;

import java.net.URL;
import java.util.*;
import java.util.concurrent.Executors;

/**
 * @Description: 保存未完成的任务以备后续执行
 * @Author: yangmingtian
 * @Date: 2019/6/8
 */
public abstract class WebCrawler {
    private volatile TrackingExector exec;
    private final Set<URL> urlsToCrawler = new HashSet<>();

    public synchronized void start() {
        exec = new TrackingExector(Executors.newCachedThreadPool());
        for (URL url : urlsToCrawler) {
            submitCrawlerTask(url);
        }
        urlsToCrawler.clear();
    }

    public synchronized void stop() {
        try {
            saveUncrawled(exec.shutdownNow());
        } finally {
            exec = null;
        }

    }

    private void saveUncrawled(List<Runnable> unCrawled) {
        for (Runnable task : unCrawled) {
            urlsToCrawler.add(((CrawlerTask) task).getUrl());
        }
    }

    private void submitCrawlerTask(URL url) {
        exec.execute(new CrawlerTask(url));
    }

    private class CrawlerTask implements Runnable {
        private final URL url;

        CrawlerTask(URL url) {
            this.url = url;
        }

        public URL getUrl() {
            return url;
        }

        @Override
        public void run() {
            for (URL link : processPage(url)) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                submitCrawlerTask(link);
            }
        }
    }

    protected abstract List<URL> processPage(URL url);
}
