package com.example.vp_simulator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
            Scene characterSelectScene = new Scene(root, 600, 400);
            stage.setScene(characterSelectScene);
            stage.setTitle("Character Select");
            stage.show();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to handle the Settings button action
    @FXML
    private void handleSettingsButtonAction() {
        // Code to navigate to the settings scene
        System.out.println("Settings button clicked");
    }

    // Method to handle the Quit button action
    @FXML
    private void handleQuitButtonAction() {
        // Close the application window
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}

