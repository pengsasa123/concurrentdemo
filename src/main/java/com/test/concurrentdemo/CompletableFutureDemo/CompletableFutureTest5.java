package com.test.concurrentdemo.CompletableFutureDemo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池和默认线程池和java8并行流的区别
 * <li>
 *     使用parallelStream时需要注意的一点是，多个parallelStream之间默认使用的是同一个线程池,
 *     所以IO操作尽量不要放进parallelStream中，否则会阻塞其他parallelStream。同理:CompletableFuture 默认线程池
 * </li>
 */
public class CompletableFutureTest5 {

    static ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 4, 0,
        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        //java8并行流
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        list.parallelStream().forEach(i -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("java8并行流: " + Thread.currentThread());
        });

        //CompletableFuture默认线程池
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

        //CompletableFuture自定义线程池
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
