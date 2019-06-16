package com.game.gameObjects.enemies;

import com.game.gameCore.Game;
import com.game.gameCore.ID;
import com.game.gameObjects.GameObject;
import com.game.gameObjects.Handler;
import com.game.gameObjects.Trail;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 * Class that represents fast enemy (an asteroid) you may encounter during a game.
 */
public class FastEnemy extends GameObject {

    private Handler handler;
    private BufferedImage image;

    public FastEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        try {
            this.image = ImageIO.read(getClass().getResource("/com/game/resources/images/asteroid.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        velX = 4;
        velY = 8;
    }
    /**
     * Get bounds for this object
     * @return rectangle that matches the size of this object image
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 40, 38);
    }

    /**
     * Checking collision with PlayerBullet and removing this object if it occurs
     */
    private void collision() {
        for (int i = 0; i < handler.getObject().size(); i++) {

            GameObject tempObject = handler.getObject().get(i);
            if (tempObject.getId() == ID.PlayerBullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.getObject().remove(this);
                    handler.getObject().remove(tempObject);
                }
            }
        }
    }

    /**
     * Method to update status of this object and its variables
     */
    public void tick() {
        x += velX;
        y += velY;

        if (x <= 0 || x >= Game.WIDTH - 40) velX *= -1;
        if (y <= 0 || y >= Game.HEIGHT - 38) velY *= -1;

        collision();
    }

    /**
     * Renders graphic for this object
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }
}

