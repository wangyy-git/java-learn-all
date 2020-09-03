package com.wangyy.ltd.springbootlearn.event;

public class EventDemo {
    public void method(Object arg1,Object arg2,EventMonitor monitor){
        //执行特定逻辑
        //指定地方监测参数 调用者去具体实现监测方法
        if (monitor != null)monitor.monitor(arg1);
    }
    public void method(Object arg1,Object arg2){
        method(arg1, arg2,null);
    }
}
interface EventMonitor{
    void monitor(Object arg);
}