package com.test.concurrentdemo.redisConfig;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * hash demo
 * 应用场景:存储对象等
 */
@RestController
@RequestMapping("/redis/hash")
public class RedisHashController {

    private static final String KEY = "123456hash";

    @Resource
    private HashOperations<String, String, Object> hashOperations;


    @GetMapping("/test")
    public void test() {
        // 新增
        hashOperations.put(KEY, "name", "伟哥");
        // 批量新增
        Map<String, Object> map = new HashMap<>();
        map.put("age", "18");
        map.put("sex", "男");
        hashOperations.putAll(KEY, map);
    }

    @GetMapping("/test2")
    public void test2() {
        // 根据key和hashkey获取value
        Object name = hashOperations.get(KEY, "name");
        System.out.println(name);
        // 根据KEY和hashkey的集合获取value集合
        List<Object> objects = hashOperations.multiGet(KEY, Arrays.asList("name", "age", "sex"));
        System.out.println(objects);
        // 获取hashkeys
        Set<String> keys = hashOperations.keys(KEY);
        System.out.println(keys);
        // 获取values
        List<Object> values = hashOperations.values(KEY);
        System.out.println(values);
        // 获取对象,hashkey为key,value为value
        Map<String, Object> entries = hashOperations.entries(KEY);
        System.out.println(entries);
        // 判断hashkey是否存在
        Boolean age = hashOperations.hasKey(KEY, "age");
        System.out.println(age);
        // 返回字符串字节长度
        // 如果给定的键或者域不存在， 返回 0
        // 如果 key 不存在时，返回 0
        Long name1 = hashOperations.lengthOfValue(KEY, "name");
        System.out.println(name1);
        // 获取hash个数
        Long size = hashOperations.size(KEY);
        System.out.println(size);
    }

    @GetMapping("/test3")
    public void test3() {
        // 用于为哈希表中的字段值加上指定增量值。
        // 增量也可以为负数，相当于对指定字段进行减法操作。
        Long increment = hashOperations.increment("hsah654321", "age", 1);
        System.out.println(increment);
        Object name = hashOperations.get("hsah654321", "age");
        System.out.println(name);
        Double increment1 = hashOperations.increment("woyeshi", "age", -0.1D);
        System.out.println(increment1);
        Object name1 = hashOperations.get("woyeshi", "age");
        System.out.println(name1);
    }

    @GetMapping("/test4")
    public void test4() {
        // 批量删除hashkey
        Long delete = hashOperations.delete(KEY, "name", "age");
        System.out.println(delete);
        // 获取hashkeys
        Set<String> keys = hashOperations.keys(KEY);
        System.out.println(keys);
    }
}
