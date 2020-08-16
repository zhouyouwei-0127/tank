package com.awei.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("all")
public class TankFrame extends Frame {

    static final int GAME_WIDTH = 1200, GAME_HEIGHT = 900; //窗口的宽高

    public TankFrame() {
        setSize(GAME_WIDTH,GAME_HEIGHT);  //设置窗口大小
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


    //取消闪烁--把东西先画在一张图片上，然后把图片画到屏幕上
    //repaint方法会先调用update方法，再调用paint方法
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(offScreenImage,0,0,null);
    }

    public void paint(Graphics g) {
        GameModel.getInstance().paint(g);
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
                    setMainTankDir(); //设置方向
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    setMainTankDir(); //设置方向
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    setMainTankDir(); //设置方向
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    setMainTankDir(); //设置方向
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    setMainTankDir(); //设置方向
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    setMainTankDir(); //设置方向
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    setMainTankDir(); //设置方向
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    setMainTankDir(); //设置方向
                    break;
                case KeyEvent.VK_CONTROL:
                    GameModel.getInstance().getMyTank().handleFireKey();
                    break;
                case KeyEvent.VK_S:
                    GameModel.getInstance().save();
                    break;
                case KeyEvent.VK_L:
                    GameModel.getInstance().load();
                    break;
                default:
                    break;
            }
        }

        //设置坦克的移动方向
        private void setMainTankDir() {
            Tank myTank = GameModel.getInstance().getMyTank();
            //如果没有按下方向键，则坦克处于静止状态
            if (!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
            }else {
                myTank.setMoving(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }
    }
}
