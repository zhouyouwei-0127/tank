package com.awei.tank.cor;

import com.awei.tank.Bullet;
import com.awei.tank.Explode;
import com.awei.tank.GameObject;
import com.awei.tank.Tank;

public class BulletTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            return collideWith(b,t);
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2,o1);
        }
        return true;
    }

    //碰撞检测
    public boolean collideWith(Bullet b,Tank tank) {
        if (b.group == tank.getGroup()) {
            return true;
        }
        if (b.rect.intersects(tank.rect)) {
            b.die();
            tank.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            b.gm.add(new Explode(eX,eY,b.gm));
            return false;
        }
        return true;
    }
}
