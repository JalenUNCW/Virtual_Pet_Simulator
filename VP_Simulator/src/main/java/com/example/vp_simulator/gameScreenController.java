package com.example.vp_simulator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class gameScreenController {

    @FXML
    private Button achievementButton;

    @FXML
    private ProgressBar energyBar;

    @FXML
    private Button feedButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label breedLabel;

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
    private AnchorPane imageAnchor;


    @FXML
    private Button trainButton;

    @FXML
    private Button vetButton;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private ToggleButton walkToggle;

    private String selectedPet;

    private Stage stage;

    private Pet pet;

    private Timeline decrementTimer;

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

    @FXML
    public void decrementProgress() {
        pet.setHealth(pet.getHealth() - 1);
        pet.setHappiness(pet.getHappiness() - 1);
        pet.setHunger(pet.getHunger() - 1);
        pet.setEnergy(pet.getEnergy() - 1);

        // Update the progress bars to reflect the new values
        handleProgressEvent();

        //if (pet.getHealth() <= 0 || pet.getEnergy() <= 0 || pet.getHunger() <= 0 || pet.getHappiness() <= 0) {
            ;
            // You can add additional logic here, e.g., pause the decrementTimer or notify the user.
        //}
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
        stopDecrementTimer();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void findselected() {
        if (pet instanceof Cat) {
            selectedPet = "Cat";
        } else if (pet instanceof Dog) {
            selectedPet = "Dog";
        } else {
            selectedPet = "Unknown"; // Fallback in case the pet is of an unexpected type
        }
    }

    // Handle other button presses (achievements, feed, play, etc.)
    @FXML
    void achievementPressed(ActionEvent event) throws IOException {
        // Handle achievement button press

        FXMLLoader loader = new FXMLLoader(getClass().getResource("finalAchievements.fxml"));
        Parent root = loader.load();

        AchievementController achievementController = loader.getController();

        Stage stage = (Stage) achievementButton.getScene().getWindow();
        achievementController.setStage(stage);

        Scene achievementScene = new Scene(root, 2000, 1200);
        stage.setScene(achievementScene);
        stage.setFullScreen(true);
        stage.setTitle("Achievements");
        stage.show();
        stopDecrementTimer();

    }

    @FXML
    void feedPressed(ActionEvent event) {
        pet.feed();
        handleProgressEvent();

    }

    @FXML
    void playPressed(ActionEvent event) {
        pet.play();
        handleProgressEvent();
    }

    @FXML
    void trainPressed(ActionEvent event) {
        // Handle train button press
        pet.train();
        handleProgressEvent();
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
        Scene vetOfficeScene = new Scene(root, 2000, 1200); // Set the size of the scene

        stage.setScene(vetOfficeScene);
        stage.setFullScreen(true);
        stage.setTitle("Vet Office");
        stage.show();  // Show the vet office scene
        stopDecrementTimer();
    }

    @FXML
    void walkPressed(ActionEvent event) {
        pet.outing();
        handleProgressEvent();

    }

    @FXML
    public void initialize() {

        pet = CharacterSelectController.getPet();

        System.out.printf("Here is your pet!: %s%n", pet.getName());

        findselected();

        if (selectedPet.equals("Cat")) {
            walkToggle.setText("Let Out");
        }

        updatePetDetails();

        backgroundImage.fitWidthProperty().bind(imageAnchor.widthProperty());
        backgroundImage.fitHeightProperty().bind(imageAnchor.heightProperty());

        nameLabel.setText(pet.getName());
        typeLabel.setText(selectedPet);
        breedLabel.setText(pet.breedToString());

        // Start the background music when the main menu is initialized
        //MediaManager.playMusic("audio/pixel-dreams-259187.wav");
        startDecrementTimer();
    }

    public void handleProgressEvent() {


        healthBar.setProgress((double) pet.getHealth() /100);
        hungerBar.setProgress((double) pet.getHunger() /100);
        happinessBar.setProgress((double) pet.getHappiness() /100);
        energyBar.setProgress((double) pet.getEnergy() /100);
    }

    private void startDecrementTimer() {
        // Create a Timeline to call decrementProgress every second
        decrementTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> decrementProgress()));
        decrementTimer.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        decrementTimer.play(); // Start the timer
    }

    public void stopDecrementTimer() {
        if (decrementTimer != null) {
            decrementTimer.stop();
        }
    }

}


