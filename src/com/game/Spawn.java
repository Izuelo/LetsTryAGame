package com.game;

import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        scoreKeep++;

        if (scoreKeep >= 200) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if (hud.getLevel() == 2) {
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 60), r.nextInt(Game.HEIGHT - 60), ID.BasicEnemy, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 60), r.nextInt(Game.HEIGHT - 60), ID.BasicEnemy, handler));
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 60), r.nextInt(Game.HEIGHT - 60), ID.SmartEnemy, handler));
            } else if (hud.getLevel() == 3) {
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 60), r.nextInt(Game.HEIGHT - 60), ID.FastEnemy, handler));
            } else if (hud.getLevel() == 4) {
                handler.addObject(new FastSmartEnemy(r.nextInt(Game.WIDTH - 60), r.nextInt(Game.HEIGHT - 60), ID.SmartEnemy, handler));
            } else if (hud.getLevel() == 5) {
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 60), r.nextInt(Game.HEIGHT - 60), ID.FastEnemy, handler));
            } else if (hud.getLevel() == 6) {
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 60), r.nextInt(Game.HEIGHT - 60), ID.BasicEnemy, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 60), r.nextInt(Game.HEIGHT - 60), ID.BasicEnemy, handler));
            } else if (hud.getLevel() == 7) {
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 60), r.nextInt(Game.HEIGHT - 60), ID.FastEnemy, handler));
            } else if (hud.getLevel() == 10) {
                handler.clearEnemies();
                handler.addObject(new BossEnemy((Game.WIDTH / 2 - 48), -110, ID.BossEnemy, handler));
            }
        }
    }


}