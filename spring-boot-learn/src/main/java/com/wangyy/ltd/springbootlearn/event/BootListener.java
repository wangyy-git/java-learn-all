package com.wangyy.ltd.springbootlearn.event;

import com.wangyy.ltd.springbootlearn.controller.MyController;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BootListener implements ApplicationListener<MailSendEvent> {
    @Override
    public void onApplicationEvent(MailSendEvent event) {
        System.out.println("BootListener .... ");
//        event.send("lis");
        System.out.println(event.getTimestamp());
        System.out.println("BootListener end .... ");
    }
}
