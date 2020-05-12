package com.wangyy.ltd.redislearn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;


@SpringBootTest
class RedisLearnApplicationTests {

    @Resource(name = "redisFactory")
    JedisConnectionFactory factory;
    
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Test
    void test(){
        System.out.println("host --> " + factory.getHostName());
        System.out.println("host --> " + redisTemplate.opsForValue().get("k1"));
        System.out.println("host --> " + redisTemplate.hasKey("k1"));
//        redisTemplate.opsForValue().set("k1","v1");

    } 
}
