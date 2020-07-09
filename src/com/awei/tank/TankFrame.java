package com.awei.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("all")
public class TankFrame extends Frame {

    Tank myTank = new Tank();

    public TankFrame() {
        setSize(800,600);  //设置窗口大小
        setResizable(false); //设置大小不能改变
        setTitle("tank war"); //设置标题
        setVisible(true); //设置窗口可见

        //添加键盘的监听
        this.addKeyListener(new MyKeyListener());

        //窗口的监听事件
        addWindowListener(new WindowAdapter() {
            //监听窗口的关闭操作
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //在窗口需要重新绘制的时候会自动调用此方法
    @Override
    public void paint(Graphics g) {
        //坦克自己画自己
        myTank.paint(g);
    }

    //键盘监听处理类，根据按键控制坦克的移动方向
    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir(); //设置方向
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir(); //设置方向
        }

        //设置坦克的移动方向
        private void setMainTankDir() {
            if (bL) myTank.setDir(Dir.LEFT);
            if (bU) myTank.setDir(Dir.UP);
            if (bR) myTank.setDir(Dir.RIGHT);
            if (bD) myTank.setDir(Dir.DOWN);
        }
    }
}
