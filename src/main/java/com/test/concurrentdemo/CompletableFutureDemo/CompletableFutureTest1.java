package com.test.concurrentdemo.CompletableFutureDemo;

import java.util.concurrent.*;

/**
 * runAsync  和 supplyAsync
 *
 * <li>
 * runAsync : 无返回值
 * supplyAsync : 有返回值
 * </li>
 */
public class CompletableFutureTest1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture.runAsync(() -> System.out.println("我是无返回值的future"));

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("我是有返回值的future");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "future";
        });

        System.out.println(future.get());
        //System.out.println(future.get(1, TimeUnit.SECONDS));
    }
}
