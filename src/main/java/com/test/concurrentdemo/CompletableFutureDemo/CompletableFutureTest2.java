package com.test.concurrentdemo.CompletableFutureDemo;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * thenApply 和 handle
 * <p>
 *     当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化。
 * <li>
 * handle 方法和 thenApply 方法处理方式基本一样。
 * 不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。
 * thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法。
 * </li>
 */
public class CompletableFutureTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

       /* CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = new Random().nextInt(100);
            System.out.println(result);
            try {
                Thread.sleep(2000);
                int i = 1/0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        }).thenApply(result -> {
            int i = result * 5;
            System.out.println(i);
            return i;
        });

        System.out.println(future.get());*/

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = new Random().nextInt(100);
            System.out.println(result);
            try {
                Thread.sleep(2000);
                int i = 1 / 0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        }).handle((result, e) -> {
            if (e == null) {
                int i = result * 5;
                System.out.println(i);
                return i;
            } else {
                throw new RuntimeException("异常", e);
            }
        });

        System.out.println(future.get());

    }
}
