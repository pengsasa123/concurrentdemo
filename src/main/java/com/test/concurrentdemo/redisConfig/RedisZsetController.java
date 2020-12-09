package com.test.concurrentdemo.redisConfig;

import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

/**
 * zset demo
 */
@RestController
@RequestMapping("/redis/zset")
public class RedisZsetController {

    @Resource
    private ZSetOperations<String, Object> zSetOperations;


    @GetMapping("/test")
    public void test() {
        // 新增zset
        zSetOperations.add("1234", 1, 100);
        zSetOperations.add("1234", 2, 125);
        zSetOperations.add("1234", 3, 75);
        zSetOperations.add("1234", 4, 50);
        zSetOperations.add("1234", 5, 150);
    }

    @GetMapping("/test2")
    public void test2() {
        // 按照下标从小到大
        Set<Object> range = zSetOperations.range("1234", 0, -1);
        System.out.println(range);
        // 按照下标从大到小
        Set<Object> objects = zSetOperations.reverseRange("1234", 0, -1);
        System.out.println(objects);
        // 按照得分区间从小到大
        Set<Object> objects1 = zSetOperations.rangeByScore("1234", 75, 125);
        System.out.println(objects1);
        // 按照得分区间从大到小
        Set<Object> objects2 = zSetOperations.reverseRangeByScore("1234", 75, 125);
        System.out.println(objects2);
        // 从下标offset开始按照得分区间从小到大获取count条数据
        Set<Object> objects3 = zSetOperations.rangeByScore("1234", 50, 100, 0, 2);
        System.out.println(objects3);
        // 从下标offset开始按照得分区间从小到大获取count条数据(并获取得分)
        Set<ZSetOperations.TypedTuple<Object>> typedTuples = zSetOperations.rangeByScoreWithScores("1234", 50, 100, 0, 2);
        typedTuples.forEach(s -> {
            System.out.println(s.getScore());
            System.out.println(s.getValue());
        });
        // 获取得分区间数量
        Long count = zSetOperations.count("1234", 50, 125);
        System.out.println("count----------->" + count);
        // 获取set的大小
        Long size = zSetOperations.size("1234");
        System.out.println("size------------>" + size);
        // 按照从小到大获取下标
        Long rank = zSetOperations.rank("1234", 4);
        System.out.println("rank------------>" + rank);
        // 按照从打到小获取下标
        Long reverseRank = zSetOperations.reverseRank("1234", 4);
        System.out.println("reverseRank------------>" + reverseRank);
    }

    @GetMapping("/test3")
    public void test3() {
        // 给目标增加分数
        Double incrementScore = zSetOperations.incrementScore("1234", 4, 150);
        System.out.println(incrementScore);
    }

    @GetMapping("/test4")
    public void test4() {
        // 批量删除目标
        Long remove = zSetOperations.remove("1234", 1, 2);
        System.out.println(remove);
    }

    @GetMapping("/test5")
    public void test5() {
        // 通过下标区间删除目标
        Long remove = zSetOperations.removeRange("1234", 0, 1);
        System.out.println(remove);
    }

    @GetMapping("/test6")
    public void test6() {
        // 通过得分区间删除目标
        Long remove = zSetOperations.removeRangeByScore("1234", 190, 201);
        System.out.println(remove);
    }
}
