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

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 40, 38);
    }

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


    public void tick() {
        x += velX;
        y += velY;

        if (x <= 0 || x >= Game.WIDTH - 40) velX *= -1;
        if (y <= 0 || y >= Game.HEIGHT - 38) velY *= -1;

        collision();
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }
}

