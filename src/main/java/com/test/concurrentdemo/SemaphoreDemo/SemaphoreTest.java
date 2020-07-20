package com.test.concurrentdemo.SemaphoreDemo;

import java.util.concurrent.Semaphore;

/**
 * 信号量 是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理的使用公共资源
 * 可以理解为加强的Synchronized
 * 也可以用来限流!
 * 内部也是基于AQS,属于共享模式
 */
public class SemaphoreTest {
    //初始化3个许可证的 信号量
    static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    //获取一个许可证 :-1操作
                    semaphore.acquire();
                    System.out.println("我是" + finalI + "号.");
                    //剩余的许可证数量
                    System.out.println(semaphore.availablePermits());
                    Thread.sleep(2000);
                    //释放一个许可证 :+1操作
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
