package com.game;

import java.awt.*;

public class Trail extends GameObject {

    private float alpha =1;
    private Handler handler;
    private Color color;
    private int width;
    private int height;
    private float life;

    public Trail(int x, int y, ID id, Handler handler, Color color, int width, int height, float life) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    public void tick() {
        if (alpha > life){
            alpha -=life -0.0001f;

        }else handler.removeObject(this);

    }


    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));

        g.setColor(color);
        g.fillRect(x, y, width-2, height-2);

        g2d.setComposite(makeTransparent(1));


    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }

    public Rectangle getBounds() {
        return null;
    }
}
