package com.game.gameMenu;

import com.game.gameObjects.Trail;
import com.game.gameCore.Game;
import com.game.gameCore.ID;
import com.game.gameObjects.GameObject;
import com.game.gameObjects.Handler;

import java.awt.*;
import java.util.Random;

/**
 * Class that makes cool bouncing rectangles as a animated background for menu
 */
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
    /**
     * Get bounds for this object
     * @return rectangle that matches the size of this object image
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

        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
        if (y <= 0 || y >= Game.HEIGHT - 16) velY *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, handler, color, 18, 18, 0.03f));
    }
    /**
     * Renders graphic for this object
     * @param g
     */
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}

