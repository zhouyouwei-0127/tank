package com.awei.tank.cor;

import com.awei.tank.*;

public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Wall && o2 instanceof Tank) {
            Wall w = (Wall) o1;
            Tank t = (Tank) o2;
            if (w.rect.intersects(t.rect)) {
                t.back();
            }
        } else if (o1 instanceof Tank && o2 instanceof Wall) {
            return collide(o2,o1);
        }
        return true;
    }

}
