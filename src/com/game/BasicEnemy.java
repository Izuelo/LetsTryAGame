package com.game;

import java.awt.*;

public class BasicEnemy extends GameObject {

    private Handler handler;

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler =handler;

        velY = 4;
        velX = 4;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 16) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        handler.addObject(new Trail(x,y,ID.Trail,handler,Color.magenta,16,16, 0.05f));
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 16, 16);
    }
}
