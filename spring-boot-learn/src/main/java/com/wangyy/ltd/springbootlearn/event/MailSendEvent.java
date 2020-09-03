package com.wangyy.ltd.springbootlearn.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
public class MailSendEvent extends ApplicationEvent {
    
    public MailSendEvent(ApplicationContext source) {
        super(source);
    }

    public void send(String address){
        System.out.println("send mail event..." + address);
        
    }
}
