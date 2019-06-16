package com.game.gameCore;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Class thath puts two backgrounds together for smooth transition
 */
public class ScrollingBackground extends Canvas {

    // Two copies of the background image to scroll
    private Background backOne;
    private Background backTwo;
    private BufferedImage back;

    public ScrollingBackground() {
        backOne = new Background(0, 0);
        backTwo = new Background(0, backOne.getImageHeight());
    }
    /**
     * Renders scrolling background
     * @param g
     */
    public void render(Graphics g) {
        Graphics2D twoD = (Graphics2D) g;

        if (back == null)
            back = (BufferedImage) (createImage(getWidth(), getHeight()));

        // Put the two copies of the background image onto the buffer
        backOne.render(g);
        backTwo.render(g);

        // Draw the image onto the window
        twoD.drawImage(back, null, 0, 0);

    }

}