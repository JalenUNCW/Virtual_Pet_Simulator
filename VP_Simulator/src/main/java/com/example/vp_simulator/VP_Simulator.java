package com.example.vp_simulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VP_Simulator extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Initialize the MediaManager and play background music
        MediaManager.initialize("audio/pixel-dreams-259187.wav");

        // Load the FXML for the main menu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
        Parent root = loader.load();
        MainMenuController controller = loader.getController();

        // Pass this VP_Simulator instance to the controller if needed
        controller.setStage(primaryStage);

        // Set up the scene and stage
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(
                getClass().getResource("/com/example/vp_simulator/styles/styles.css").toExternalForm()
        );
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true); // Optional: start in fullscreen
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }

    // Optional: Stop the music when the application exits
    @Override
    public void stop() {
        if (MediaManager.getMediaPlayer() != null) {
            MediaManager.getMediaPlayer().stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


