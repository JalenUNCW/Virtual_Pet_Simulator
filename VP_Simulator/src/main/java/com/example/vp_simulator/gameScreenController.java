package com.example.vp_simulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private ImageView petImageDisplay;

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
            petImageDisplay.setImage(dogImage);
        } else if (selectedPet.equals("Cat")) {
            Image catImage = new Image("file:images/cutecat.png");  // Adjust path as needed
            petImageDisplay.setImage(catImage);
        }
    }

    @FXML
    void achievementPressed(ActionEvent event) {
        // Handle achievement button press
    }

    @FXML
    void feedPressed(ActionEvent event) {
        // Handle feed button press
    }

    @FXML
    void menuPressed(ActionEvent event) {
        // Handle menu button press
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
    void vetPressed(ActionEvent event) {
        // Handle vet button press
    }

    @FXML
    void walkPressed(ActionEvent event) {
        // Handle walk button press
    }
}

