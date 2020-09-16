package com.test.concurrentdemo.DesignPatterns.factory;

/**
 * 具体创建者
 */
public class WindowsDialog extends Dialog{

    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
