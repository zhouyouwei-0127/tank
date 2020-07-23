package com.awei.db.factory.abstractFactory;

public class ModernFactory extends AbstractFactory {

    @Override
    Food createFood() {
        return new Bread();
    }

    @Override
    Vehicle createVehicle() {
        return new Car();
    }

    @Override
    Weapon CreateWeapon() {
        return new AK47();
    }
}
