package com.awei.db.factory.simpleFactory;

/**
 * Car的工厂
 */
public class CarFactory {

    public Moveable createCar() {
        System.out.println("car created");
        return new Car();
    }
}
