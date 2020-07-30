package com.awei.tank.cor;

import com.awei.tank.GameObject;

public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
