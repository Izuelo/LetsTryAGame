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

        if (scoreKeep >= 100) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if (hud.getLevel() == 2) {
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH + 16), r.nextInt(Game.HEIGHT + 16), ID.BasicEnemy, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH + 16), r.nextInt(Game.HEIGHT + 16), ID.BasicEnemy, handler));
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH + 16), r.nextInt(Game.HEIGHT + 16), ID.SmartEnemy, handler));
            } else if (hud.getLevel() == 3) {
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH + 16), r.nextInt(Game.HEIGHT + 16), ID.FastEnemy, handler));
            } else if (hud.getLevel() == 4) {
                handler.addObject(new FastSmartEnemy(r.nextInt(Game.WIDTH + 16), r.nextInt(Game.HEIGHT + 16), ID.FastSmartEnemy, handler));
            }
        }
    }


}
