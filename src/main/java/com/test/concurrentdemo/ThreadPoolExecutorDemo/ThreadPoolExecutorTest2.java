package com.test.concurrentdemo.ThreadPoolExecutorDemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义一个线程池
 */
public class ThreadPoolExecutorTest2 {
    /**
     * 获取处理器数目
     */
    private static int availableProcessors = Runtime.getRuntime().availableProcessors();

    private static AtomicInteger count = new AtomicInteger(1);
    /**
     * 基于LinkedBlockingQueue
     */
    private static BlockingQueue queue = new LinkedBlockingQueue(20);

    static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
        availableProcessors * 2,
        availableProcessors * 2,
        0,
        TimeUnit.SECONDS,
        //阻塞队列
        queue,
        //线程工厂
        new MyTheadFactory("qingzu")
    );

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            poolExecutor.execute(() -> System.out.println(Thread.currentThread().getName()));
        }
    }

}
