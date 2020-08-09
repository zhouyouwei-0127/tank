package com.awei.dp.singleton;

/**
 * 饿汉式
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全-因为JVM保证每个类只Load内存一次所以不管怎样都只能拿到一个实例
 * 缺点：不管是否用到，类装载时就完成实例化-因为是静态变量
 */

public class Mgr01 {

    //定义一个final修饰的类对象
    private static final Mgr01 INSTANCE = new Mgr01();

    //构造方法为private 别的类不能new此类对象
    private Mgr01() {}

    //只能拿到上面new出来的那个实例
    public static Mgr01 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Mgr01 instance = Mgr01.getInstance();
        Mgr01 instance1 = Mgr01.getInstance();
        System.out.println(instance == instance1);
    }
}
