package com.awei.tank.observer;

import java.io.Serializable;

public interface TankFireObserver extends Serializable {

    void actionFire(TankFireEvent event);
}
