package com.game.gameObjects.bullets;

import com.game.gameCore.Game;
import com.game.gameCore.ID;
import com.game.gameObjects.GameObject;
import com.game.gameObjects.Handler;
import com.game.gameObjects.Trail;

import java.awt.*;
import java.util.Random;

/**
 * Class of a bullet that player is shooting
 */
public class PlayerBullet extends GameObject {
    private Handler handler;
    private Random r = new Random();

    public PlayerBullet(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = 0;
        velY = -4.5f;
    }

    /**
     * Get bounds for this object
     * @return Rectangle that matches the size of this object image
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    /**
     * Method to update status of this object and its variables
     */
    public void tick() {
        x += velX;
        y += velY;

        if (y >= Game.HEIGHT) handler.removeObject(this);
        handler.addObject(new Trail(x, y, ID.Trail, handler, Color.WHITE, 18, 18, 0.09f));
    }

    /**
     * renders graphic for this object
     * @param g
     */
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
