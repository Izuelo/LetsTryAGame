package com.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        handler.object.forEach(obj -> {
            if (obj.getId() == ID.Player) {
                if(key == KeyEvent.VK_UP) obj.setVelY(-5);
                if(key == KeyEvent.VK_DOWN) obj.setVelY(5);
                if(key == KeyEvent.VK_RIGHT) obj.setVelX(5);
                if(key == KeyEvent.VK_LEFT) obj.setVelX(-5);
            }
        });
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        handler.object.forEach(obj -> {
            if (obj.getId() == ID.Player) {
                if(key == KeyEvent.VK_UP) obj.setVelY(0);
                if(key == KeyEvent.VK_DOWN) obj.setVelY(0);
                if(key == KeyEvent.VK_RIGHT) obj.setVelX(0);
                if(key == KeyEvent.VK_LEFT) obj.setVelX(0);
            }
        });
    }
}
