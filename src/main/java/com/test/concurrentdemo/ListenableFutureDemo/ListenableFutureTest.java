package com.test.concurrentdemo.ListenableFutureDemo;


import com.google.common.util.concurrent.*;

import java.util.concurrent.Executors;

/**
 * 应用场景:比如说: 我想快速的返回前端,然后异步得到的结果 根据结果去处理
 * 如果是正常情况下 我们想异步计算出结果然后根据结果处理的话得阻塞线程
 * ListenableFuture是加入回调函数 成功了走onSuccess, 失败了走onFailure  完美~~~
 */
public class ListenableFutureTest {

    public static void main(String[] args) {
        //把线程池转成 ListeningExecutorService的实例
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(4));
        //像线程池提交任务，并得到ListenableFuture
        ListenableFuture<String> future = executorService.submit(() -> {
            System.out.println("come on");
            Thread.sleep(2000);
            int i =1/0;
            return "123";
        });

        //可以通过addListener对listenableFuture注册回调，但是通常使用Futures中的工具方法
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t);
                System.out.println("发生异常,执行回滚");
            }
        });

        System.out.println("主线程结束");
    }
}
