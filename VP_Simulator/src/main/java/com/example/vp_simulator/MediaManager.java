package com.example.vp_simulator;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.Objects;

public class MediaManager {
    private static MediaPlayer mediaPlayer;
    private static String currentTrack;
    private static boolean hardcoreMode = false; // Default is OFF
    private static double volume = 0.5; // Default volume (0.0 to 1.0)

    // Method to play music, ensuring the track continues when returning to main menu
    public static void playMusic(String path) {
        // If the track is already playing, and it's the same track, do nothing
        if (mediaPlayer != null && currentTrack != null && currentTrack.equals(path) && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            return;
        }

        // If music is playing, stop it before playing a new track
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        // Load new track
        URL resourceUrl = MediaManager.class.getResource(path);
        if (resourceUrl == null) {
            System.err.println("Resource not found: " + path);
            return;
        }

        Media media = new Media(resourceUrl.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.5); // Set default volume (50%)
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
        mediaPlayer.play();

        currentTrack = path; // Save the current track
    }

    // Stop the currently playing music
    public static void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    // Method to initialize the media player with the given resource path
    public static void initialize(String resourcePath) {
        if (mediaPlayer != null) {
            mediaPlayer.stop(); // Stop the previous track if it's playing
        }

        // Use the resource path for bundled audio
        Media media = new Media(Objects.requireNonNull(MediaManager.class.getResource(resourcePath)).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Optional: loop playback
        mediaPlayer.setVolume(volume); // Set initial volume
        mediaPlayer.play(); // Start the new track
    }

    // Method to switch between tracks
    public static void switchTrack(String resourcePath) {
        initialize(resourcePath); // Reinitialize the media player with the new track
    }

    // Toggle hardcore mode (changes tracks)
    public static void toggleHardcoreMode(String hardcoreTrack, String normalTrack) {
        hardcoreMode = !hardcoreMode; // Toggle mode
        if (hardcoreMode) {
            switchTrack(hardcoreTrack); // Switch to hardcore track
        } else {
            switchTrack(normalTrack); // Switch to normal track
        }
    }

    // Get the current hardcore mode state
    public static boolean isHardcoreMode() {
        return hardcoreMode;
    }

    // Set the hardcore mode directly (if needed)
    public static void setHardcoreMode(boolean mode, String hardcoreTrack, String normalTrack) {
        hardcoreMode = mode;
        if (hardcoreMode) {
            switchTrack(hardcoreTrack);
        } else {
            switchTrack(normalTrack);
        }
    }

    // Get the current MediaPlayer instance
    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    // Adjust the volume
    public static void setVolume(double newVolume) {
        volume = newVolume; // Update global volume
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume); // Apply to current track
        }
    }

    // Get the current volume
    public static double getVolume() {
        return volume;
    }
}

