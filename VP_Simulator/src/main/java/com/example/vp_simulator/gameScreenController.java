package com.example.vp_simulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class gameScreenController {

    @FXML
    private Button achievementButton;

    @FXML
    private ImageView backgroundImageDisplay;

    @FXML
    private ProgressBar energyBar;

    @FXML
    private Button feedButton;

    @FXML
    private ProgressBar happinessBar;

    @FXML
    private ProgressBar healthBar;

    @FXML
    private ProgressBar hungerBar;

    @FXML
    private Button menuButton;

    @FXML
    private ImageView petImage;

    @FXML
    private ToggleButton playToggle;

    @FXML
    private Button trainButton;

    @FXML
    private Button vetButton;

    @FXML
    private ToggleButton walkToggle;

    private String selectedPet;

    // Method to set the selected pet (called from CharacterSelectController)
    public void setSelectedPet(String pet) {
        this.selectedPet = pet;
        updatePetDetails();
    }

    // Update the pet image and related UI elements based on the selected pet
    private void updatePetDetails() {
        if (selectedPet.equals("Dog")) {
            Image dogImage = new Image("file:images/cutedog.jpg");  // Adjust path as needed
            petImage.setImage(dogImage);
        } else if (selectedPet.equals("Cat")) {
            Image catImage = new Image("file:images/cutecat.png");  // Adjust path as needed
            petImage.setImage(catImage);
        }
    }

    // Handle Menu button press (Go back to Main Menu)
    @FXML
    void menuPressed() throws IOException {
        // Load the main menu scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
        Parent root = loader.load();

        // Get the controller for the MainMenuController
        MainMenuController mainMenuController = loader.getController();

        // Pass the current stage to the MainMenuController
        Stage stage = (Stage) menuButton.getScene().getWindow();
        mainMenuController.setStage(stage);

        // Set the new scene
        Scene mainMenuScene = new Scene(root, 1200, 800); // Set the size of the main menu scene

        stage.setScene(mainMenuScene);
        stage.setFullScreen(true);
        stage.setTitle("Main Menu");
        stage.show();  // Show the main menu scene
    }

    // Handle other button presses (achievements, feed, play, etc.)
    @FXML
    void achievementPressed(ActionEvent event) {
        // Handle achievement button press
    }

    @FXML
    void feedPressed(ActionEvent event) {
        // Handle feed button press
    }

    @FXML
    void playPressed(ActionEvent event) {
        // Handle play button press
    }

    @FXML
    void trainPressed(ActionEvent event) {
        // Handle train button press
    }

    @FXML
    void vetPressed(ActionEvent event) throws IOException {
        // Load the vet office scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vetOffice.fxml"));
        Parent root = loader.load();

        // Get the controller for the vetOfficeController
        vetOfficeController vetController = loader.getController();

        // Pass the current stage to the vetController
        Stage stage = (Stage) vetButton.getScene().getWindow();
        vetController.setStage(stage);

        // Set the new scene
        Scene vetOfficeScene = new Scene(root, 1200, 800); // Set the size of the scene

        stage.setScene(vetOfficeScene);
        stage.setFullScreen(true);
        stage.setTitle("Vet Office");
        stage.show();  // Show the vet office scene
    }

    @FXML
    void walkPressed(ActionEvent event) {
        // Handle walk button press
    }

}


