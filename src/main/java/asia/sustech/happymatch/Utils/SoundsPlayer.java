package asia.sustech.happymatch.Utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;


public class SoundsPlayer {

    public static void playSound_btnClick() {
        play("/Sounds/btnClick.wav");
    }

    public static void playSound_btnClick1() {
        play("/Sounds/btnClick1.wav");
    }

    public static void playSound_btnClick2() {play("/Sounds/btnClick2.wav");}

    public static void playSound_match() {
        Random rd = new Random();
        int i = rd.nextInt(3);
        switch (i) {
            case 0:
                play("/Sounds/match.wav");
                break;
            case 1:
                play("/Sounds/match1.wav");
                break;
            case 2:
                play("/Sounds/match2.wav");
                break;
        }
    }

    public static void playSound_Excellent() {play("/Sounds/excellent.wav");}

    public static void playSound_Wow() {play("/Sounds/wow.wav");}

    public static void playSound_Amazing() {play("/Sounds/amazing.wav");}

    public static void playSound_Win() {play("/Sounds/win.wav");}

    public static void playSound_Lose() {play("/Sounds/lose.wav");}

    public static void playSound_fall() {play("/Sounds/fall.wav");}

    private static void play(String audioFilePath) {
        Thread thread = new Thread(() -> {
            File audioFile =
                    new File(Objects.requireNonNull(SoundsPlayer.class.getResource(audioFilePath)).getFile());
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            } catch (UnsupportedAudioFileException | IOException e) {
                throw new RuntimeException(e);
            }

            Clip clip = null;
            try {
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
            } catch (LineUnavailableException | IOException e) {
                throw new RuntimeException(e);
            }
            //设置音量
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.start();
        });
        thread.start();
    }


    public static void playSound_Unbelievable() {play("/Sounds/unbelievable.wav");}

    public static void playSound_getTips() {
        play("/Sounds/tips.wav");
    }
}
