package com.awei.tank.cor;

import com.awei.tank.GameObject;
import com.awei.tank.Tank;

public class TankTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.rect.intersects(t2.rect)) {
                t1.x = t1.oldX;
                t1.y = t1.oldY;
                t2.x = t2.oldX;
                t2.y = t2.oldY;
            }
        }
        return true;
    }
}
