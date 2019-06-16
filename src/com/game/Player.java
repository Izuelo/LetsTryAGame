package com.game;

import java.awt.*;

public class Player extends GameObject {
    Handler handler;
    private int shootTimer = 40;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 32);
        y = Game.clamp(y, 225, Game.HEIGHT - 32);

        handler.addObject(new Trail(x, y, ID.Trail, handler, Color.WHITE, 34, 34, 0.15f));
        try {
            collision();
        } catch (Exception ignored){}

        if (!canShoot) {
            shootTimer--;
            if (shootTimer == 0) {
                shootTimer = 40;
                setCanShoot(true);
            }
        }
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.BossEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 2;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int) x, (int) y, 32, 32);
    }
}
