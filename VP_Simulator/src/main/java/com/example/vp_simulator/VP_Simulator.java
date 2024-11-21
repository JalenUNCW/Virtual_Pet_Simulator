package com.example.vp_simulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class VP_Simulator extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize the MediaPlayer with the default audio file
        try{
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(
                            Objects.requireNonNull(this.getClass().getResource("audio/pixel-dreams-259187.wav")));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch(Exception ex)
        {
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
        Parent root = loader.load();
        MainMenuController controller = loader.getController();
        controller.setStage(primaryStage);  // Passing stage to controller



        // Set the preferred window size
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(getClass().getResource("/com/example/vp_simulator/styles/styles.css").toExternalForm());
        primaryStage.setScene(scene);

        // Optional: disable resizing
        //primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);


        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
