package com.awei.dp.singleton;

/**
 * lazyLoading-懒汉式
 * 改造-解决线程不安全问题
 * 在第一次判断时，如果为空，则不往下执行枷锁的代码，所以比直接把锁加在getInstance方法上的效率高
 */
public class Mgr03 {

    private static volatile Mgr03 INSTANCE;

    private Mgr03() {}

    public static  Mgr03 getInstance() {
        if (INSTANCE == null) {
            synchronized (Mgr03.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr03();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Mgr03.getInstance().hashCode());
            }).start();
        }
    }
}
