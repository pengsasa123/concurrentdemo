package com.test.concurrentdemo.DesignPatterns.factory;

/**
 * 具体创建者 -> HtmlDialog
 */
public class HtmlDialog extends Dialog{

    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
