package com.wangyy.ltd.redislearn.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

@ConfigurationProperties("student")
public class Student {
    @NotNull
    private String name;
}
