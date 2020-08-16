package com.awei.dp.methodTemplate;

public class Main {
    public static void main(String[] args) {
        F f = new C();
        f.m();
    }
}

/**
 * m()中调用了p1()、p2()  在m()被调用时，就会按照p1()--->p2()的顺序执行，m()就可以看做一个模板方法
 */
abstract class F {
    public void m() {
        p1();
        p2();
    }

    abstract void p1();
    abstract void p2();
}

class C extends F {

    @Override
    void p1() {
        System.out.println("p1");
    }

    @Override
    void p2() {
        System.out.println("p2");
    }
}
