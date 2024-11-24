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
import java.util.ArrayList;
import java.util.List;

public class MainMenuController {

    public Label titleLabel;
    public VBox mainVBox;
    public ImageView petImage;
    public ImageView petImage2;
    public ImageView petImage3;
    public ImageView petImage4;
    private Stage stage;
    private final List<ImageView> pets = new ArrayList<>();


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

    @FXML
    public void initialize() {
        // Bind main menu image dimensions to the anchor pane
        mainmenuImage.fitWidthProperty().bind(anchorPane.widthProperty());
        mainmenuImage.fitHeightProperty().bind(anchorPane.heightProperty());

        // Add pets and animations when the AnchorPane is ready
        anchorPane.widthProperty().addListener((observable, oldValue, newValue) -> setupPets());
        anchorPane.heightProperty().addListener((observable, oldValue, newValue) -> setupPets());

        // Listen for changes in Hardcore Mode
        AppState.hardcoreModeProperty().addListener((observable, oldValue, newValue) -> updateState(newValue));
        updateState(AppState.isHardcoreMode());
    }

    private void setupPets() {
        anchorPane.getChildren().removeAll(pets);
        pets.clear();// Clear existing pets to avoid duplicates

        // Initialize and animate pets
        addAndAnimatePet("images/lab_dog_image.png", 20, 5, 1); // Dog
        addAndAnimatePet("images/siamese_cat_image.png", anchorPane.getWidth() - 170, 5, -1); // Siamese Cat
        addAndAnimatePet("images/german_shep_dog.png", 200, 3, 1); // Golden Retriever
        addAndAnimatePet("images/ragdoll_cat_image.png", anchorPane.getWidth() - 400, 3, -1); // Bengal Cat
    }

    private void addAndAnimatePet(String imagePath, double startX, int speed, int initialDirection) {
        ImageView pet = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        pet.setFitWidth(150);
        pet.setFitHeight(150);
        pet.setLayoutX(startX); // Starting position
        pet.setLayoutY(anchorPane.getHeight() - 150); // Bottom of the screen
        pets.add(pet);
        anchorPane.getChildren().add(pet);

        animateSinglePet(pet, speed, initialDirection);
    }

    private void animateSinglePet(ImageView pet, int speed, int initialDirection) {
        Timeline timeline = new Timeline();
        final int[] direction = {initialDirection};

        KeyFrame movePet = new KeyFrame(
                Duration.millis(50),
                event -> {
                    double newX = pet.getLayoutX() + (speed * direction[0]);

                    // Reverse direction if pet hits the screen edges
                    if (newX + pet.getFitWidth() >= anchorPane.getWidth() || newX <= 0) {
                        direction[0] *= -1;
                    }

                    // Apply the new position
                    pet.setLayoutX(newX);
                }
        );

        timeline.getKeyFrames().add(movePet);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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

