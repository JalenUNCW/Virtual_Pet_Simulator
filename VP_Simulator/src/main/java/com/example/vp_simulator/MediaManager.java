package com.example.vp_simulator;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaManager {
    private static MediaPlayer mediaPlayer;

    public static void initialize(String resourcePath) {
        if (mediaPlayer == null) {
            // Use the resource path for bundled audio
            Media media = new Media(MediaManager.class.getResource(resourcePath).toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Optional: loop playback
            mediaPlayer.play();
        }
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}

