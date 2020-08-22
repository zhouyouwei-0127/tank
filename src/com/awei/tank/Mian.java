package com.awei.tank;

@SuppressWarnings("all")
public class Mian {

    public static void main(String[] args) throws Exception {
        TankFrame tf = new TankFrame();
        int initTankCount = Integer.parseInt((String)PropertiesMgr.get("initTankCount"));
        //画敌方坦克
        /*for (int i = 0; i < initTankCount; i++) {
            tf.tanks.add(new Tank(50 + 50*i,300,Dir.DOWN,Group.BAD,tf));
        }*/

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
