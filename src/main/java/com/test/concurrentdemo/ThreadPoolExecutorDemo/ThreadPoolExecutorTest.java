package com.test.concurrentdemo.ThreadPoolExecutorDemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义一个线程池
 */
public class ThreadPoolExecutorTest {
    /**
     * 获取处理器数目
     */
    private static int availableProcessors = Runtime.getRuntime().availableProcessors();

    private static AtomicInteger count = new AtomicInteger(1);
    /**
     * 基于LinkedBlockingQueue的容量为500
     */
    private static BlockingQueue queue = new LinkedBlockingQueue(20);

    static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
        availableProcessors * 2,
        availableProcessors * 4 + 1,
        0,
        TimeUnit.SECONDS,
        //阻塞队列
        queue,
        //线程工厂
        r -> new Thread(r, "老王" + count.incrementAndGet() + "号"),
        //异常策略
        (r, executor) -> {
            throw new RuntimeException("队列满了!");
        }
    );

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            poolExecutor.execute(() -> System.out.println(Thread.currentThread().getName()));
        }

        /*for (int i = 0; i < 10; i++) {
            poolExecutor.execute(() -> {
                //如果某个任务执行出现异常，
                // 那么执行任务的线程会被关闭，而不是继续接收其他任务。
                // 然后会启动一个新的线程来代替它。
                if (Thread.currentThread().getName().equals("老王2号")) {
                    int j = 1 / 0;
                }
                System.out.println(Thread.currentThread().getName());
            });
        }*/

        /*poolExecutor.submit(() -> {
            //execute:会抛出异常,submit: 不会抛出异常
            System.out.println(Thread.currentThread().getName());
            int j = 1 / 0;
        });*/

    }

}
