package asia.sustech.happymatch;

import asia.sustech.happymatch.NetUtils.HttpRequests;
import asia.sustech.happymatch.NetUtils.HttpResult;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SoundsPlayer {

    public static void playSound_btnClick() {
        play("/Sounds/btnClick.wav");
    }

    public static void playSound_btnClick1() {
        play("/Sounds/btnClick1.wav");
    }

    public void playSound3() {
        play("path/to/your/sound3.wav");
    }

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
            clip.start();
        });
        thread.start();

    }
}
