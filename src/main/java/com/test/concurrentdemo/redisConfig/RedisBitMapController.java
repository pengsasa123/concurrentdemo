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
        byte[] bytes = get(KEY);
        BitSet bitSet = BitSet.valueOf(bytes);
        // 获取是1的个数
        System.out.println(bitSet.cardinality());
        boolean b = bitSet.get(99998);
        System.out.println(b);

    }

    private Long bitCount(String key) {
        return stringRedisTemplate.execute((RedisCallback<Long>) connection -> connection.bitCount(key.getBytes()));
    }

    private byte[] get(final String key) {
        return stringRedisTemplate.execute((RedisCallback<byte[]>) connection -> connection.get(key.getBytes()));
    }

    /**
     * bitset 用法 (统计活跃用户)
     *
     * @param action 可以理解为key
     * @param dates 时间集合key的组成部分
     * @return
     */
    public int uniqueCount(String action, String... dates) {
        // OR：一个为1返回1-->|
        // XOR：只有一个1返回1否则返回0-->^
        // AND：两个都是1返回1否则返回0-->&
        BitSet all = new BitSet();
        for (String date : dates) {
            String key = action + ":" + date;
            BitSet users = BitSet.valueOf(get(key));
            all.or(users);
        }
        return all.cardinality();
    }

    //public void set(int pos): 位置pos的字位设置为true。
    // public void set(int bitIndex, boolean value) 将指定索引处的位设置为指定的值。
    // public void clear(int pos): 位置pos的字位设置为false。
    // public void clear() : 将此 BitSet 中的所有位设置为 false。
    // public int cardinality() 返回此 BitSet 中设置为 true 的位数。
    // public boolean get(int pos): 返回位置是pos的字位值。
    // public void and(BitSet other): other同该字位集进行与操作，结果作为该字位集的新值。
    // public void or(BitSet other): other同该字位集进行或操作，结果作为该字位集的新值。
    // public void xor(BitSet other): other同该字位集进行异或操作，结果作为该字位集的新值。
    // public void andNot(BitSet set) 清除此 BitSet 中所有的位,set - 用来屏蔽此 BitSet 的 BitSet
    // public int size(): 返回此 BitSet 表示位值时实际使用空间的位数。
    // public int length() 返回此 BitSet 的“逻辑大小”：BitSet 中最高设置位的索引加 1。
    // public int hashCode(): 返回该集合Hash 码， 这个码同集合中的字位值有关。
    // public boolean equals(Object other): 如果other中的字位同集合中的字位相同，返回true。
    // public Object clone() 克隆此 BitSet，生成一个与之相等的新 BitSet。
    // public String toString() 返回此位 set 的字符串表示形式。
}
