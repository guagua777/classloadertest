package com.test;

public class ClassLoaderDemo1 {
    public static void main(String[] args) throws Exception{
        //演示类加载器的父子关系
        ClassLoader loader = ClassLoaderDemo1.class.getClassLoader();
        while(loader!=null){
            System.out.println(loader);
            loader = loader.getParent();
        }
    }
}
