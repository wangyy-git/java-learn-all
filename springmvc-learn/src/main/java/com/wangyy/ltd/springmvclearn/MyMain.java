package com.wangyy.ltd.springmvclearn;

import com.wangyy.ltd.springmvclearn.controller.Controller;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ComponentScan("com.wangyy.ltd.springmvclearn.controller")
public class MyMain {

    public static void main(String[] args) {
        Class<MyMain> myMainClass = MyMain.class;
        ComponentScan scan = myMainClass.getAnnotation(ComponentScan.class);
        String scanPackage = scan.value()[0];
        System.out.println(scanPackage);
        System.out.println(myMainClass.getCanonicalName());
        URL resource = myMainClass.getClassLoader().getResource("");
        System.out.println(resource);
//        System.out.println(Controller.s);
        String replace = scanPackage.replace(".", "/");
        String path = resource.getPath();
        System.out.println(path + replace);
        File file = new File(path + replace);

        System.out.println(Arrays.toString(file.list()));
        
        parseFile(file);
        for (String s : files.keySet()) {
            System.out.println(s + " --> " + files.get(s));
            String filePath = files.get(s);
            try {
                String className = filePath.substring(filePath.indexOf("com"),filePath.indexOf(".")).replace("\\", ".");
                System.out.println(className);
                Class clazz = Class.forName(className);
                Method[] methods = clazz.getDeclaredMethods();
                System.out.println(Arrays.toString(methods));
                Object o = clazz.newInstance();
                Object ss = methods[0].invoke(o, "ss");
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    private static Map<String,String> files = new HashMap<>();
    private static void parseFile(File file){
        
        if (file.isDirectory()){
            String[] list = file.list();
            for (String s : list) {
                File f = new File(file.getAbsolutePath() + "/" + s);
                parseFile(f);
            }
        }else {
            files.put(file.getName(),file.getAbsolutePath());
        }
    } 
}
