package com.test.concurrentdemo.DesignPatterns.abstractFactory;

import com.test.concurrentdemo.DesignPatterns.abstractFactory.factory.GUIFactory;
import com.test.concurrentdemo.DesignPatterns.abstractFactory.factory.MacOSFactory;
import com.test.concurrentdemo.DesignPatterns.abstractFactory.factory.WindowsFactory;

public class Demo {

    private static Application configureApplication() {
        Application app;
        GUIFactory factory;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            factory = new MacOSFactory();
            app = new Application(factory);
        } else {
            factory = new WindowsFactory();
            app = new Application(factory);
        }
        return app;
    }

    public static void main(String[] args) {
        Application app = configureApplication();
        app.paint();
    }
}
