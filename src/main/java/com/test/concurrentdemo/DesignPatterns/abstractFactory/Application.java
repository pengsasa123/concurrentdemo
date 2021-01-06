package com.test.concurrentdemo.DesignPatterns.abstractFactory;

import com.test.concurrentdemo.DesignPatterns.abstractFactory.button.Button;
import com.test.concurrentdemo.DesignPatterns.abstractFactory.checkbox.Checkbox;
import com.test.concurrentdemo.DesignPatterns.abstractFactory.factory.GUIFactory;

public class Application  {

    private Button button;

    private Checkbox checkbox;

    public Application (GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}
