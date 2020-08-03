package com.awei.tank.fireStategy;

import com.awei.tank.Bullet;
import com.awei.tank.Dir;
import com.awei.tank.GameModel;
import com.awei.tank.Tank;
import com.awei.tank.decorator.RectDecorator;
import com.awei.tank.decorator.TailDecorator;

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
            GameModel.getInstance().add(
                    new RectDecorator(
                            new TailDecorator(
                                    new Bullet(bX,bY,dir,tank.group))));
        }
    }
}
