//定义一个类，注意包名
package java.lang;

public class MyObject {

    //加载该类
    public static void main(String[] args) {
        Class clazz = MyObject.class;
        System.out.println(clazz.getClassLoader());
    }
}
