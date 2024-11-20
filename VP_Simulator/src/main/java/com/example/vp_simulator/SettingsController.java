package com.example.vp_simulator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private Slider hardcoreModeSlider;

    @FXML
    private ToggleButton hardcoreModeToggle;

    @FXML
    private Button mainmenuSettingsButton;

    @FXML
    private Label volumeLabel;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Stage stage;

    // Setter method to pass the stage to the controller
    public void setStage(Stage stage) { this.stage = stage;
    }

    // Method to initialize any required UI setup when the scene is loaded
    @FXML
    private void initialize() {
        // Additional setup if needed (e.g., hiding/showing elements, setting initial images, etc.)
    }

}
