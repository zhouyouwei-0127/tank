package com.awei.tank;

import java.awt.*;

@SuppressWarnings("all")
public class Tank {

    private int x = 200,y = 200;  //用变量定义位置，用来控制移动
    private final int speed = 5; //移动速度
    private Dir dir = Dir.UP; //移动方向
    private static boolean moving = false; //坦克状态--静止或移动
    public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();
    TankFrame tf;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }
    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null); //画坦克图片
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null); //画坦克图片
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null); //画坦克图片
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null); //画坦克图片
                break;
        }
        move(); //移动
    }

    public void move() {
        if (!moving) {
            return;
        }
        //根据方向控制坦克的移动
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
    }

    public void fire() {
        int bx = x + WIDTH/2 - Bullet.WIDTH/2;
        int by = y + HEIGHT/2 - Bullet.HEIGHT/2;
        tf.bullets.add(new Bullet(bx,by,dir,tf));
    }
}
