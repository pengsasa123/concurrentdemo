package com.test.concurrentdemo.ReentrantLockTest.demo1;

public class SynchronizedTest {
    public static void main(String[] args) {
        Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 65; i < 91; i++) {
                    System.out.println("----------thread1------- " + (char) i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i < 27; i++) {
                    System.out.println("----------thread2------- " + i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        });
        thread1.start();
        thread2.start();
    }
}
