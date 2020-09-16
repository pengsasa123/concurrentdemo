package com.test.concurrentdemo.DesignPatterns.factory;

/**
 * 基础创建者
 */
public abstract class Dialog {

    public void renderWindow() {
        Button okButton = createButton();
        okButton.render();
    }

    public abstract Button createButton();
}
