package com.game.gameCore;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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


    public void render(Graphics window) {

        // Draw the image onto the Graphics reference
        window.drawImage(image, getX(), getY(), image.getWidth(), image.getHeight(), null);

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


    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getImageWidth() {
        return image.getWidth();
    }
    public int getImageHeight() {
        return image.getHeight();
    }


    @Override
    public String toString() {
        return "Background: x=" + getX() + ", y =  " + getY() + ", height = " + image.getHeight() + " , width =  " + image.getWidth();
    }

}

