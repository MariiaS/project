package miniTennis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by Маруся on 16.06.2014.
 */
@SuppressWarnings("serial")
public class Game extends JPanel {
    public final Target target;
    public final Ball ball = new Ball(this);
    public final Racquet racquet = new Racquet(this);
    public int speed = 0;
    public int score = 0;
    public int life = 3;
    private final JLabel pointsLabel;

    /**
     * Данная игра - одна из модификация классического тенниса.
     * Управление - стрелками влево, вправо, вверх, вниз
     * Поскольку перемещаться можно не только вправо и влево, но и вверх и вниз, это делает игру динамичней
     * Поэтому вначале, когда скорость еще маленькая, можно быстро нагнать очки, подняв ракетку вверх
     * У Вас есть три жизни. Соответственно, после потери последней, игра заканчивается
     * Скорость прибавляется через каждые 8 очков, но не может быть больше 5(Итак хватит, все равно проиграете.)
     * Что касается target - это препятствие, которое появляется в рандомном месте после удара о вашу ракетку
     * Или после того, как вы его уничтожите мячом.
     * За удар мяча о препятствие также дается очко.
     * Когда игра заканчивается, выводится окно с числом Ваших очков, также играет грустная музыка( которую Вы, скорей всего, не услышите)
     * Каждый удар об ракетку сопровождается звуком, а на каждом 4 ударе - комбо!
     * Есть звук для всей игры, но с ним что-то мутное(играет 30сек и вырубается), поэтому я его закомментила
     * Я так полагаю, что на Вашем ноуте звука не будет вовсе,т.к. файл указан через мою корневую папку
     * Так что закомментируйте звуки, если они будут выдавать вам ошибку
     * P.S. my best score is 83, and ur?
     * @throws java.io.IOException
     * @throws InterruptedException
     */
    public Game() throws IOException, InterruptedException {
        target = new Target(this);
        pointsLabel = new JLabel(String.valueOf(score));
        pointsLabel.setForeground(Color.black);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                racquet.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                racquet.keyPressed(e);
            }
        });
        //   Sound.playSoundBack();      //    если нужен звук во время всей игры, то раскомментируйте
        setFocusable(true);
        this.add(pointsLabel);
    }

    private void move() throws Exception {
        ball.move();
        racquet.move();
    }

    public void reset() throws MalformedURLException {
        target.reset();
        ball.reset();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        pointsLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        pointsLabel.setText("LIVES:" + life + " - SPEED:" + speed % 20 + " - POINTS:" + String.valueOf(score));
        ball.paint(g2d);
        g2d.setColor(Color.BLUE);
        racquet.paint(g2d);
        target.paint(g2d);
        Graphics2D gg2d = (Graphics2D) g;
        gg2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gg2d.setColor(Color.GREEN);
        ball.paint(gg2d);
        g2d.setBackground(Color.YELLOW);
    }
//
//    public void startGame() {
//        JButton convertTemp = new JButton("Easy");
//        JOptionPane.showInputDialog(this, "What level? Choose easy, medium or hard", JOptionPane.YES_NO_OPTION);
//    }

    public void gameOver() throws MalformedURLException {
        if (--life == 0) {
            Sound.playGameOver();
            JOptionPane.showMessageDialog(this, "your score is: " + score, "Game Over", JOptionPane.YES_NO_OPTION);
            System.exit(ABORT);
        }

    }

    public boolean collisionDetection(Rectangle rec) { //if one of the bullet has a collision with that rectangle than
        if (this.getBounds().intersects(rec)) {
            return true;
        }
        return false;
    }

    public Rectangle getBounds() {

        return new Rectangle(60, 330, WIDTH, HEIGHT);
    }

    public static void main(String[] args) throws Exception {
        final JFrame frame = new JFrame("Mini Tennis");
        frame.setLocation(500, 100);
        final Game game = new Game();
        frame.add(game);
        game.setBackground(Color.YELLOW);
        //  frame.setLayout(new BorderLayout());
        frame.setSize(400, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(20);
        }

    }
}