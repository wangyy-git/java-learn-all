package com.wangyy.ltd.springmvclearn.controller;

import com.wangyy.ltd.springmvclearn.annotation.WController;

@WController
public class Controller {

    public void test(String s){
        System.out.println("Controller --> " + s);
    }
}
