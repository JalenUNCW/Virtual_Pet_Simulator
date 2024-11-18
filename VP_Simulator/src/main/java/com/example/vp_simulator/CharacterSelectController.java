package com.example.vp_simulator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class CharacterSelectController {

    // Declare UI elements
    @FXML
    private Button dogButton;

    @FXML
    private Button catButton;

    @FXML
    private ImageView dogImageView;

    @FXML
    private ImageView catImageView;

    // Stage to switch scenes
    private Stage stage;

    // Setter method to pass the stage to the controller
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Method to initialize any required UI setup when the scene is loaded
    @FXML
    private void initialize() {
        // Additional setup if needed (e.g., hiding/showing elements, setting initial images, etc.)
    }

    // Handle Dog button action
    @FXML
    private void handleDogButtonAction() throws IOException {
        // Print to confirm the action
        System.out.println("Dog button clicked");

        // Load the game screen scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-screen.fxml"));
        Parent root = loader.load();

        // Get the controller for the game screen
        gameScreenController controller = loader.getController();
        controller.setSelectedPet("Dog");  // Set the selected pet to "Dog"

        // Create a new scene for the game screen
        Scene gameScene = new Scene(root, 600, 400);
        stage.setScene(gameScene);
        stage.setTitle("Game Screen - Dog Selected");
        stage.show();

        System.out.println("Scene switched to game screen with Dog selected");
    }

    // Handle Cat button action
    @FXML
    private void handleCatButtonAction() throws IOException {
        // Print to confirm the action
        System.out.println("Cat button clicked");

        // Load the game screen scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-screen.fxml"));
        Parent root = loader.load();

        // Get the controller for the game screen
        gameScreenController controller = loader.getController();
        controller.setSelectedPet("Cat");  // Set the selected pet to "Cat"

        // Create a new scene for the game screen
        Scene gameScene = new Scene(root, 600, 400);
        stage.setScene(gameScene);
        stage.setTitle("Game Screen - Cat Selected");
        stage.show();

        System.out.println("Scene switched to game screen with Cat selected");
    }
}



