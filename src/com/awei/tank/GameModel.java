package com.awei.tank;

import com.awei.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class GameModel {

    Tank myTank = new Tank(300,300,Dir.UP, Group.GOOD,this); //己方坦克
    private List<GameObject> gameObjects = new ArrayList<>(); //所有物品的集合
    ColliderChain chain = new ColliderChain();

    public GameModel() {
        int initTankCount = Integer.parseInt((String)PropertiesMgr.get("initTankCount"));
        //画敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            add(new Tank(80 + 80*i,300,Dir.DOWN,Group.BAD,this));
        }
    }

    public void add(GameObject go) {
        gameObjects.add(go);
    }

    public void remove(GameObject go) {
        gameObjects.remove(go);
    }

    //在窗口需要重新绘制的时候会自动调用此方法
    public void paint(Graphics g) {

        myTank.paint(g); //主站坦克自己画自己
        //所有物品画自己
        for (int i=0; i<gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }

        //碰撞
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i+1; j < gameObjects.size(); j++) {
                GameObject o1 = gameObjects.get(i);
                GameObject o2 = gameObjects.get(j);
                chain.collide(o1,o2);
            }
        }

    }

    public Tank getMyTank() {
        return myTank;
    }
}
