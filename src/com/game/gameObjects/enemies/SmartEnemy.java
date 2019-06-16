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
 * Class that represents smart enemy you may encounter during a game. SmartEnemy follows Player position
 */
public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;
    private BufferedImage image;


    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        player = handler.getObject().get(0);

        try {
            this.image= ImageIO.read(getClass().getResource("/com/game/resources/images/smartEnemySD.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.handler = handler;
    }
    /**
     * Get bounds for this object
     * @return rectangle that matches the size of this object image
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 50, 47);
    }

    /**
     * Method to update status of this object and its variables
     */
    public void tick() {
        x += velX;
        y += velY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

        velY = ((-1 / distance) * diffY);
        velX = ((-1 / distance) * diffX);

        if (x <= 0 || x >= Game.WIDTH - 16)  velX *= -1;
        if (y <= 0 || y >= Game.HEIGHT - 16) velY *= -1;

    }

    /**
     * renders graphic for this object
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(this.image,(int)x, (int) y,null);
    }
}
