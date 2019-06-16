package com.game.gameObjects;

import com.game.gameCore.ID;
import com.game.gameObjects.GameObject;
import com.game.gameObjects.Handler;

import java.awt.*;

/**
 * Class which adds trail effect to our bullets
 */
public class Trail extends GameObject {

    private float alpha = 1;
    private Handler handler;
    private Color color;
    private float width;
    private float height;
    private float life;

    public Trail(float x, float y, ID id, Handler handler, Color color, int width, int height, float life) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    /**
     * Method which updates alpha parameter and then removes Trail from our Game Objects if its completely transparent
     */
    public void tick() {
        if (alpha > life) {
            alpha -= life - 0.0001f;

        } else handler.removeObject(this);
    }

    /**
     * Renders graphic for this object
     * @param g
     */
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));

        g.setColor(color);
        g.fillRect((int) x, (int) y, (int) width - 2, (int) height - 2);

        g2d.setComposite(makeTransparent(1));
    }

    /**
     * Function which determine degree of transparency and how overlapping objects are rendered
     * @param alpha Alpha parameter which specifies degree of transparency
     * @return AlphaComposite
     */
    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }

    /**
     * Method which must be implemented because of the abstract class GameObjects
     * @return null
     */
    public Rectangle getBounds() {
        return null;
    }
}
