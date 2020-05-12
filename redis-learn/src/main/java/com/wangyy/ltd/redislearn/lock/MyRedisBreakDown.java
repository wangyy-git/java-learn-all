package com.wangyy.ltd.redislearn.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * 解决缓存击穿
 */
@Component
public class MyRedisBreakDown {
    
    @Autowired
    private ValueOperations<String,Object> operations;
    @Autowired
    private RedissonClient redisson;
    
    
    public void getValueByLock(String key){

        String threadName = Thread.currentThread().getName();
        Object jedisValue = operations.get(key);
        
        if (jedisValue != null) {
            jedisValue = operations.get(key);
            System.out.println("key ->" + threadName + "---" + key +" 获取value" + jedisValue);
            return;
        }
        
        RLock lock = redisson.getLock(key);
        lock.lock();
        jedisValue = operations.get(key);
        
        System.out.println("key ->" + threadName + "---" + key +" 第二次从redis中获取jedisValue" + jedisValue);

        
        if (jedisValue == null) {
            System.out.println("从库中取");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //取完后set
            operations.set(key,"lock-" + threadName);
        }

        lock.unlock();
    }

    
}
