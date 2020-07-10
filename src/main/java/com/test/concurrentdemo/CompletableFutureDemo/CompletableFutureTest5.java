package com.test.concurrentdemo.CompletableFutureDemo;

import java.util.concurrent.*;

/**
 * 自定义线程池和默认线程池的区别
 */
public class CompletableFutureTest5 {

    static ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 4, 0,
        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        for (int i = 0; i < 10; i++) {
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("默认线程池: " + Thread.currentThread());
            });
        }

        for (int i = 0; i < 10; i++) {
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("自定义线程池: " + Thread.currentThread());
            }, pool);
        }
    }
}
