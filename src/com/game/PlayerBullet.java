package com.game;

import java.awt.*;
import java.util.Random;

public class PlayerBullet extends GameObject {
    private Handler handler;
    private Random r = new Random();

    public PlayerBullet(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = 0;
        velY = -4;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y >= Game.HEIGHT) handler.removeObject(this);
        handler.addObject(new Trail(x, y, ID.Trail, handler, Color.cyan, 18, 18, 0.09f));
    }

    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
