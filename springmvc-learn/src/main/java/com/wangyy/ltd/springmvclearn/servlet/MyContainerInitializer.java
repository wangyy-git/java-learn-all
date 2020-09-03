package com.wangyy.ltd.springmvclearn.servlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

public class MyContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext context) throws ServletException {
        System.out.println(" ******* ServletContainerInitializer ******* ");

        context.addServlet("my",MyServlet.class).addMapping("/my");
//        context.addFilter("filter","myFilter");
    }
}
