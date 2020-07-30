package com.awei.tank.cor;

import com.awei.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider {

    List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        colliders.add(new BulletTankCollider());
        colliders.add(new TankTankCollider());
    }

    public void add(Collider c) {
        colliders.add(c);
    }

    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (!colliders.get(i).collide(o1,o2)) {
                return false;
            };
        }
        return true;
    }
}