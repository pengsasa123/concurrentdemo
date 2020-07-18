package com.test.concurrentdemo.ReentrantLockTest.demo3;


import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试公平锁与非公平锁
 */
public class ReentTrantLockTest {

    static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(ReentTrantLockTest::test).start();
        }
    }

    public static void test() {
        try {
            System.out.println(Thread.currentThread().getName() + "开始获取锁");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获取到了锁==============");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
