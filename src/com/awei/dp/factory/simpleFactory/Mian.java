package com.awei.dp.factory.simpleFactory;

public class Mian {

    public static void main(String[] args) {
        Moveable car = new CarFactory().createCar();
        car.go();
    }
}
