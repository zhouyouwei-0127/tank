package com.awei.tank.fireStategy;

import com.awei.tank.Bullet;
import com.awei.tank.GameModel;
import com.awei.tank.Tank;
import com.awei.tank.decorator.TailDecorator;
import com.awei.tank.decorator.RectDecorator;

/**
 * 默认开火策略
 */
public class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.x + tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.y + tank.HEIGHT/2 - Bullet.HEIGHT/2;
        GameModel.getInstance().add(

                        new Bullet(bX,bY,tank.dir,tank.group));
    }
}
