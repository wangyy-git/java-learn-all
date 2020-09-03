package com.wangyy.ltd.springbootlearn.loade;

public class Test {



    public void test(){

        System.out.println(Thread.currentThread());
        System.out.println("商鞅老师 -version 2.0");
        System.out.println(this.getClass().getClassLoader());
    }
}
