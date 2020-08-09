package com.awei.dp.factory.abstractFactory;

public class Main {

    public static void main(String[] args) {
        AbstractFactory af = new ModernFactory();
        Food food = af.createFood();
        food.printName();
        Vehicle vehicle = af.createVehicle();
        vehicle.go();
        Weapon weapon = af.CreateWeapon();
        weapon.fire();
    }

}
