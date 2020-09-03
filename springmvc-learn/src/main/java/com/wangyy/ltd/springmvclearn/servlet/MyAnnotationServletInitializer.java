package com.wangyy.ltd.springmvclearn.servlet;

import com.wangyy.ltd.springmvclearn.config.SpringContext;
import com.wangyy.ltd.springmvclearn.config.SpringmvcContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyAnnotationServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //获取root容器的所有Class
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringContext.class};
    }
    //获取web容器的所有Class
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringmvcContext.class};
    }
    //springmvc的url
    protected String[] getServletMappings() {
        // / 表示所有请求(除了jsp) /* 任何请求
        return new String[]{"/"};
    }
}
