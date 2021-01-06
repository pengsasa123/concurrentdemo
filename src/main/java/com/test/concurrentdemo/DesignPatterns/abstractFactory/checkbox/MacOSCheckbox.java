package com.test.concurrentdemo.DesignPatterns.abstractFactory.checkbox;

public class MacOSCheckbox implements Checkbox{
    @Override
    public void paint() {
        System.out.println("You have created MacOSCheckbox.");
    }
}
