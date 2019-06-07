package com.ymt.edu.book.poison;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * @Description: 通过”毒丸”对象来关闭服务
 * @Author: yangmingtian
 * @Date: 2019/6/7
 */
public class IndexingService {
    private static final File POISON = new File("");
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;

    public IndexingService(BlockingQueue<File> queue, FileFilter fileFilter, File root) {
        this.queue = queue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    class CrawlerThread extends Thread {
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                while (true) {
                    try {
                        queue.put(POISON);
                        break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 重新重试
                    }
                }
            }
        }

        private void crawl(File root) throws InterruptedException {
            File[] files = root.listFiles(fileFilter);
            for (File f : files) {
                if (f.isDirectory()) {
                    crawl(f);
                } else {
                    queue.put(f);
                }
            }
        }
    }

    class IndexerThread extends Thread {
        public void run() {
            try {
                while (true) {
                    File file = queue.take();
                    if (file == POISON) {
                        break;
                    } else {
                        indexFile(file);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void indexFile(File file) {
        }
    }

}
