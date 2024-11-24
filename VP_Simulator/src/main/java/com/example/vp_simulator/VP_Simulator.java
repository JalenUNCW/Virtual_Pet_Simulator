package com.example.vp_simulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class VP_Simulator extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Initialize the MediaManager and play background music
        MediaManager.initialize("audio/gentle-fields-194622.wav");

        // Load the FXML for the main menu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
        Parent root = loader.load();
        MainMenuController controller = loader.getController();

        // Pass this VP_Simulator instance to the controller if needed
        controller.setStage(primaryStage);

        // Set up the scene and stage
        Scene scene = new Scene(root, 2000, 1200);
        scene.getStylesheets().add(
                getClass().getResource("/com/example/vp_simulator/styles/styles.css").toExternalForm()
        );

        primaryStage.setScene(scene);
        primaryStage.setWidth(Screen.getPrimary().getBounds().getWidth()); // Match screen width
        primaryStage.setHeight(Screen.getPrimary().getBounds().getHeight()); // Match screen height
        primaryStage.setFullScreen(true); // Optional: start in fullscreen (Hardcoded but can be changed using ESC Key
        //primaryStage.setResizable(false);
        primaryStage.setTitle("Main Menu");

        // Disable exit full screen (optional)
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(null);
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
