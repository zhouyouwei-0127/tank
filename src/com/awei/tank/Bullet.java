package com.awei.tank;

import java.awt.*;

/**
 * 子弹类
 */
public class Bullet {

    private int x = 300, y = 300; //子弹位置
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private final int speed = 10; //子弹速度
    private Dir dir = Dir.DOWN; //子弹方向
    private Group group; //敌我标识
    private boolean living = true; //是否存活标识
    GameModel gm;
    Rectangle rect = new Rectangle(x,y,WIDTH,HEIGHT);

    public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gm = gm;

        rect.x = x;
        rect.y = y;

        gm.bullets.add(this);
    }

    public void paint(Graphics g) {
        if (!living) {
            gm.bullets.remove(this); //移除死亡的坦克
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

    //碰撞检测
    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) {
            return;
        }
        if (rect.intersects(tank.rect)) {
            die();
            tank.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            gm.explodes.add(new Explode(eX,eY,gm));
        }
    }

    private void die() {
        living = false;
    }
}
