package com.test.concurrentdemo.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class UserPo {
    private Integer id;

    private String name;

    private Integer age;
}
