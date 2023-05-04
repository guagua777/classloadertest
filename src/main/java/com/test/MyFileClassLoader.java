package com.test;

import java.io.*;

public class MyFileClassLoader extends ClassLoader {
    private String directory;//被加载的类所在的目录

    /**
     * 指定要加载的类所在的文件目录
     * @param directory
     */
    public MyFileClassLoader(String directory,ClassLoader parent){
        super(parent);
        this.directory = directory;
    }

    public MyFileClassLoader(String directory){
        this.directory = directory;
    }
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            //把类名转换为目录
            String file = directory+File.separator+name.replace(".", File.separator)+".class";
            //构建输入流
            System.out.println("file is : " + file);
            InputStream in = new FileInputStream(file);
            //存放读取到的字节数据
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte buf[] = new byte[1024];
            int len = -1;
            while((len=in.read(buf))!=-1){
                baos.write(buf,0,len);
            }
            byte data[] = baos.toByteArray();
            in.close();
            baos.close();
            return defineClass(name,data,0,data.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) throws Exception {
//        MyFileClassLoader myFileClassLoader = new MyFileClassLoader("/Users/projects/classloadertest/target/classes/", null);
//        //MyFileClassLoader myFileClassLoader = new MyFileClassLoader("/Users/projects/classloadertest/target/classes/");
//        Class clazz = myFileClassLoader.loadClass("com.test.Demo");
//        final Object o = clazz.newInstance();
//        System.out.println(o);
//    }


    public static void main(String[] args) throws ClassNotFoundException {
        // 当传入的parent为null的时候，真实的parent为什么
        MyFileClassLoader myFileClassLoader1 = new MyFileClassLoader("/Users/projects/classloadertest/target/classes/",null);
        final ClassLoader parent = myFileClassLoader1.getParent();
        System.out.println("parent is " + parent);
        MyFileClassLoader myFileClassLoader2 = new MyFileClassLoader("/Users/projects/classloadertest/target/classes/",myFileClassLoader1);
        Class clazz1 = myFileClassLoader1.loadClass("com.test.Demo");
        Class clazz2 = myFileClassLoader2.loadClass("com.test.Demo");
        System.out.println("class1:"+clazz1.hashCode());
        System.out.println("class2:"+clazz2.hashCode());
        //结果:class1和class2的hashCode一致

        MyFileClassLoader myFileClassLoader3 = new MyFileClassLoader("/Users/projects/classloadertest/target/classes/",null);
        MyFileClassLoader myFileClassLoader4 = new MyFileClassLoader("/Users/projects/classloadertest/target/classes/",myFileClassLoader3);
        Class clazz3 = myFileClassLoader3.findClass("com.test.Demo");
        Class clazz4 = myFileClassLoader4.findClass("com.test.Demo");
        System.out.println("class3:"+clazz3.hashCode());
        System.out.println("class4:"+clazz4.hashCode());
        //结果：class1和class2的hashCode不一致

    }
}

