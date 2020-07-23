package com.awei.db.factory.simpleFactory;

public class Plan implements Moveable {

    @Override
    public void go() {
        System.out.println("plan goind");
    }
}
