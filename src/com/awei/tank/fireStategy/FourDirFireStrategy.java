package com.awei.tank.fireStategy;

import com.awei.tank.Bullet;
import com.awei.tank.Dir;
import com.awei.tank.Tank;

/**
 * 四个方向开火策略
 */
public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.x + tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.y + tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] values = Dir.values();
        for (Dir dir: values) {
            new Bullet(bX,bY,dir,tank.group,tank.tf);
        }
    }
}
