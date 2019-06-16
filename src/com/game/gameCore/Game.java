package com.game.gameCore;

import com.game.gameMenu.Menu;
import com.game.gameMenu.MenuParticle;
import com.game.gameObjects.Handler;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * Main class which runs the game.
 */
public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Random r = new Random();
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    private ScrollingBackground scrollingBackground;

    /**
     * Set of constants that represents different GameStates.
     */
    public enum STATE {
        Menu,
        Help,
        Game,
        GameOver
    }

    public STATE gameState = STATE.Menu;

    public Game() {
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        scrollingBackground = new ScrollingBackground();

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "Giereczka", this);

        spawner = new Spawn(handler, hud);


        if (gameState != STATE.Game) {
            for (int i = 0; i < 20; i++) {
                handler.addObject(new MenuParticle(r.nextInt(WIDTH - 32), r.nextInt(HEIGHT - 32), ID.MenuParticle, handler));
            }
        }
    }

    /**
     * Starts the game
     */
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    /**
     * Stops the game
     */
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Game loop that allos to update everything in the game
     */
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta > 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    /**
     * Method to update status of every other object in the game
     */
    private void tick() {
        handler.tick();
        if (gameState == STATE.Game) {
            hud.tick();
            spawner.tick();
            if (HUD.HEALTH <= 0) {
                HUD.HEALTH = 100;
                handler.getObject().clear();
                spawner.setScoreKeep(0);
                gameState = STATE.GameOver;
                for (int i = 0; i < 20; i++) {
                    handler.addObject(new MenuParticle(r.nextInt(WIDTH - 32), r.nextInt(HEIGHT - 32), ID.MenuParticle, handler));
                }
            }
        } else if (gameState == STATE.Menu || gameState == STATE.GameOver) {
            menu.tick();
        }
    }

    /**
     * Method to render image of every other object in the game
     */
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if (gameState == STATE.Game) {
            scrollingBackground.render(g);
        }
        handler.render(g);
        if (gameState == STATE.Game) {


            hud.render(g);
        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.GameOver) {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    /**
     * Method that does not allow variable to cross the minimum or maximum value
     * @param val value that needs to be controlled
     * @param min minimal value
     * @param max maximal value
     * @return returns value within minimum and maximum bounds
     */
    public static int clamp(float val, float min, float max) {
        return (int) Math.max(min, Math.min(max, val));
    }

    public static void main(String[] args) {
        new Game();
    }
}