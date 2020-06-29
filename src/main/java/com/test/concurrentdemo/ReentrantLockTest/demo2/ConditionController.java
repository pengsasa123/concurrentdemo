package com.test.concurrentdemo.ReentrantLockTest.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConditionController {

    @Autowired
    BoundedQueue boundedQueue;

    @GetMapping("/put")
    public void put(int i) throws InterruptedException {
        boundedQueue.put(i);
    }


    @GetMapping("/get")
    public void get() throws InterruptedException {
        while (true) {
            Object o = boundedQueue.get();
            System.out.println(o);
        }
    }
}
