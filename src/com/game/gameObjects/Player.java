package com.game.gameObjects;

import com.game.gameCore.Game;
import com.game.gameCore.HUD;
import com.game.gameCore.ID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Class which represents Player in our game
 */
public class Player extends GameObject {
    private Handler handler;
    private int shootTimer = 40;
    private BufferedImage image;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        try {
            this.image = ImageIO.read(getClass().getResource("/com/game/resources/images/playerShipSD.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get bounds for this object
     * @return Rectangle that matches the size of this object image
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 50, 32);
    }

    /**
     * Method to update status of this object and its variables
     */
    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 32);
        y = Game.clamp(y, 225, Game.HEIGHT - 32);

        try {
            collision();
        } catch (Exception ignored) {
        }

        if (!canShoot) {
            shootTimer--;
            if (shootTimer == 0) {
                shootTimer = 40;
                setCanShoot(true);
            }
        }
    }

    /**
     * Checking collision with every Enemy and Bullet in the game, removing them from the game and decreasing HEALTH of our Player
     */
    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy  || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.BossEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 2;
                    handler.object.remove(tempObject);
                }
            }
            if(tempObject.getId() == ID.FastEnemy){
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 20;
                    handler.object.remove(tempObject);
                }
            }
        }
    }

    /**
     * renders graphic for this object
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(this.image, (int) x, (int) y, null);
    }
}
