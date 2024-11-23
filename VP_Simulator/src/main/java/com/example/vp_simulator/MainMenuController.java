package com.example.vp_simulator;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainMenuController {

    public Label titleLabel;
    public VBox mainVBox;
    public ImageView petImage;
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

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView mainmenuImage;

    // Bind image dimensions to anchor pane
    @FXML
    public void initialize() {
        // Bind image dimensions to anchor pane (if applicable)
        mainmenuImage.fitWidthProperty().bind(anchorPane.widthProperty());
        mainmenuImage.fitHeightProperty().bind(anchorPane.heightProperty());

        // Initialize pet image
        Image pet = new Image(getClass().getResource("images/lab_dog_image.png").toExternalForm());
        petImage.setImage(pet);
        petImage.setFitWidth(150); // Adjust size as needed
        petImage.setFitHeight(150);
        petImage.setLayoutX(20); // Starting X position
        petImage.setLayoutY(anchorPane.getHeight() - (-850)); // Starting Y position, near the bottom

        // Add animation
        animatePet();

        // Listen for changes in Hardcore Mode
        AppState.hardcoreModeProperty().addListener((observable, oldValue, newValue) -> updateState(newValue));
        updateState(AppState.isHardcoreMode());
    }

    private void animatePet() {
        // First pet setup
        Timeline timeline1 = new Timeline();
        final int[] direction1 = {1}; // Direction for the first pet (1 = right, -1 = left)

        KeyFrame moveFirstPet = new KeyFrame(
                Duration.millis(50),
                event -> {
                    double newX1 = petImage.getLayoutX() + (5 * direction1[0]);

                    // Reverse direction if pet hits the screen edges
                    if (newX1 + petImage.getFitWidth() >= anchorPane.getWidth() || newX1 <= 0) {
                        direction1[0] *= -1;
                    }

                    // Apply the new position
                    petImage.setLayoutX(newX1);
                }
        );

        timeline1.getKeyFrames().add(moveFirstPet);
        timeline1.setCycleCount(Animation.INDEFINITE);
        timeline1.play();

        // Second pet setup
        ImageView petImage2 = new ImageView(new Image(getClass().getResource("images/siamese_cat_image.png").toExternalForm()));
        petImage2.setFitWidth(150); // Adjust size as needed
        petImage2.setFitHeight(150);
        petImage2.setLayoutX(350); // Start from the right edge
        petImage2.setLayoutY(anchorPane.getHeight() - (-850)); // Align with the bottom of the screen

        // Add the second pet to the AnchorPane
        anchorPane.getChildren().add(petImage2);

        Timeline timeline2 = new Timeline();
        final int[] direction2 = {-1}; // Direction for the second pet (opposite to the first pet)

        KeyFrame moveSecondPet = new KeyFrame(
                Duration.millis(50),
                event -> {
                    double newX2 = petImage2.getLayoutX() + (5 * direction2[0]);

                    // Reverse direction if pet hits the screen edges
                    if (newX2 + petImage2.getFitWidth() >= anchorPane.getWidth() || newX2 <= 0) {
                        direction2[0] *= -1;
                    }

                    // Apply the new position
                    petImage2.setLayoutX(newX2);
                }
        );

        timeline2.getKeyFrames().add(moveSecondPet);
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();
    }

    public void updateState(boolean isHardcore) {
        // Check if Hardcore Mode is on and set the background accordingly
        if (isHardcore) {
            // Set the hardcore background
            Image hardcoreBackground = new Image(getClass().getResource("images/hell_intense_landscape.jpg").toExternalForm());
            mainmenuImage.setImage(hardcoreBackground);

            // Optionally, you could also resume the music here if desired
            MediaManager.playMusic("audio/05. BFG Division 2020.wav");
        } else {
            // Set normal background
            Image normalBackground = new Image(getClass().getResource("images/mainmenu-backgroundimage.jpg").toExternalForm());
            mainmenuImage.setImage(normalBackground);

            // Optionally, you could also play normal music here if desired
            MediaManager.playMusic("audio/gentle-fields-194622.wav");
        }
    }

    // Method to handle the Play button action
    @FXML
    private void handlePlayButtonAction() {
        try {
            // Load the Character Select screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("character-select.fxml"));
            Parent root = loader.load();

            // Set the new controller and pass stage
            CharacterSelectController controller = loader.getController();
            controller.setStage(stage);

            // Set scene and stage details
            Scene scene = new Scene(root, 1200, 800);
            scene.getStylesheets().add(getClass().getResource("/com/example/vp_simulator/styles/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setTitle("Character Select Screen");
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load Character Select screen: " + e.getMessage());
        }
    }

    // Method to handle the Settings button action
    @FXML
    private void handleSettingsButtonAction() {
        try {
            // Load the Settings screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("settings-menu.fxml"));
            Parent root = loader.load();

            // Set the new controller and pass stage
            SettingsController controller = loader.getController();
            controller.setStage(stage);

            // Set scene and stage details
            Scene scene = new Scene(root, 1200, 800);
            scene.getStylesheets().add(getClass().getResource("/com/example/vp_simulator/styles/settingsStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setTitle("Settings Menu");
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load Settings Menu: " + e.getMessage());
        }
    }

    // Method to handle the Quit button action
    @FXML
    private void handleQuitButtonAction() {
        // Stop any playing music and close the application window
        MediaManager.stopMusic();
        stage.close();
    }
}

