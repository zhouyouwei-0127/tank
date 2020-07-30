package com.awei.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTank = new Tank(300,300,Dir.UP, Group.GOOD,this); //己方坦克
    List<Bullet> bullets = new ArrayList<>(); //子弹集合
    List<Tank> tanks = new ArrayList<>(); //敌方坦克集合--主类中初始化
    List<Explode> explodes = new ArrayList<>(); //爆炸集合

    public GameModel() {
        int initTankCount = Integer.parseInt((String)PropertiesMgr.get("initTankCount"));
        //画敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tanks.add(new Tank(50 + 50*i,300,Dir.DOWN,Group.BAD,this));
        }
    }

    //在窗口需要重新绘制的时候会自动调用此方法
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量：" + bullets.size(),10,60);
        g.drawString("敌人的数量：" + tanks.size(),10,80);
        g.drawString("爆炸的数量：" + explodes.size(),10,100);
        g.setColor(c);

        myTank.paint(g); //坦克自己画自己
        for (int i=0; i<bullets.size(); i++) {
            bullets.get(i).paint(g);//子弹画自己
        }
        for (int i=0; i<tanks.size(); i++) {
            tanks.get(i).paint(g);//敌方坦克画自己
        }
        for (int i=0; i<explodes.size(); i++) {
            explodes.get(i).paint(g);//爆炸画自己
        }

        //碰撞检测
        for (int i=0; i<bullets.size(); i++) {
            for (int j=0; j<tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j)); //判断子弹和坦克是否相撞
            }
        }
    }

    public Tank getMyTank() {
        return myTank;
    }
}
