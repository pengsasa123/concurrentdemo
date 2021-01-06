package com.test.concurrentdemo.DesignPatterns.abstractFactory.factory;

import com.test.concurrentdemo.DesignPatterns.abstractFactory.button.Button;
import com.test.concurrentdemo.DesignPatterns.abstractFactory.checkbox.Checkbox;

/**
 * 抽象工厂
 */
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
