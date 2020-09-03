package com.wangyy.ltd.springmvclearn.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.wangyy.ltd.springmvclearn",
                includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),
                useDefaultFilters = false)
@EnableWebMvc
public class SpringmvcContext implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) { }
}
