package com.game;

import java.awt.*;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        for (int i = 0; i < handler.object.size(); i++)
            if (handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);

        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

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
        handler.addObject(new Trail(x, y, ID.Trail, handler, Color.ORANGE, 19, 19, 0.05f));
    }

    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}
