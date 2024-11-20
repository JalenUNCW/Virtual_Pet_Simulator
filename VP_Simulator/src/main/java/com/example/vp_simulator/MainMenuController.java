package com.example.vp_simulator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;

import java.io.IOException;
import java.util.Objects;

public class MainMenuController {


    public VBox mainVBox;
    private Stage stage;
    private MediaPlayer mediaPlayer;

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

    @FXML
    public void initialize() {


        mainmenuImage.fitWidthProperty().bind(anchorPane.widthProperty());
        mainmenuImage.fitHeightProperty().bind(anchorPane.heightProperty());
    }


    // Call this method to stop the music if necessary
    public void stopBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    // Method to handle the Play button action
    @FXML
    private void handlePlayButtonAction() throws IOException {
        try {
            // Load the character selection screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("character-select.fxml"));
            Parent root = loader.load();

            // Get the controller for character selection
            CharacterSelectController controller = loader.getController();
            controller.setStage(stage);  // Pass the stage to the new controller

            // Switch to the character selection scene
            Scene characterSelectScene = new Scene(root, 1200, 800);
            characterSelectScene.getStylesheets().add(getClass().getResource("/com/example/vp_simulator/styles/styles.css").toExternalForm());
            stage.setScene(characterSelectScene);
            stage.setTitle("Character Select");
            stage.show();
            stage.setFullScreen(true);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to handle the Settings button action
    @FXML
    private void handleSettingsButtonAction() {
        // Code to navigate to the settings scene
        System.out.println("Settings button clicked");

        try {
            // Load the character selection screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("settings-menu.fxml"));
            Parent root = loader.load();

            // Get the controller for character selection
            SettingsController controller = loader.getController();
            controller.setStage(stage);  // Pass the stage to the new controller

            // Switch to the character selection scene
            Scene characterSelectScene = new Scene(root, 600, 400);
            characterSelectScene.getStylesheets().add(getClass().getResource("/com/example/vp_simulator/styles/styles.css").toExternalForm());
            stage.setScene(characterSelectScene);
            stage.setTitle("Character Select");
            stage.show();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to handle the Quit button action
    @FXML
    private void handleQuitButtonAction() {
        // Close the application window
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}