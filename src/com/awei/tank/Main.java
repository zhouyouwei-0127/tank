package com.awei.tank;

@SuppressWarnings("all")
public class Main {

    public static void main(String[] args) throws Exception {
        TankFrame tf = new TankFrame();

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
