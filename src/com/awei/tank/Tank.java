package com.awei.tank;

import com.awei.tank.fireStategy.DefaultFireStrategy;
import com.awei.tank.fireStategy.FireStrategy;
import com.awei.tank.fireStategy.FourDirFireStrategy;

import java.awt.*;
import java.util.Random;

@SuppressWarnings("all")
public class Tank {

    public int x,y;  //用变量定义位置，用来控制移动
    public static int WIDTH = ResourceMgr.goodTankD.getWidth(); //图片的宽度
    public static int HEIGHT = ResourceMgr.goodTankD.getHeight(); //图片的高度
    private final int speed = 5; //移动速度
    public Dir dir = Dir.UP; //移动方向
    private boolean moving = true; //坦克状态--静止或移动
    private boolean living = true; //是否存活
    public Group group; //敌我标识
    public TankFrame tf = null;
    private Random random = new Random();
    Rectangle rect = new Rectangle(x,y,WIDTH,HEIGHT);
    FireStrategy fs; //坦克开火的策略

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = x;
        rect.y = y;

        //根据坦克的敌我属性更换不同的开火策略
        if (group == Group.GOOD) {
            fs = new FourDirFireStrategy();
        } else {
            fs = new DefaultFireStrategy();
        }
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.tanks.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL,x,y,null); //画坦克图片
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU,x,y,null); //画坦克图片
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR,x,y,null); //画坦克图片
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD,x,y,null); //画坦克图片
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
            default:
                break;
        }

        if (group == Group.BAD && random.nextInt(100) > 95) { //敌方坦克随机发射子弹
            fire();
        }
        if (group == Group.BAD && random.nextInt(100) > 95) { //敌方坦克随机移动
            randomDir();
        }

        //边界检测
        boundsCheck();

        //更新rect
        rect.x = x;
        rect.y = y;
    }

    private void boundsCheck() {
        if (x < 2) x = 2;
        if (y < 30) y = 30;
        if (x > TankFrame.GAME_WIDTH - Tank.WIDTH + 2) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        if (y > TankFrame.GAME_HEIGHT - Tank.HEIGHT + 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT + 2;
    }

    private void randomDir() {
        dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fs.fire(this);
    }

    public void die() {
        living = false;
    }
}
