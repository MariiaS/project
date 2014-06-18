package miniTennis;

import java.awt.*;
import java.net.MalformedURLException;

/**
 * Created by Маруся on 16.06.2014.
 */
public class Ball {
    private static final int DIAMETER = 30;
    private int coordX = 0;
    private int coordY = 0;
    private int moveXcoord = 1;
    private int moveYcoord = 1;
    private final Game game;

    public Ball(final Game game) {
        this.game = game;
    }

    void move() throws MalformedURLException {
        if (coordX + moveXcoord < 0)
            moveXcoord = game.speed;
        if (coordX + moveXcoord > game.getWidth() - DIAMETER)
            moveXcoord = -game.speed;
        if (coordY + moveYcoord < 0)
            moveYcoord = game.speed;
        if (coordY + moveYcoord > game.getHeight() - DIAMETER) {
            if (game.life - 1 == 0) {
                game.gameOver();
            } else {
                Sound.playMinusLife();
                game.speed--;
                game.life--;
                game.reset();

            }
        }
        if (collision()) {
            Sound.playSoundBall();
            moveYcoord = -game.speed;
            coordY = game.racquet.getTopY() - DIAMETER;
            game.target.nextTarget();
            if ((double) (game.score) % 8.0 == 0) {
                game.speed++;
            }
            if (game.speed > 5) {
                game.speed--;
            }
            if ((double) (game.score + 1) % 4.0 == 0 && game.score != 0) {
                Sound.playCombo();
            }
            game.score++;
        }
        if (destroyed()) {
            if ((double) (game.score) % 8.0 == 0) {
                game.speed++;
            }

            if ((double) (game.score + 1) % 4.0 == 0 && game.score != 0) {
                Sound.playCombo();
            }
            game.target.nextTarget();
            Sound.playSoundBall();
            game.score++;
            game.repaint();
            if (coordX + moveXcoord < coordX) {
                moveXcoord = -game.speed;
            } else {
                moveXcoord = game.speed;
            }
            if (coordY + moveYcoord < coordY) {
                moveYcoord = game.speed;
            } else {
                moveYcoord = -game.speed;
            }
        }
//        if (coordX + moveXcoord < coordX)
//            moveXcoord = -game.speed;
//        if (coordX + moveXcoord > game.getWidth() - DIAMETER)
//            moveXcoord = -game.speed;
//        if (coordY + moveYcoord < coordY)
//            moveYcoord = -game.speed;
        coordX = coordX + moveXcoord;
        coordY = coordY + moveYcoord;
    }

    public void reset() throws MalformedURLException {
        coordX = 0;
        coordY = 0;
    }

    private boolean collision() {
        return game.racquet.getBounds().intersects(getBounds());
    }

    public boolean destroyed() {
        return game.target.getBounds().intersects(this.getBounds());
    }

    public void paint(final Graphics2D g) {
        g.fillOval(coordX, coordY, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(coordX, coordY, DIAMETER, DIAMETER);
    }
}