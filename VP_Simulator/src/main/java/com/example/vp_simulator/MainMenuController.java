package com.example.vp_simulator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    private Stage stage;

    // Method to set the stage for the controller
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button playButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button quitButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView mainmenuImage;

    // Bind image dimensions to anchor pane
    @FXML
    public void initialize() {
        mainmenuImage.fitWidthProperty().bind(anchorPane.widthProperty());
        mainmenuImage.fitHeightProperty().bind(anchorPane.heightProperty());

        // Start the background music when the main menu is initialized
        //MediaManager.playMusic("audio/pixel-dreams-259187.wav");
    }

    // Method to handle the Play button action
    @FXML
    private void handlePlayButtonAction() {
        try {
            // Load the Character Select screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("character-select.fxml"));
            Parent root = loader.load();

            // Set the new controller and pass stage
            CharacterSelectController controller = loader.getController();
            controller.setStage(stage);

            // Set scene and stage details
            Scene scene = new Scene(root, 1200, 800);
            scene.getStylesheets().add(getClass().getResource("/com/example/vp_simulator/styles/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setTitle("Character Select Screen");
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load Character Select screen: " + e.getMessage());
        }
    }

    // Method to handle the Settings button action
    @FXML
    private void handleSettingsButtonAction() {
        try {
            // Load the Settings screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("settings-menu.fxml"));
            Parent root = loader.load();

            // Set the new controller and pass stage
            SettingsController controller = loader.getController();
            controller.setStage(stage);

            // Set scene and stage details
            Scene scene = new Scene(root, 1200, 800);
            scene.getStylesheets().add(getClass().getResource("/com/example/vp_simulator/styles/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setTitle("Settings Menu");
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load Settings Menu: " + e.getMessage());
        }
    }

    // Method to handle the Quit button action
    @FXML
    private void handleQuitButtonAction() {
        // Stop any playing music and close the application window
        MediaManager.stopMusic();
        stage.close();
    }
}

