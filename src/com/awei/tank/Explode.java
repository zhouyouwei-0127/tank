package com.awei.tank;

import java.awt.*;

/**
 * 子弹类
 */
public class Explode {

    private int x = 200, y = 200; //位置
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private int step = 0;
    TankFrame tf = null;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if (step == ResourceMgr.explodes.length) {
            tf.explodes.remove(this);
        }
    }
}
