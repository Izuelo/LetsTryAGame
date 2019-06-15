package com.game;

import java.awt.*;

public class BasicEnemy extends GameObject {
    private Handler handler;

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 4;
        velY = 4;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
        if (y <= 0 || y >= Game.HEIGHT - 16) velY *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, handler, Color.red, 18, 18, 0.05f));
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
