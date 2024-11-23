package com.example.vp_simulator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    public Label titleLabel;
    public VBox mainVBox;
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
        // Bind image dimensions to anchor pane (if applicable)
        mainmenuImage.fitWidthProperty().bind(anchorPane.widthProperty());
        mainmenuImage.fitHeightProperty().bind(anchorPane.heightProperty());

        // Listen for changes in Hardcore Mode
        AppState.hardcoreModeProperty().addListener((observable, oldValue, newValue) -> updateState(newValue));
        updateState(AppState.isHardcoreMode());
    }

    public void updateState(boolean isHardcore) {
        // Check if Hardcore Mode is on and set the background accordingly
        if (isHardcore) {
            // Set the hardcore background
            Image hardcoreBackground = new Image(getClass().getResource("images/hell_intense_landscape.jpg").toExternalForm());
            mainmenuImage.setImage(hardcoreBackground);

            // Optionally, you could also resume the music here if desired
            MediaManager.playMusic("audio/05. BFG Division 2020.wav");
        } else {
            // Set normal background
            Image normalBackground = new Image(getClass().getResource("images/mainmenu-backgroundimage.jpg").toExternalForm());
            mainmenuImage.setImage(normalBackground);

            // Optionally, you could also play normal music here if desired
            MediaManager.playMusic("audio/gentle-fields-194622.wav");
        }
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
            scene.getStylesheets().add(getClass().getResource("/com/example/vp_simulator/styles/settingsStyle.css").toExternalForm());
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

