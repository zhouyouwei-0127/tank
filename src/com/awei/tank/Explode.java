package com.awei.tank;

import java.awt.*;

/**
 * 子弹类
 */
public class Explode extends GameObject {

    private int x = 200, y = 200; //位置
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if (step == ResourceMgr.explodes.length) {
            GameModel.getInstance().remove(this);
        }
    }
}
