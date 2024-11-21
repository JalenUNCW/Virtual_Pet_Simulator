package com.example.vp_simulator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {

    public Slider hardcoreModeSlider;
    private Stage stage;
    private boolean isTrack1Playing = true;

    // Setter for stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Slider volumeSlider;

    @FXML
    private ToggleButton hardcoreModeToggle;

    @FXML
    private Button mainmenuSettingsButton;

    @FXML
    private Label volumeLabel;

    // Handle initialization
    @FXML
    private void initialize() {
        // Bind volume slider to the MediaManager volume property
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            MediaManager.setVolume(newValue.doubleValue() / 100); // Scale to 0-1
        });

        // Set initial volume position
        volumeSlider.setValue(MediaManager.getVolume() * 100); // Get volume as a percentage
    }

    // Handle toggling between two music tracks
    @FXML
    private void handleToggleMusic() {
        if (isTrack1Playing) {
            MediaManager.playMusic("audio/05. BFG Division 2020.wav"); // Track 2
            hardcoreModeToggle.setText("HARDCORE ON");
        } else {
            MediaManager.playMusic("audio/gentle-fields-194622.wav"); // Track 1
            hardcoreModeToggle.setText("HARDCORE OFF");
        }
        isTrack1Playing = !isTrack1Playing;
    }

    // Navigate back to the main menu
    @FXML
    private void mainmenuSettingsButton() {


        try {
            // Load the main menu scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
            Parent root = loader.load();

            // Get the MainMenuController and pass stage
            MainMenuController mainMenuController = loader.getController();
            mainMenuController.setStage(stage);

            Scene mainMenuScene = new Scene(root, 1200, 800);
            mainMenuScene.getStylesheets().add(getClass().getResource("/com/example/vp_simulator/styles/styles.css").toExternalForm());
            stage.setScene(mainMenuScene);
            stage.setFullScreen(true);
            stage.setTitle("Main Menu");
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load Main Menu: " + e.getMessage());
        }
    }

    public Button getMainmenuSettingsButton() {
        return mainmenuSettingsButton;
    }

    public void setMainmenuSettingsButton(Button mainmenuSettingsButton) {
        this.mainmenuSettingsButton = mainmenuSettingsButton;
    }

    public Label getVolumeLabel() {
        return volumeLabel;
    }

    public void setVolumeLabel(Label volumeLabel) {
        this.volumeLabel = volumeLabel;
    }
}
