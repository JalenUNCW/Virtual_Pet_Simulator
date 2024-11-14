package com.example.vp_simulator;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    private Stage stage;

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
        // Code to navigate to the game scene
        System.out.println("Play button clicked");

        // Load the character select scene (FXML)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("character-select.fxml"));  // Adjust path as needed
        Parent root = loader.load();  // Load the FXML file
        Scene scene = new Scene(root);  // Create a new scene with the loaded FXML

        // Set the new scene on the current stage
        stage.setScene(scene);  // Replace the current scene with the character select scene
        stage.show();  // Ensure the stage is shown with the new scene
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
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }


}

