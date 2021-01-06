package com.test.concurrentdemo.DesignPatterns.abstractFactory.factory;

import com.test.concurrentdemo.DesignPatterns.abstractFactory.button.Button;
import com.test.concurrentdemo.DesignPatterns.abstractFactory.button.MacOSButton;
import com.test.concurrentdemo.DesignPatterns.abstractFactory.checkbox.Checkbox;
import com.test.concurrentdemo.DesignPatterns.abstractFactory.checkbox.MacOSCheckbox;

/**
 * 具体工厂(macOS)
 */
public class MacOSFactory implements GUIFactory{
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
