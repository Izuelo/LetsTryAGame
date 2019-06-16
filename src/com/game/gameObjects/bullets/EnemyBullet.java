package com.game.gameObjects.bullets;

import com.game.gameCore.Game;
import com.game.gameCore.ID;
import com.game.gameObjects.GameObject;
import com.game.gameObjects.Handler;
import com.game.gameObjects.Trail;

import java.awt.*;
import java.util.Random;

public class EnemyBullet extends GameObject {
    private Handler handler;
    private Random r = new Random();

    public EnemyBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = 0;
        velY = 5;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y >= Game.HEIGHT) handler.removeObject(this);
        handler.addObject(new Trail(x, y, ID.Trail, handler, Color.ORANGE, 18, 18, 0.09f));
    }

    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
