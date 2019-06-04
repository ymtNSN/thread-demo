package com.ymt.edu.condition;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Description: TODO
 * @Author: yangmingtian
 * @Date: 2019/6/1
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {

    private ConcurrentHashMap<String, Date> startTimes;

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        startTimes = new ConcurrentHashMap<>();
    }

    @Override
    public void shutdown() {
        System.out.println("已经执行的任务数：" + this.getCompletedTaskCount() + "," + "当前活动线程数：" + this.getActiveCount() +
                ",当前排队线程数：" + this.getQueue().size());
        System.out.println();
        super.shutdown();
    }

    public static ExecutorService newCachedThreadPool(){
        return new MyThreadPoolExecutor(0,Integer.MAX_VALUE,60L,TimeUnit.SECONDS,new SynchronousQueue<>());
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        startTimes.put(String.valueOf(r.hashCode()), new Date());
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Date startDate = startTimes.remove(String.valueOf(r.hashCode()));
        Date finishDate = new Date();
        long diff = finishDate.getTime() - startDate.getTime();
        System.out.print(" 任务耗时:" + diff + "\n");
        System.out.print(" 初始线程数:" + this.getPoolSize() + "\n");
        System.out.print(" 核心线程数:" + this.getCorePoolSize() + "\n");
        System.out.print(" 正在执行的任务数量:" + this.getActiveCount() + "\n");
        System.out.print(" 已经执行的任务数量:" + this.getCompletedTaskCount() + "\n");
        System.out.print(" 任务总数:" + this.getTaskCount() + "\n");
        System.out.print(" 最大允许的线程数:" + this.getMaximumPoolSize() + "\n");
        System.out.print(" 线程空闲时间:" + this.getKeepAliveTime(TimeUnit.MILLISECONDS) + "\n");
        System.out.println();
        super.afterExecute(r, t);
    }
}
