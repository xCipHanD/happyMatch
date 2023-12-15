package asia.sustech.happymatch.Utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

public class BGMPlayer {
    private final static String BGM = "/bgm.mp3";
    private static BGMPlayer instance = null;
    private static MediaPlayer mediaPlayer = null;

    public static BGMPlayer getInstance() {
        if (instance == null) {
            instance = new BGMPlayer();
        }
        return instance;
    }

    private BGMPlayer() {
        mediaPlayer = new MediaPlayer(new Media(Objects.requireNonNull(getClass().getResource(BGM)).toString()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void play() {
        mediaPlayer.play();
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    public double getVolume() {
        return mediaPlayer.getVolume();
    }

    public void setMute(boolean mute) {
        mediaPlayer.setMute(mute);
    }

    public boolean isMute() {
        return mediaPlayer.isMute();
    }
}
