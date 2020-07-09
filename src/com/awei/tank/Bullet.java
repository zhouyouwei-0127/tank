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

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,width,height); //画图形
        g.setColor(c);

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
    }
}
