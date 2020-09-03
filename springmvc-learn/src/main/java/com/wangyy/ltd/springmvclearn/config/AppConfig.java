package com.wangyy.ltd.springmvclearn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Configuration
@ComponentScan("com")
public class AppConfig extends WebMvcConfigurationSupport {
    @Autowired//Spring mvc 环境初始化完之后 去加载这个方法
    public void initArgumentResolvers(   RequestMappingHandlerAdapter requestMappingHandlerAdapter){
        List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>(requestMappingHandlerAdapter.getArgumentResolvers());
        List<HandlerMethodArgumentResolver> customResolvers = requestMappingHandlerAdapter.getCustomArgumentResolvers();
        argumentResolvers.removeAll(customResolvers);
        argumentResolvers.addAll(0, customResolvers);
        requestMappingHandlerAdapter.setArgumentResolvers(argumentResolvers);
        //Vector
    }
    
}
