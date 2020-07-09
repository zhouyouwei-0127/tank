package com.awei.tank;

import java.awt.*;

@SuppressWarnings("all")
public class Tank {
    int x = 200,y = 200;  //用变量定义位置，用来控制移动
    private final int speed = 10; //移动速度
    private Dir dir = Dir.UP; //移动方向

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        g.fillRect(x,y,50,50);
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
}
