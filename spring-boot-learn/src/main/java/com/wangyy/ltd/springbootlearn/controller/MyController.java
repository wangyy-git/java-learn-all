package com.wangyy.ltd.springbootlearn.controller;

import com.wangyy.ltd.springbootlearn.event.MailSendEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private MailSendEvent event;
    
    @RequestMapping("/test")
    public void testMonitor(){
        System.out.println("test controller ...");
        System.out.println("test controller ...");
        event.send("jiajia");
    }
}
