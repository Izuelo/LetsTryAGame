package com.game.gameCore;

import com.game.gameCore.Game;

import java.awt.*;

/**
 * Class that represents current Helth of a player, Level he is in and Score he got
 */
public class HUD {
    public static float HEALTH = 100;
    private float greenValue = 255;
    private int score = 0;
    private int level = 1;

    /**
     * Method to update status of this object and its variables
     */
    public void tick() {

        HEALTH = Game.clamp(HEALTH, 0, HEALTH);
        greenValue = Game.clamp(greenValue, 0, 255);

        greenValue = HEALTH * 2;

        score++;
    }

    /**
     * Renders graphic for this object
     * @param g
     */
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(125, (int) greenValue, 0));
        g.fillRect(15, 15, (int) HEALTH * 2, 32);
        g.setColor(Color.WHITE);
        g.drawRect(15, 15, (int) HEALTH * 2, 32);
        g.drawString(HEALTH + "%", 105, 34);
        g.drawString("Score: " + score, 10, 68);
        g.drawString("Level: " + level, 10, 84);
    }

    /**
     * Getter of the score parameter
     * @return Returns score of a player
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter of the score parameter
     * @param score Sets new score of a player
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Getter of the level parameter
     * @return Returns level of a player
     */
    public int getLevel() {
        return level;
    }

    /**
     * Setter of the level parameter
     * @param level Sets new level of a player
     */
    public void setLevel(int level) {
        this.level = level;
    }
}
