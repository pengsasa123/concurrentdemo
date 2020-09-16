package com.test.concurrentdemo.DesignPatterns.decorator;
//具体组件实现类
public class BatterCake extends ABattercake{

    @Override
    protected String getDesc() {
        return "煎饼";
    }

    @Override
    protected int cost() {
        return 8;
    }
}
