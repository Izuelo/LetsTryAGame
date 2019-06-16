package com.game;

import java.awt.*;
import java.util.Random;

public class EnemyBullet extends GameObject {
    private Handler handler;
    private Random r = new Random();
    private Color color;
    private boolean smartBullets;
    private GameObject player;

    public EnemyBullet(int x, int y, ID id, Handler handler, boolean isBoss, boolean smartBullets) {
        super(x, y, id);
        this.handler = handler;
        this.smartBullets = smartBullets;
        this.player = handler.object.get(0);

        if (smartBullets) color = Color.GREEN;
        else color = Color.ORANGE;
        if (isBoss) velX = (r.nextInt(5 - -5) + -5);
        else velX = 0;
        velY = 5;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        if (smartBullets) {
            float diffX = x - player.getX() - 8;
            float diffY = y - player.getY() - 8;
            float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

            if (x < player.getX())
                velX = ((-1 / distance) * diffX) + 1.2f;
            else if (x >= player.getX())
                velX = ((-1 / distance) * diffX) - 1.2f;
            if (y < player.getY())
                velY = ((-1 / distance) * diffY) + 1.2f;
            else if (y >= player.getY())
                velY = ((-1 / distance) * diffY) - 1.2f;

            smartBullets = false;
        }
        x += velX;
        y += velY;

        if (y >= Game.HEIGHT) handler.removeObject(this);
        handler.addObject(new Trail(x, y, ID.Trail, handler, Color.orange, 18, 18, 0.09f));
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
