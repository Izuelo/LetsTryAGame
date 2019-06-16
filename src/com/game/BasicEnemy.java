package com.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class BasicEnemy extends GameObject {
    private Handler handler;
    private Random r = new Random();
    private Color color;
    private BufferedImage image;
    private boolean smartBullets;
    private int timer1 = r.nextInt(60) + 40;
    private int timer2 = r.nextInt(60) + 10;

    public BasicEnemy(int x, int y, ID id, Handler handler, boolean smartBullets) {
        super(x, y, id);
        this.handler = handler;
        this.smartBullets = smartBullets;
        if (smartBullets) color = Color.BLUE;
        else color = Color.RED;

        try {
            this.image= ImageIO.read(getClass().getResource("/com/resources/images/enemyShipSD.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        velX = 0;
        velY = 3;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 40, 40);
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.PlayerBullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.object.remove(this);
                    handler.object.remove(tempObject);
                }
            }
        }
    }

    public void tick() {
        x += velX;
        y += velY;

        if (timer1 <= 0) {
            velY = 0;
            if (velX == 0) velX = (r.nextInt(7 - -7) + -7);
            if (timer2 == 0) {
                timer2 = r.nextInt(60) + 10;
                if (smartBullets)
                    handler.addObject(new EnemyBullet((int) x + 48, (int) y, ID.BasicEnemy, handler, false, true));
                else
                    handler.addObject(new EnemyBullet((int) x + 48, (int) y, ID.BasicEnemy, handler, false, false));
            }
            timer2--;
        } else timer1--;

        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
        collision();
    }

    public void render(Graphics g) {
//        g.setColor(color);
//        g.fillRect((int) x, (int) y, 32, 32);

        g.drawImage(this.image,(int)x, (int) y,null);
    }
}
