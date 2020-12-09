package com.test.concurrentdemo.redisConfig;

import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisDemo {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ValueOperations<String, String> valueOperations;
    @Resource
    private HashOperations<String, String, Object> hashOperations;
    @Resource
    private ListOperations<String, Object> listOperations;
    @Resource
    private SetOperations<String, Object> setOperations;
    @Resource
    private ZSetOperations<String, Object> zSetOperations;


    @GetMapping("/test")
    public void test() {
        zSetOperations.add("1234", 1, 100);
        zSetOperations.add("1234", 2, 125);
        zSetOperations.add("1234", 3, 75);
        zSetOperations.add("1234", 4, 50);
        zSetOperations.add("1234", 5, 150);
    }
}
