package com.game.gameObjects;

import com.game.gameCore.ID;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class which handles every object in our game
 */
public class Handler {
    ArrayList<GameObject> object = new ArrayList<>();

    /**
     * Method to update status of every object in the game and their variables
     */
    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    /**
     * Renders graphic for every object in our game
     * @param g
     */
    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject obj = object.get(i);
            obj.render(g);
        }
    }

    /**
     * Adds object to our game
     * @param object Object to-be added
     */
    public void addObject(GameObject object) {
        this.object.add(object);
    }

    /**
     * Removes object from our game
     * @param object Object to-be removed
     */
    public void removeObject(GameObject object) {
        this.object.remove(object);
    }


    /**
     * Method which removes Fast and Smart enemies from the game
     */
    public void clearEnemies() {
        for (int i = 0; i < object.size(); i++) {
            GameObject obj = object.get(i);
            if (obj.getId() == ID.FastEnemy || obj.getId() == ID.SmartEnemy) {
                removeObject(obj);
                i--;
            }
        }
    }

    /**
     * Getter of the ArrayList object parameter
     * @return Returns ArrayList of the objects in our game
     */
    public ArrayList<GameObject> getObject() {
        return object;
    }
}
