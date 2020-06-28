package com.test.concurrentdemo.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    Dao dao;

    @GetMapping("/test")
    public void test() {
        UserPo userPo = dao.selectById(1);
        userPo.setAge(userPo.getAge() + 1);
        dao.updateById(userPo);
    }












    @GetMapping("/test2")
    public synchronized void test2() {
        UserPo userPo = dao.selectById(1);
        userPo.setAge(userPo.getAge() + 1);
        dao.updateById(userPo);
    }




    static Lock lock = new ReentrantLock();



    @GetMapping("/test3")
    public void test3() {
        try {
            lock.lock();
            UserPo userPo = dao.selectById(1);
            userPo.setAge(userPo.getAge() + 1);
            dao.updateById(userPo);
        } finally {
            lock.unlock();
        }
    }
}
