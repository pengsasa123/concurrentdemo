package com.test.concurrentdemo.DesignPatterns.abstractFactory.factory;

import com.test.concurrentdemo.DesignPatterns.abstractFactory.button.Button;
import com.test.concurrentdemo.DesignPatterns.abstractFactory.button.WindowsButton;
import com.test.concurrentdemo.DesignPatterns.abstractFactory.checkbox.Checkbox;
import com.test.concurrentdemo.DesignPatterns.abstractFactory.checkbox.WindowsCheckbox;

/**
 * 具体工厂 （Windows）
 */
public class WindowsFactory implements GUIFactory{
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
