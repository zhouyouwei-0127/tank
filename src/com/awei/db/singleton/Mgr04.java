package com.awei.db.singleton;

/**
 * 静态内部类方式
 * 在一个类被jvm加载时，内部类是不会被加载的，只是在调用的时候加载，所以解决了饿汉模式启动就加载的问题，比枷锁的效率高
 */
public class Mgr04 {

    private Mgr04() {}

    private static class Mgr04Holder {
        private final static Mgr04 INSTANCE = new Mgr04();
    }

    public static Mgr04 getInstance() {
        return Mgr04Holder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Mgr04.getInstance().hashCode());
            }).start();
        }
    }
}
