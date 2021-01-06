package com.test.concurrentdemo.DesignPatterns.abstractFactory.button;

/**
 * 抽象工厂模式
 *
 * javax.xml.parsers.DocumentBuilderFactory#newInstance()
 *
 * javax.xml.transform.TransformerFactory#newInstance()
 *
 * javax.xml.xpath.XPathFactory#newInstance()
 */
// 第一个产品层次结构
public interface Button {
    void paint();
}
