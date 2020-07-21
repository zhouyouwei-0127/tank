package com.awei.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class TankFrame extends Frame {

    static final int GAME_WIDTH = 1200; int GAME_HEIGHT = 900; //窗口的宽高
    Tank myTank = new Tank(300,300,Dir.UP, Group.GOOD,this); //己方坦克
    List<Bullet> bullets = new ArrayList<>(); //子弹集合
    List<Tank> tanks = new ArrayList<>(); //敌方坦克集合--主类中初始化
    List<Explode> explodes = new ArrayList<>(); //爆炸集合

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

    //在窗口需要重新绘制的时候会自动调用此方法
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量：" + bullets.size(),10,60);
        g.drawString("敌人的数量：" + tanks.size(),10,80);
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
                    myTank.fire();
                    break;
                default:
                    break;
            }
        }

        //设置坦克的移动方向
        private void setMainTankDir() {
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
