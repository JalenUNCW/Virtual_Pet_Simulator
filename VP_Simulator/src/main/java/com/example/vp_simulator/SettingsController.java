package com.example.vp_simulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.net.URL;


import java.io.IOException;

public class SettingsController {

    public Slider hardcoreModeSlider;
    public ImageView backgroundImage;
    public Button achievementButton;
    public Label settingsLabel;
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

        // Load the background image
        URL imageUrl = getClass().getResource("images/grasslands.jpg"); // Use absolute path from resources
        if (imageUrl != null) {
            Image image = new Image(imageUrl.toExternalForm());
            backgroundImage.setImage(image);
        } else {
            System.err.println("Image not found!");
        }

        // Defer scene binding until the scene is ready
        backgroundImage.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                // Bind the ImageView size to the scene size
                backgroundImage.fitHeightProperty().bind(newScene.heightProperty());
                backgroundImage.fitWidthProperty().bind(newScene.widthProperty());

                // Optionally, set image to preserve aspect ratio (if needed)
                backgroundImage.setPreserveRatio(true);
            }
        });

        // Bind volume slider to MediaManager's volume property
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

    public void handleAchievementButton(ActionEvent actionEvent) {
        try {
            // Load the main menu scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("finalAchievements.fxml"));
            Parent root = loader.load();

            // Get the MainMenuController and pass stage
            AchievementController achievementController = loader.getController();
            achievementController.setStage(stage);

            Scene scene = new Scene(root, 1200, 800);
            scene.getStylesheets().add(getClass().getResource("/com/example/vp_simulator/styles/achievementStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setTitle("Main Menu");
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load Main Menu: " + e.getMessage());
        }
    }
}
