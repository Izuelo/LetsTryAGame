package com.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends GameObject {
    Handler handler;
    private int shootTimer = 40;
    private BufferedImage image;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        try {
            this.image= ImageIO.read(getClass().getResource("/com/resources/images/playerShipSD.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 50, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 32);
        y = Game.clamp(y, 225, Game.HEIGHT - 32);


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
//        g.setColor(Color.white);
//        g.fillRect((int) x, (int) y, 32, 32);

        g.drawImage(this.image,(int)x, (int) y,null);
    }
}
