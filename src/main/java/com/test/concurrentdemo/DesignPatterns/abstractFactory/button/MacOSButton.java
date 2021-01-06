package com.test.concurrentdemo.DesignPatterns.abstractFactory.button;

/**
 * mac按钮
 */
public class MacOSButton implements Button{
    @Override
    public void paint() {
        System.out.println("You have created MacOSButton.");
    }
}
