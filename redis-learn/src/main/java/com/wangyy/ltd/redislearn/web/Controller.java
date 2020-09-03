package com.wangyy.ltd.redislearn.web;

import com.wangyy.ltd.redislearn.lock.MyRedisBreakDown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("#{1+2}")
    private int a;
    @Autowired
    private MyRedisBreakDown redisLock;
    
    @RequestMapping(value = "/value")
    public void getValue(){
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    
                    redisLock.getValueByLock("k1");
                }
            }).start();
        }
    }
    
}
