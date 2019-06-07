package com.ymt.edu.book.interrupt;

import java.util.concurrent.*;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/6
 */
public class InterruptDemo {
    private static final ScheduledExecutorService cancelExec = new ScheduledThreadPoolExecutor(1);

    public static void timeRun(final Runnable r, long timeout, TimeUnit unit) throws Throwable {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            @Override
            public void run() {
                int i = 0;
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(i++);
                    }
                    System.out.println(00);
                } catch (Throwable t) {
                    this.t = t;
                }

            }

            void rethrow() throws Throwable {
                if (t != null) {
                    throw new Throwable(t.getCause());
                }
            }
        }


        RethrowableTask task = new RethrowableTask();
        Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(() -> taskThread.interrupt(), timeout, unit);
        taskThread.join(unit.toMillis(timeout));
        task.rethrow();

        Future<?> f = cancelExec.submit(task);
        f.get(1, TimeUnit.SECONDS);


    }

    public static void timeRun01(final Runnable r, long timeout, TimeUnit unit) {
        Future<?> f = cancelExec.submit(r);
        try {
            f.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            // 重新抛出异常
        } catch (TimeoutException e) {
            // 任务的取消
            e.printStackTrace();
        }finally {
            // 取消
            f.cancel(true);
        }

    }

    public static void main(String[] args) throws Throwable {
//        timeRun(null,3,TimeUnit.SECONDS);
        int i = 0;
        retry:
        while (true) {
            System.out.println("kkk");
            continue retry;
        }
    }


}
