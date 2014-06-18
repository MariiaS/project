package miniTennis;

import java.awt.*;
import java.util.Random;

/**
 * Created by Маруся on 17.06.2014.
 */
public class Target {
    private final Game game;
    private int x;
    private int y;
    private boolean first = true;

    public Target(final Game game) {
        this.game = game;
    }

    public void nextTarget() {
        x = new Random().nextInt(game.getWidth() - 80);
        y = new Random().nextInt(game.getHeight()) % (550 - 100);
        //   System.out.println("New Target:("+x+","+y+") ---> " + game.getWidth() + "/" + game.getHeight());
    }

    public void paint(final Graphics2D g) {
        if (bulleted() || first) {
            nextTarget();
            first = false;
        }
        g.setColor(Color.RED);
        g.fillRect(x, y, 30, 10);
    }


    public boolean bulleted() {
        return game.collisionDetection(this.getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 10);
    }

    public void reset() {
        this.nextTarget();
    }
}
