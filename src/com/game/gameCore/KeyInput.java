package com.game.gameCore;

import com.game.gameObjects.Handler;
import com.game.gameObjects.bullets.PlayerBullet;
import com.game.gameObjects.GameObject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Class which handles user key input
 */
public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;
        for (int i = 0; i < keyDown.length; i++) {
            keyDown[i] = false;
        }
    }

    /**
     * Method which handles events of pressed keys
     * @param e stored event of pressed key
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        GameObject tempPlayer = handler.getObject().get(0);

        if (key == KeyEvent.VK_ESCAPE) System.exit(0);
        if (key == KeyEvent.VK_SPACE && tempPlayer.isCanShoot()) {
            handler.addObject(new PlayerBullet(tempPlayer.getX() + 16, tempPlayer.getY(), ID.PlayerBullet, handler));
            tempPlayer.setCanShoot(false);
        }

        if (tempPlayer.getId() == ID.Player) {
            if (key == KeyEvent.VK_UP) {
                tempPlayer.setVelY(-5);
                keyDown[0] = true;
            }
            if (key == KeyEvent.VK_DOWN) {
                tempPlayer.setVelY(5);
                keyDown[1] = true;
            }
            if (key == KeyEvent.VK_RIGHT) {
                tempPlayer.setVelX(5);
                keyDown[2] = true;
            }
            if (key == KeyEvent.VK_LEFT) {
                tempPlayer.setVelX(-5);
                keyDown[3] = true;
            }
        }

    }
    /**
     * Method which handles events of released keys
     * @param e stored event of released key
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        GameObject obj = handler.getObject().get(0);
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
