package com.test.concurrentdemo.CountDownLatchDemo.demo1;

import java.util.concurrent.*;

public class CyclicBarrierTest {

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> System.out.println("一轮结束"));

    static ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 4, 0,
        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        pool.execute(() -> {
            try {
                System.out.println(Thread.currentThread() + "第一回合");
                Thread.sleep(1000);
                cyclicBarrier.await();

                System.out.println(Thread.currentThread() + "第二回合");
                Thread.sleep(1000);
                cyclicBarrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        });

        pool.execute(() -> {
            try {
                System.out.println(Thread.currentThread() + "第一回合");
                Thread.sleep(1000);
                cyclicBarrier.await();

                System.out.println(Thread.currentThread() + "第二回合");
                Thread.sleep(1000);
                cyclicBarrier.await();

                /*//如果再加一个会怎么样?
                Thread.sleep(1000);
                System.out.println("第三回合");
                cyclicBarrier.await();
                System.out.println("第三回合结束");*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        });
        //手动关闭线程池
        pool.shutdown();
    }
}
