package com.test;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

public class Urlclassloadertest {


    public static void main(String[] args) throws Exception{
        File file = new File("/Users/projects/classloadertest/");
        URI uri = file.toURI();
        URL url = uri.toURL();
        URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
        System.out.println(classLoader.getParent());
        Class aClass = classLoader.loadClass("com.test.Demo");
        Object obj = aClass.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().hashCode());
        System.out.println(Demo.class.hashCode());

        Class stringC = classLoader.loadClass("java.lang.String");
        Object str = stringC.newInstance();
        System.out.println("str is " + str.hashCode());

    }



}
