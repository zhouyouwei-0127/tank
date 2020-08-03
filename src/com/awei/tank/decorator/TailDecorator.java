package com.awei.tank.decorator;

import com.awei.tank.GameObject;

import java.awt.*;

public class TailDecorator extends GODecorator {

    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        super.paint(g);

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawLine(go.x,go.y,go.x + getWidth(),go.y +getHeight());
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return go.getWidth();
    }

    @Override
    public int getHeight() {
        return go.getHeight();
    }
}
