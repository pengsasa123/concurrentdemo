package com.test.concurrentdemo.CountDownLatchDemo.demo1;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    static CountDownLatch countDownLatch = new CountDownLatch(2);

    static ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 4, 0,
        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) throws InterruptedException {

        pool.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Thread-1 执行完毕");
                //计数器减1
                countDownLatch.countDown();
            }
        });

        pool.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Thread-2 执行完毕");
                //计数器减1
                countDownLatch.countDown();
            }
        });

        System.out.println("主线程等待子线程执行完毕");
        System.out.println("计数器值为: " + countDownLatch.getCount());
        //等待
        countDownLatch.await();
        System.out.println("最终计数器值为: " + countDownLatch.getCount());
        System.out.println("主线程执行完毕");

        pool.execute(()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(11);
            }
        });
        //手动关闭线程池
        pool.shutdown();
    }
}
