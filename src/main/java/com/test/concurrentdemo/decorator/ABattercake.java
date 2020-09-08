package com.test.concurrentdemo.decorator;

/**
 * 装饰者模式
 *
 * <li>
 *     例:有一个卖煎饼的店铺找上了你,希望你能给她们的店铺开发一个收银系统,
 *     已知一个煎饼的价格是8元,一个鸡蛋的价格是1元,一根香肠的价格是2元.
 *     但是: 如果加1跟香肠的煎饼,加2个鸡蛋的煎饼,加2个鸡蛋和1跟香肠的煎饼,
 *     如果对每一种可能都写一个实现,会造成类爆炸
 * </li>
 */
//组件类
public abstract class ABattercake {

    protected abstract String getDesc();

    protected abstract int cost();
}
