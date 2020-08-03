package com.awei.tank;

import java.awt.*;

/**
 * 子弹类
 */
public class Bullet extends GameObject {

    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private final int speed = 10; //子弹速度
    private Dir dir; //子弹方向
    public Group group; //敌我标识
    private boolean living = true; //是否存活标识
    public Rectangle rect = new Rectangle(x,y,WIDTH,HEIGHT);

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect.x = x;
        rect.y = y;

        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        if (!living) {
            GameModel.getInstance().remove(this); //移除死亡的坦克
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
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }

        //更新rect
        rect.x = x;
        rect.y = y;
    }

    public void die() {
        living = false;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
