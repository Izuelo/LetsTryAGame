package com.game;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        for (int i =0; i< object.size();i++) {
            GameObject tempObject=object.get(i);

            tempObject.tick();

        }
    }

    public void render(Graphics g) {
        object.forEach(obj -> obj.render(g));
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }
}
