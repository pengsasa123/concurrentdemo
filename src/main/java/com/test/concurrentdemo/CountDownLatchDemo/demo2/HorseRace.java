package com.test.concurrentdemo.CountDownLatchDemo.demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 赛马
 */
class Horse implements Runnable {
    //马的牌号
    private int id ;
    //马的步数
    private int strides = 0;
    private static Random rand = new Random();
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier b, int id) {
        barrier = b;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    //赛马每次随机跑几步
                    strides += rand.nextInt(5);
                }
                barrier.await();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String tracks() {
        StringBuffer s = new StringBuffer();
        for(int i = 0; i < getStrides(); i++) {
            s.append("*");
        }
        s.append(id);
        return s.toString();
    }

    public synchronized int getStrides() { return strides; }
    public String toString() { return "Horse " + id + " "; }

}

public class HorseRace implements Runnable {

    private static final int FINISH_LINE = 75;
    private static List<Horse> horses = new ArrayList<Horse>();
    private static ExecutorService exec = Executors.newCachedThreadPool();

    @Override
    public void run() {
        StringBuffer s = new StringBuffer();
        //打印赛道边界
        for(int i = 0; i < FINISH_LINE; i++) {
            s.append("=");
        }
        System.out.println(s);
        //打印赛马轨迹
        for(Horse horse : horses) {
            System.out.println(horse.tracks());
        }
        //判断是否结束
        for(Horse horse : horses) {
            if(horse.getStrides() >= FINISH_LINE) {
                System.out.println(horse + "won!");
                //手动关闭线程池
                exec.shutdownNow();
                return;
            }
        }
        //休息指定时间再到下一轮
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch(InterruptedException e) {
            System.out.println("barrier-action sleep interrupted");
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(7, new HorseRace());
        for(int i = 0; i < 7; i++) {
            Horse horse = new Horse(barrier, i);
            horses.add(horse);
            exec.execute(horse);
        }
    }

}