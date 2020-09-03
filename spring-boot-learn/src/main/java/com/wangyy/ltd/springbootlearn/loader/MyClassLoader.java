package com.wangyy.ltd.springbootlearn.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyClassLoader extends ClassLoader {
    private List<String> classNames = new ArrayList<>();
    
    public void scanAndDefineClass(){
        //查找遍历到项目下的所有class文件
        //........
        for (String name :classNames){
            try {
                defineClass(new File(name));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //从classLoader中获取class对象
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        //直接从缓存中获取
        Class<?> loadedClass = findLoadedClass(name);
        if (loadedClass == null){
            //类不存在 或者不需要加载
            if (!classNames.contains(name)){
                //不在项目中 有jvm加载器加载
                getSystemClassLoader().loadClass(name);
            } else {
                throw new ClassNotFoundException("类加载报错");
            }
        }
        return loadedClass;
    }
    //传进当前类的file 加载到classLoader
    public void defineClass(File file) throws Exception {
        InputStream input = new FileInputStream(file);
        byte[] bts = new byte[(int)file.length()];

        String className = "";//这里是类的全限定名com.wangyy.Xxx
        //字节数组转换为Class 类的一个实例 就是将一个class文件加载成对应的Class对应 并且会缓存到classLoader中
        super.defineClass(className, bts, 0, bts.length);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader loader = new MyClassLoader();
        loader.scanAndDefineClass();//指定一个根目录
        Class<?> aClass = loader.loadClass("Xxx"); 
        //截止到这里是通过自己的classLoader来加载并获取class对象 但是使用new的话还是走的AppClassLoader
        //需要使用全盘委托机制
        //全盘委托机制 用于确认默认使用的类加载器(new关键字使用的类加载器)
        //当有new关键字需要类加载器加载的时候 jvm会判断当前调用new关键字的类是哪一个加载器
        // (比如在main方法中 main方法被AppClassLoader加载 则调用new的类加载器也是AppClassLoader)
        //然后调用new关键字对应的类加载器来加载
        //需要自定义个程序入口
        
    }
}
