package com.game.gameCore;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Stores image for a background and moves it vertically
 */
public class Background {
    private BufferedImage image;
    private int x;
    private int y;
    private int timer = 0;

    public Background(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(getClass().getResource("/com/game/resources/images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Renders graphic for this object and moves it vertically
     * @param g
     */
    public void render(Graphics g) {

        // Draw the image onto the Graphics reference
        g.drawImage(image, getX(), getY(), image.getWidth(), image.getHeight(), null);

        // Move the y position left for next time
        timer++;
        if (timer % 40 == 0) {
            this.y -= 1;
            timer=0;
        }
        // Check to see if the image has gone off stage up
        if (this.y <= -1 * image.getHeight()) {

            // If it has, line it back up so that its upper edge is
            // lined up to the bottom side of the other background image
            this.y = this.y + image.getHeight() * 2;
        }

    }

    /**
     * Setter of the X parameter
     * @param x New x value
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Getter of the X parameter
     * @return Returns value of the coordinate X
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter of the Y parameter
     * @return Returns value of the coordinate Y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Get the height of the background image
     * @return Height of the background image
     */
    public int getImageHeight() {
        return image.getHeight();
    }


}

