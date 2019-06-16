package com.game.gameCore;

import com.game.gameObjects.Handler;
import com.game.gameObjects.enemies.BasicEnemy;
import com.game.gameObjects.enemies.BossEnemy;
import com.game.gameObjects.enemies.FastEnemy;
import com.game.gameObjects.enemies.SmartEnemy;

import java.util.Random;

/**
 * Class which lets us control levels of our game and spawning of the enemies
 */
public class Spawn {
    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    /**
     * Method which spawn enemies and updates our level
     */
    public void tick() {
        scoreKeep++;

        if (scoreKeep >= 400) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if (hud.getLevel() == 2) {
                for (int i = 0; i < 1; i++) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 60), -100, ID.BasicEnemy, handler, true));
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 60), -100, ID.BasicEnemy, handler, false));
                }
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 60), r.nextInt(Game.HEIGHT - 60), ID.SmartEnemy, handler));
            } else if (hud.getLevel() == 3) {
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 60), r.nextInt(Game.HEIGHT - 60), ID.FastEnemy, handler));
            } else if (hud.getLevel() == 4) {
                for (int i = 0; i < 2; i++) {

                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 60), -100, ID.FastEnemy, handler));
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 60), -100, ID.BasicEnemy, handler, false));
                }
            } else if (hud.getLevel() == 5) {
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 60), r.nextInt(Game.HEIGHT - 60), ID.FastEnemy, handler));
            } else if (hud.getLevel() == 6) {
                for (int i = 0; i < 2; i++) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 60), -100, ID.BasicEnemy, handler, true));
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 60), -100, ID.BasicEnemy, handler, true));
                }
            } else if (hud.getLevel() == 7) {
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 60), r.nextInt(Game.HEIGHT - 60), ID.FastEnemy, handler));
            } else if (hud.getLevel() == 10) {
                handler.clearEnemies();
                handler.addObject(new BossEnemy((Game.WIDTH / 2 - 48), -110, ID.BossEnemy, handler));
            }
        }
    }

    /**
     * Setter of the scoreKeep parameter
     * @param scoreKeep Parameter which tells the game when the level should be updated and enemies spawned
     */
    public void setScoreKeep(int scoreKeep) {
        this.scoreKeep = scoreKeep;
    }
}
