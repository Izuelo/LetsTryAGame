package com.game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;

    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void mouseReleased(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //Play button
        if (game.gameState == Game.STATE.Menu) {
            if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 150, 200, 64)) {
                game.gameState = Game.STATE.Game;
                handler.object.clear();
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                for (int i = 0; i < 3; i++) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 60), -100, ID.BasicEnemy, handler, false));
                }
            }

            //Help button
            if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 250, 200, 64)) {
                game.gameState = Game.STATE.Help;
            }

            //Quit button
            if (game.gameState == Game.STATE.Menu) {
                if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 350, 200, 64)) {
                    System.exit(0);
                }
            }
        }

        //Back button from help
        if (game.gameState == Game.STATE.Help) {
            if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 350, 200, 64)) {
                game.gameState = Game.STATE.Menu;
            }
        }
        //Try again button
        if (game.gameState == Game.STATE.GameOver) {
            if (mouseOver(mx, my, Game.WIDTH / 2 - 100, 350, 200, 64)) {
                game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                handler.object.clear();
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                for (int i = 0; i < 3; i++) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 60), -100, ID.BasicEnemy, handler, false));
                }
            }
        }
    }

    public void mousePressed(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        Font fnt = new Font("arial", 1, 50);
        Font fnt2 = new Font("small", 1, 30);
        if (game.gameState == Game.STATE.Menu) {
            g.setFont(fnt);

            g.setColor(Color.GRAY);
            g.fillRect(Game.WIDTH / 2 - 100, 150, 200, 64);
            g.fillRect(Game.WIDTH / 2 - 100, 250, 200, 64);
            g.fillRect(Game.WIDTH / 2 - 100, 350, 200, 64);

            g.setColor(Color.white);
            g.drawString("GIERECZKA", Game.WIDTH / 2 - 150, 100);
            g.drawRect(Game.WIDTH / 2 - 100, 150, 200, 64);
            g.drawRect(Game.WIDTH / 2 - 100, 250, 200, 64);
            g.drawRect(Game.WIDTH / 2 - 100, 350, 200, 64);
            g.setFont(fnt2);
            g.drawString("Play", Game.WIDTH / 2 - 30, 190);
            g.drawString("Help", Game.WIDTH / 2 - 30, 290);
            g.drawString("Quit", Game.WIDTH / 2 - 30, 390);
        } else if (game.gameState == Game.STATE.Help) {
            g.setFont(fnt);
            g.setColor(Color.GRAY);
            g.fillRect(Game.WIDTH / 2 - 100, 350, 200, 64);
            g.setColor(Color.white);
            g.drawRect(Game.WIDTH / 2 - 100, 350, 200, 64);
            g.drawString("Help", Game.WIDTH / 2 - 50, 100);
            g.setFont(fnt2);
            g.drawString("Back", Game.WIDTH / 2 - 30, 390);
        } else if (game.gameState == Game.STATE.GameOver) {
            g.setFont(fnt);
            g.setColor(Color.GRAY);
            g.fillRect(Game.WIDTH / 2 - 100, 350, 200, 64);

            g.setColor(Color.white);
            g.drawString("Game Over", Game.WIDTH / 2 - 130, 100);
            g.setFont(fnt2);
            g.drawString("You lost with a score of: " + hud.getScore(), Game.WIDTH / 2 - 205, 150);

            g.drawRect(Game.WIDTH / 2 - 100, 350, 200, 64);
            g.drawString("Try again!", Game.WIDTH / 2 - 65, 390);
        }
    }
}