package com.wangyy.ltd.redislearn.redisTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;

@SpringBootTest
public class RedisTest {

    @Autowired
    private ValueOperations<String,Object> operations;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Test
    @SuppressWarnings("unchecked")
    void test(){
        //RedisTemplate<K, V> extends RedisAccessor implements RedisOperations<K, V>
        SessionCallback callback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.multi();
                redisOperations.opsForValue().set("multi1", "multi1");
                redisOperations.opsForValue().set("multi2", "multi1");
                redisOperations.watch("multi1");
                return redisOperations.exec();
            }
        };

        redisTemplate.execute(callback);
//        HashMap
        
    }

}
