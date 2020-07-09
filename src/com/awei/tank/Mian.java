package com.awei.tank;

@SuppressWarnings("all")
public class Mian {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
