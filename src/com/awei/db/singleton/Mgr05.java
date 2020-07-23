package com.awei.db.singleton;

/**
 * 不仅可以解决线程安全问题，还可以防止方序列化
 */
public enum Mgr05 {

    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Mgr05.INSTANCE.hashCode());
            }).start();
        }
    }
}
