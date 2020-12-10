package com.test.concurrentdemo.redisConfig;

import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;


/**
 * set demo
 * 应用场景:可以做好友,粉丝,关注,随机展示等
 */
@RestController
@RequestMapping("/redis/set")
public class RedisSetController {

    private static final String KEY = "1234set";
    private static final String OTHER_KEY = "tes4321";
    private static final String RESULT_KEY = "result1234";

    @Resource
    private SetOperations<String, Object> setOperations;

    @GetMapping("/test")
    public void test() {
        // 新增set
        setOperations.add(KEY, 1, 2, 3, 4, 5, 6, 7, 8);
        setOperations.add(OTHER_KEY, 5, 6, 7, 8, 9, 10, 11, 12);
    }

    @GetMapping("/test2")
    public void test2() {
        // 获取集合中所有元素
        getAll(KEY);
        // 判断元素是否在集合中
        Boolean member = setOperations.isMember(KEY, 5);
        System.out.println(member);
        // 获取集合中元素的个数
        Long size = setOperations.size(KEY);
        System.out.println("集合中元素的个数:" + size);
        // 随机获取集合中的元素
        // distinctRandomMembers 随机获得count个不重复的元素 count > 集合大小的时候,返回全部  randomMembers 随机返回count个元素,但是可能重复
        // 两个方法count都必须大于0,区别是一个会返回可以重复的,一个返回不重复的
        List<Object> randomMembers = setOperations.randomMembers(KEY, 3);
        System.out.println("随机获取集合中的元素:" + randomMembers);
        // 差集,返回在第一个set里面不在后面任何一个set里面的元素(这个方法有重载,可传入多个key)
        Set<Object> difference = setOperations.difference(KEY, OTHER_KEY);
        System.out.println("差集:" + difference);
        // 差集,同上,并保留结果到另一个key 如果这个key已存在就覆盖
        Long aLong = setOperations.differenceAndStore(KEY, OTHER_KEY, RESULT_KEY);
        System.out.println("差集,并保留结果:" + aLong);
        getAll(RESULT_KEY);
        // 交集,返回多个set里面都有的元素(这个方法有重载,可传入多个key)
        Set<Object> intersect = setOperations.intersect(KEY, OTHER_KEY);
        System.out.println("交集:" + intersect);
        // 交集,同上,并保留结果到另一个key 如果这个key已存在就覆盖
        Long aLong1 = setOperations.intersectAndStore(KEY, OTHER_KEY, RESULT_KEY);
        System.out.println("交集,并保留结果:" + aLong1);
        getAll(RESULT_KEY);
        // 并集
        Set<Object> union = setOperations.union(KEY, OTHER_KEY);
        System.out.println("并集:" + union);
        // 并集,并保留结果到另一个key 如果这个key已存在就覆盖
        Long aLong2 = setOperations.unionAndStore(KEY, OTHER_KEY, RESULT_KEY);
        System.out.println("并集,并保留结果:" + aLong2);
        getAll(RESULT_KEY);
    }

    private void getAll(String key) {
        Set<Object> members = setOperations.members(key);
        System.out.println(members);
    }

    @GetMapping("/test3")
    public void test3() {
        // 批量移除元素
        Long remove = setOperations.remove(KEY, 1, 2);
        System.out.println("移除元素的个数:" + remove);
        getAll(KEY);
    }

    @GetMapping("/test4")
    public void test4() {
        // 移动元素把一个set里的元素移到另一个set中
        Boolean move = setOperations.move(KEY, 4, OTHER_KEY);
        System.out.println(move);
        getAll(KEY);
        getAll(OTHER_KEY);
    }


}
