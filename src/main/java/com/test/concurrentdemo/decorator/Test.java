package com.test.concurrentdemo.decorator;

public class Test {
    public static void main(String[] args) {
        ABattercake aBattercake = new BatterCake();
        aBattercake = new EggDecorator(aBattercake);
        aBattercake = new EggDecorator(aBattercake);
        aBattercake = new SausageDecorator(aBattercake);

        System.out.println(aBattercake.getDesc() + "销售价格为: " + aBattercake.cost());
    }
}
