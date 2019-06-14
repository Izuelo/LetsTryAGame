package com.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.Arrays;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;
        for (int i = 0; i < keyDown.length; i++) {
            keyDown[i] = false;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) System.exit(0);
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject obj = handler.object.get(i);
            if (obj.getId() == ID.Player) {
                if (key == KeyEvent.VK_UP) {
                    obj.setVelY(-5);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_DOWN) {
                    obj.setVelY(5);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_RIGHT) {
                    obj.setVelX(5);
                    keyDown[2] = true;
                }
                if (key == KeyEvent.VK_LEFT) {
                    obj.setVelX(-5);
                    keyDown[3] = true;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject obj = handler.object.get(i);
            if (obj.getId() == ID.Player) {
                if (key == KeyEvent.VK_UP) keyDown[0] = false;
                if (key == KeyEvent.VK_DOWN) keyDown[1] = false;
                if (key == KeyEvent.VK_RIGHT) keyDown[2] = false;
                if (key == KeyEvent.VK_LEFT) keyDown[3] = false;

                if (!keyDown[0] && !keyDown[1]) obj.setVelY(0);
                else if (keyDown[0] && !keyDown[1]) obj.setVelY(-5);
                else if (!keyDown[0] && keyDown[1]) obj.setVelY(5);
                if (!keyDown[2] && !keyDown[3]) obj.setVelX(0);
                else if (keyDown[2] && !keyDown[3]) obj.setVelX(5);
                else if (!keyDown[2] && keyDown[3]) obj.setVelX(-5);
            }
        }

    }
}
