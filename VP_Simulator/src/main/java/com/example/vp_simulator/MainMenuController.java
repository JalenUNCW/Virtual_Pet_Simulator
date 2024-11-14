package com.example.vp_simulator;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private Button playButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button quitButton;

    // Method to handle the Play button action
    @FXML
    private void handlePlayButtonAction() {
        // Code to navigate to the game scene
        System.out.println("Play button clicked");
        // Load the game scene here
    }

    // Method to handle the Settings button action
    @FXML
    private void handleSettingsButtonAction() {
        // Code to navigate to the settings scene
        System.out.println("Settings button clicked");
        // Load the settings scene here
    }

    // Method to handle the Quit button action
    @FXML
    private void handleQuitButtonAction() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}

