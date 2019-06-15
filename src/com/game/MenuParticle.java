package com.game;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject {
    private Handler handler;
    private Random r = new Random();
    private Color color;

    public MenuParticle(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255), 50);
        velX = (r.nextInt(7 - -7) + -7);
        velY = (r.nextInt(7 - -7) + -7);
        if (velX == 0) velX = 1;
        if (velY == 0) velY = 1;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
        if (y <= 0 || y >= Game.HEIGHT - 16) velY *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, handler, color, 18, 18, 0.03f));
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}

