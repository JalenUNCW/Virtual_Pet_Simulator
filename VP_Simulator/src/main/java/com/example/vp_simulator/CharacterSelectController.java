package com.example.vp_simulator;  // Update with your package name

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class CharacterSelectController {

    @FXML
    private Button dogButton;

    @FXML
    private Button catButton;

    @FXML
    private ImageView dogImageView;

    @FXML
    private ImageView catImageView;

    @FXML
    private void initialize() {
    }

    // Handle dog button click
    @FXML
    private void handleDogButtonAction() {
        System.out.println("Dog button clicked!");
    }

    // Handle cat button click
    @FXML
    private void handleCatButtonAction() {
        System.out.println("Cat button clicked!");
    }
}

