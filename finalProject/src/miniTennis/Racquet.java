package miniTennis;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Маруся on 16.06.2014.
 */
public class Racquet {
    private static final int WIDTH = 90;
    private static final int HEIGHT = 10;
    private int coordX = 0;
    private int moveXcoord = 0;
    private int coordY = 500;
    private int moveYcoord = 0;
    private final Game game;

    public Racquet(final Game game) {
        this.game = game;
    }

    public void move() {
        if (coordX + moveXcoord > 0 && coordX + moveXcoord < game.getWidth() - WIDTH) {
            coordX = coordX + moveXcoord + moveXcoord;
        }
        if (coordY + moveYcoord > 0 && coordY + moveYcoord < game.getHeight() - HEIGHT) {
            coordY = coordY + moveYcoord + moveYcoord;
        }

    }

    public void paint(final Graphics2D g) {
        g.fillRect(coordX, coordY, WIDTH, HEIGHT);
    }

    public void keyReleased(final KeyEvent e) {
        moveXcoord = 0;
        moveYcoord = 0;
    }

    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            moveXcoord = -game.speed;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            moveXcoord = game.speed;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            moveYcoord = -game.speed;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            moveYcoord = game.speed;
    }

    public Rectangle getBounds() {
        return new Rectangle(coordX, coordY, WIDTH, HEIGHT);
    }

    public int getTopY() {
        return coordY - HEIGHT;
    }
}