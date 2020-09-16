package com.test.concurrentdemo.DesignPatterns.factory;

/**
 * 工厂方法
 * <li>
 *     核心 Java 程序库中有该模式的应用：
 * java.util.Calendar#getInstance()
 * java.util.ResourceBundle#getBundle()
 * java.text.NumberFormat#getInstance()
 * java.nio.charset.Charset#forName()
 * java.net.URLStreamHandlerFactory#createURLStreamHandler(String) （根据协议返回不同的单例对象）
 * java.util.EnumSet#of()
 * javax.xml.bind.JAXBContext#createMarshaller() 及其他类似的方法。
 * </li>
 */

//通用产品接口
public interface Button {
    void render();

    void onClick();
}
