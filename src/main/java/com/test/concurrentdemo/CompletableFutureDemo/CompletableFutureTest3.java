package com.test.concurrentdemo.CompletableFutureDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异常处理
 * exceptionally = try catch 里面catch的片段
 * whenComplete = finaly
 */
public class CompletableFutureTest3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

       /* CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("我进来啦");
            int i = 1 / 0;
            System.out.println("我出去啦");
            return 1;
        }).exceptionally(e -> {
            System.out.println("发生异常了");
            System.out.println(e);
            return 2;
        });
        System.out.println(future.get());*/

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("我进来啦");
            int i = 1 / 0;
            System.out.println("我出去啦");
            return 1;
        }).exceptionally(e -> {
            System.out.println("发生异常了");
            System.out.println(e);
            return 2;
        }).whenComplete((r, e) -> {
            System.out.println("result结果是:" + r);
            System.out.println("我出来啦");
        });
        System.out.println("最终结果是:" + future.get());

    }
}
