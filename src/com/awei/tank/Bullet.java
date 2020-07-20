package com.awei.tank;

import java.awt.*;

/**
 * 子弹类
 */
public class Bullet {

    private int x = 300, y = 300; //子弹位置
    private int width = 30, height = 30; //子弹宽高
    private final int speed = 10; //子弹速度
    private Dir dir = Dir.DOWN; //子弹方向
    private boolean live = true;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    TankFrame tf;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if (!live) {
            tf.bullets.remove(this); //移除死亡的坦克
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null); //画子弹图片
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null); //画子弹图片
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null); //画子弹图片
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null); //画子弹图片
                break;
        }

        move(); //移动
    }

    public void move() {
        //根据方向控制子弹的移动
        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case UP:
                y -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case DOWN:
                y += speed;
                break;
        }
        //如果子弹碰到边界则视为死亡，在Paint方法中将其在集合中移除
        if (x < 0 || y < 0 || x > tf.getWidth() || y > tf.getHeight()) {
            live = false;
        }
    }
}
