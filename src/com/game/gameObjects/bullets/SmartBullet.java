package com.game.gameObjects.bullets;

import com.game.gameCore.Game;
import com.game.gameCore.ID;
import com.game.gameObjects.GameObject;
import com.game.gameObjects.Handler;
import com.game.gameObjects.Trail;

import java.awt.*;
import java.util.Random;

public class SmartBullet extends GameObject {
    private Handler handler;

    public SmartBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        GameObject player = handler.getObject().get(0);

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
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y >= Game.HEIGHT) handler.removeObject(this);
        handler.addObject(new Trail(x, y, ID.Trail, handler, Color.BLUE, 18, 18, 0.09f));
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
