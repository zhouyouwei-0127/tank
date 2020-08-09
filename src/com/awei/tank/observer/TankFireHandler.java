package com.awei.tank.observer;

public class TankFireHandler implements TankFireObserver {
    @Override
    public void actionFire(TankFireEvent event) {
        event.getSource().fire();
    }
}
