package com.test.concurrentdemo.redisConfig;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.BitSet;

/**
 * redis bitmap
 * 应用场景:用户签到,统计活跃用户,用户在线状态等
 */
@RestController
@RequestMapping("/redis/value")
public class RedisBitMapController {

    private static final String KEY = "12345value";

    @Resource
    private ValueOperations<String, Object> valueOperations;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("/test")
    public void test() {
        for (long i = 1; i < 100000; i++) {
            if (i % 2 == 0) {
                valueOperations.setBit(KEY, i, true);
            } else {
                valueOperations.setBit(KEY, i, false);
            }
        }
    }

    @GetMapping("/test2")
    public void test2() {
        // 查询是否存在(理解为是否为1)
        Boolean bit = valueOperations.getBit(KEY, 99998);
        Boolean bit2 = valueOperations.getBit(KEY, 99999);
        System.out.println(bit);
        System.out.println(bit2);
        // 计数 获取为1的个数
        Long aLong = bitCount(KEY);
        System.out.println(aLong);
        // 结合bitset
        byte[] bytes = valueOperations.get(KEY).toString().getBytes();
        BitSet bitSet = BitSet.valueOf(bytes);
        System.out.println(bitSet.length());
        boolean b = bitSet.get(99998);
        System.out.println(b);

    }

    private Long bitCount(String key) {
        return stringRedisTemplate.execute((RedisCallback<Long>) connection -> connection.bitCount(key.getBytes()));
    }
}
