package com.awei.tank;

import java.awt.*;

public class Wall extends GameObject {

    int h,w;
    public Rectangle rect;

    public Wall(int x, int y, int h, int w) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;

        rect = new Rectangle(x,y,w,h);

        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x,y,w,h);
        g.setColor(color);
    }

    @Override
    public int getWidth() {
        return x;
    }

    @Override
    public int getHeight() {
        return y;
    }
}
