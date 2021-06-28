package com.mao;

import com.mao.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class BlogApplicationTests {

//    @Autowired
//    private RedisTemplate<String,Object> myRedisTemplate;
//    @Test
//    void contextLoads() {
//        myRedisTemplate.opsForValue().set("hh",1);
//        System.out.println(myRedisTemplate.opsForValue().get("hh"));
//        myRedisTemplate.opsForValue().increment("hh");
//        System.out.println(myRedisTemplate.opsForValue().get("hh"));
//    }
//    @Autowired
//    private RedisUtil redisUtil;
//    @Test
//    void contextLoads() {
//        System.out.println(redisUtil.get("hh"));
//    }

}
