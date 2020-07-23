package com.awei.db.factory.simpleFactory;

public class Mian {

    public static void main(String[] args) {
        Moveable car = new CarFactory().createCar();
        car.go();
    }
}
