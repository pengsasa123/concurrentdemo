package com.test.concurrentdemo.redisConfig;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * list demo
 * 应用场景:消息队列,最新列表,定时计算的排行榜等
 */
@RestController
@RequestMapping("/redis/list")
public class RedisListController {

    private static final String KEY = "12345list";
    private static final String OTHER_KEY = "tsil54321";

    @Resource
    private ListOperations<String, Object> listOperations;


    @GetMapping("/test")
    public void test() {
        //从左边添加数据
        listOperations.leftPushAll(KEY, 1, 2, 3, 4);
        listOperations.rightPushAll(KEY, 1, 2, 3, 4);
    }

    @GetMapping("/test2")
    public void test2() {
        // 获取下标范围list的值
        getAll();
        // 查找list的集合大小
        Long size = listOperations.size(KEY);
        System.out.println("集合大小:" + size);
        // 根据索引获取数据
        Object index = listOperations.index(KEY, 5);
        System.out.println("index:" + index);
    }

    @GetMapping("/test3")
    public void test3() {
        // 从左边找第一个1的元素前,加入元素0
        listOperations.leftPush(KEY, 1, 0);
        // 从右边找第一个1的元素后,加入元素0
        listOperations.rightPush(KEY, 1, 0);
        getAll();
    }

    @GetMapping("/test4")
    public void test4() {
        // 在下标3的位置设置值为-1
        listOperations.set(KEY, 3, -1);
        getAll();
    }

    @GetMapping("/test5")
    public void test5() {
        // 删除1个1 count可正负,表示从左或右删除,如果数量为0,表示删除全部给定值相等的元素
        listOperations.remove(KEY, 1, 1);
        getAll();
    }

    @GetMapping("/test6")
    public void test6() {
        // 保留指定下标的元素
        listOperations.trim(KEY, 2, 7);
        getAll();
    }

    @GetMapping("/test7")
    public void test7() {
        // 从右边移除元素并获取列表的第一个元素(重载方法:如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。)
        Object o = listOperations.rightPop(KEY);
        System.out.println(o);
        getAll();
        // 从左边移除元素并获取列表的第一个元素(重载方法:如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。)
        Object o1 = listOperations.leftPop(KEY);
        System.out.println(o1);
        getAll();
    }

    @GetMapping("/test8")
    public void test8() {
        // 将一个元素从一个列表转移到另一个列表(重载方法:如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。)
        Object o = listOperations.rightPopAndLeftPush(KEY, OTHER_KEY);
        System.out.println(o);
        getAll();

        List<Object> range = listOperations.range(OTHER_KEY, 0, -1);
        System.out.println(range);
    }

    private void getAll() {
        List<Object> range = listOperations.range(KEY, 0, -1);
        System.out.println(range);
    }
}
