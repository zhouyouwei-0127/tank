package com.awei.tank;

import com.awei.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class GameModel {

    private static final GameModel INSTANCE = new GameModel();

    Tank myTank;
    private List<GameObject> gameObjects = new ArrayList<>(); //所有物品的集合
    ColliderChain chain = new ColliderChain();

    private GameModel() {

    }

    public static GameModel getInstance() {
        return INSTANCE;
    }

    static {
        INSTANCE.init();
    }

    private void init() {
        myTank = new Tank(200,200,Dir.UP, Group.GOOD); //己方坦克
        int initTankCount = Integer.parseInt((String)PropertiesMgr.get("initTankCount"));
        //画敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            new Tank(80 + 80*i,500,Dir.DOWN,Group.BAD);
        }

        //初始化墙
        new Wall(150,150,200,50);
        new Wall(550,150,200,50);
        new Wall(300,300,50,200);
        new Wall(550,300,50,200);
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