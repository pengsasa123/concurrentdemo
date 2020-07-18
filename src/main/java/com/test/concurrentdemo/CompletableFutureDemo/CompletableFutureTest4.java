package com.test.concurrentdemo.CompletableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 其他常用API
 *
 * thenCombine:等待两个异步任务都完成后,可以对两个结果值进行处理
 * allof:等待CompletableFuture数组的所有任务完成后才执行
 * anyOf:任何一个任务完成就执行
 */
public class CompletableFutureTest4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("我是第一个任务");
            try {
                Thread.sleep(1000);
                System.out.println("第一个任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("我是第二个任务");
            try {
                Thread.sleep(2000);
                System.out.println("第二个任务完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "20";
        });

        CompletableFuture<Integer> future = future2.thenCombine(future1, (s1, s2) -> {
            System.out.println("两个任务都完成了");
            int i1 = Integer.parseInt(s1);
            return i1 + s2;
        });

        System.out.println(future.get());

        //等待所有任务完成 不然一直阻塞
        //CompletableFuture.allOf(future1, future2).join();
        //其中一个完成就结束
        //CompletableFuture.anyOf(future1, future2).join();

    }
}
