package com.game.gameObjects.enemies;

import com.game.gameCore.Game;
import com.game.gameCore.ID;
import com.game.gameObjects.Handler;
import com.game.gameObjects.Trail;
import com.game.gameObjects.bullets.BossBullet;
import com.game.gameObjects.GameObject;

import java.awt.*;
import java.util.Random;

public class BossEnemy extends GameObject {
    private Handler handler;
    private Random r = new Random();
    private int timer1 = 120;
    private int timer2 = 80;
    private float bossHealth = 100;
    private float greenValue = 255;
    private float offset = 0.00f;
    public BossEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = 0;
        velY = 1;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 96, 96);
    }

    private void collision() {
        for (int i = 0; i < handler.getObject().size(); i++) {

            GameObject tempObject = handler.getObject().get(i);
            if (tempObject.getId() == ID.PlayerBullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.getObject().remove(tempObject);
                    bossHealth -= 10;
                    offset += 0.2f;
                    if (bossHealth <= 0)
                        handler.getObject().remove(this);
                }
            }
        }
    }

    public void tick() {
        x += velX;
        y += velY;

        if (timer1 <= 0) {
            velY = 0;
            if (timer2 == 40) {
                handler.addObject(new FastEnemy((int) x - 96, (int) y, ID.FastEnemy, handler));
                handler.addObject(new FastEnemy((int) x + 96, Game.HEIGHT - 32, ID.FastEnemy, handler));
            }
            timer2--;
        } else timer1--;
        if (timer2 <= 0) {
            collision();
            if (velX == 0) velX = 5;
            if (velX > 0) {
                velX += offset;
            } else if (velX < 0) {
                velX -= offset;
            }
            velX = Game.clamp(velX, -12, 12);
            int spawn = r.nextInt(8);
            if (spawn == 0) handler.addObject(new BossBullet((int) x + 48, (int) y, ID.BasicEnemy, handler));
        }

        if (x <= 0 || x >= Game.WIDTH - 96) velX *= -1;
        handler.addObject(new Trail(x, y, ID.Trail, handler, Color.RED, 98, 98, 0.08f));
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, 96, 96);

        g.setColor(Color.GRAY);
        g.fillRect((int) x - 2, (int) y - 16, (int) bossHealth, 15);
        g.setColor(new Color(125, (int) greenValue, 0));
        g.fillRect((int) x - 2, (int) y - 16, (int) bossHealth, 15);
        g.setColor(Color.WHITE);
        g.drawRect((int) x - 2, (int) y - 16, (int) bossHealth, 15);
    }
}
