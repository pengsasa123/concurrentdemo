package com.test.concurrentdemo.ReentrantLockTest.demo4;


import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试中断机制
 */
public class ReentTrantLockTest {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(ReentTrantLockTest::test);
        Thread thread2 = new Thread(ReentTrantLockTest::test);
        thread1.start();
        thread2.start();
        //响应中断
        thread2.interrupt();

    }

    public static void test() {
        try {
            System.out.println(Thread.currentThread().getName() + "开始获取锁");
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + "获取到了锁==============");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
