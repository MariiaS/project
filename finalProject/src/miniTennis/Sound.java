package miniTennis;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Маруся on 17.06.2014.
 */
public class Sound {
    public static synchronized void playSoundBack() throws MalformedURLException, InterruptedException {
        final URL url = new URL("file:/C:/Java/onlyGit/project/finalProject/src/miniTennis/back.wav");
        final AudioClip clip = Applet.newAudioClip(url);
        ;
        clip.loop();
    }

    public static synchronized void playSoundBall() throws MalformedURLException {
        final URL url = new URL("file:/C:/Java/onlyGit/project/finalProject/src/miniTennis/3dm_bik_ball.wav");
        final AudioClip clip = Applet.newAudioClip(url);
        ;
        clip.play();
    }

    public static synchronized void playGameOver() throws MalformedURLException {
        final URL url = new URL("file:/C:/Java/onlyGit/project/finalProject/src/miniTennis/woody2.wav");
        final AudioClip clip = Applet.newAudioClip(url);
        ;
        clip.play();
    }

    public static synchronized void playCombo() throws MalformedURLException {
        final URL url = new URL("file:/C:/Java/onlyGit/project/finalProject/src/miniTennis/combo.wav");
        final AudioClip clip = Applet.newAudioClip(url);
        ;
        clip.play();
    }

    public static synchronized void playMinusLife() throws MalformedURLException {
        final URL url = new URL("file:/C:/Java/onlyGit/project/finalProject/src/miniTennis/tennis.wav");
        final AudioClip clip = Applet.newAudioClip(url);
        ;
        clip.play();
    }
//    public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("C:\\Users\\Public\\Downloads\\30.wav"));
//public static final AudioClip BACK = Applet.newAudioClip(Game.class.getResource("C:/Java/onlyGit/term4/lang/src/java/ru/ifmo/enf/solodyankina/miniTennis/woody2.wav"));
    //   public static final AudioClip BACK = Applet.newAudioClip(Sound.class.getResource("C:/Users/Public/Downloads/30.wav"));
}
