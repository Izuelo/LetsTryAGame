package com.game.gameObjects;

import com.game.gameCore.ID;

import java.awt.*;

/**
 * Abstract class which represents every Game Object in our game
 */
public abstract class GameObject {

    protected float x, y;
    protected ID id;
    protected float velX, velY;
    protected boolean canShoot = true;

    public GameObject(float x, float y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    /**
     * Method to update status of the object and its variables
     */
    public abstract void tick();

    /**
     * renders graphic for the object
     * @param g
     */
    public abstract void render(Graphics g);

    /**
     * Get bounds for this object
     * @return Rectangle that matches the size of this object image
     */
    public abstract Rectangle getBounds();

    /**
     * Getter of the X parameter
     * @return Returns X coordinate of the object
     */
    public float getX() {
        return x;
    }

    /**
     * Setter for the X parameter
     * @param x X coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter of the Y parameter
     * @return Returns Y coordinate of the object
     */
    public float getY() {
        return y;
    }

    /**
     * Setter for the Y parameter
     * @param y Y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter of the ID parameter
     * @return Returns ID of the object
     */
    public ID getId() {
        return id;
    }

    /**
     * Setter of the ID parameter
     * @param id ID of the object
     */
    public void setId(ID id) {
        this.id = id;
    }

    /**
     * Getter of the velX parameter
     * @return Returns velocity of the coordinate X
     */
    public float getVelX() {
        return velX;
    }

    /**
     * Setter of the valX parameter
     * @param velX Velocity of the X coordinate
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }

    /**
     * Getter of the velY parameter
     * @return Returns velocity of the coordinate Y
     */
    public float getVelY() {
        return velY;
    }

    /**
     * Setter of the valY parameter
     * @param velY Velocity of the Y coordinate
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }

    /**
     * Getter of the canShoot parameter
     * @return Returns boolean which tells if object can shoot
     */
    public boolean isCanShoot() {
        return canShoot;
    }

    /**
     * Setter of the canShoot parameter
     * @param canShoot Boolean which tells if object can shoot
     */
    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }
}
