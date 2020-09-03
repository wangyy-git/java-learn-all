package com.wangyy.ltd.springbootlearn.container;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

public class Tomcat {
    
    @Bean
    public TomcatServletWebServerFactory servletWebServerFactory(){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.setPort(80);
        
        return tomcat;
    }
    
    @Bean
    public WebServerFactoryCustomizer customizer(){
        //入参factory是正在使用的容器的WebServerFactory
        WebServerFactoryCustomizer customizer = factory -> {
            TomcatServletWebServerFactory tomcat = (TomcatServletWebServerFactory) factory;
            tomcat.setPort(80);
        };
        return customizer;
    }
    
    
}
