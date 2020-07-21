package com.awei.tank;

@SuppressWarnings("all")
public class Mian {

    public static void main(String[] args) throws Exception {
        TankFrame tf = new TankFrame();

        //画敌方坦克
        for (int i = 0; i < 5; i++) {
            tf.tanks.add(new Tank(50 + 50*i,300,Dir.DOWN,Group.BAD,tf));
        }

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
