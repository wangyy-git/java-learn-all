package com.wangyy.ltd.redislearn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.params.SetParams;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;


@Component
public class RedisLock implements Lock {


//    @Autowired
//    private JedisPool jedisPool;
    @Autowired
    private ValueOperations<String,Object> operations;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    private static final String key="lock";

    private ThreadLocal<String> threadLocal=new ThreadLocal<>();

    private static AtomicBoolean isHappened = new AtomicBoolean(true);

    //加锁
    @Override
    public void lock() {
        boolean b = tryLock();  //尝试加锁
        if(b){
            //拿到了锁
            return;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    //尝试加锁
    @Override
    public boolean tryLock() {
        
        SetParams setParams=new SetParams();
        setParams.ex(2);  //2s
        setParams.nx();
        String s = UUID.randomUUID().toString();
//        Jedis resource = jedisPool.getResource();
        Boolean lock = operations.setIfAbsent(key, s,1,TimeUnit.SECONDS);
//        String lock = resource.set(key,s,"NX","PX",5000);
        
        if(lock){
            //拿到了锁
            threadLocal.set(s);
            if(isHappened.get()){
                new Thread(new MyRUnble(redisTemplate,operations)).start();
                isHappened.set(false);
            }
            return true;
        }
        return false;
    }




    static class MyRUnble implements Runnable{

        private RedisTemplate<String, Object> redisTemplate;
        private ValueOperations<String,Object> operations;

        public MyRUnble(RedisTemplate<String, Object> redisTemplate, ValueOperations<String, Object> operations) {
            this.redisTemplate = redisTemplate;
            this.operations = operations;
        }

        @Override
        public void run() {
            
            while (true){
                Long ttl = redisTemplate.getExpire(key);
                if(ttl!=null && ttl>0){
                    operations.set(key, (int) (ttl+1));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    //第一步判断设置时候的value 和 此时redis的value是否相同
    //解锁
    @Override
    public void unlock() throws Exception{
        String script="if redis.call(\"get\",KEYS[1])==ARGV[1] then\n" +
                "    return redis.call(\"del\",KEYS[1])\n" +
                "else\n" +
                "    return 0\n" +
                "end";

        Boolean delete = redisTemplate.delete(key);
//        Object eval = redisTemplate.execute(script, Arrays.asList(key), Arrays.asList(threadLocal.get()));
//        if(Integer.valueOf(eval.toString())==0){
//            
//            throw new Exception("解锁失败");
//        }

        if(delete){
            throw new Exception("解锁失败");
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}
