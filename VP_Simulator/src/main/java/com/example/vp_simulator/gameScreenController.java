package com.example.vp_simulator;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class gameScreenController {
    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
    private boolean messageDisplay = false;
    private Set<String> triggeredActions = new HashSet<>();
    @FXML
    private Label unlockedLabel;

    @FXML
    private Button achievementButton;

    @FXML
    private ProgressBar energyBar;

    @FXML
    private GridPane mainpane;

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
    private StackPane imageAnchor;


    @FXML
    private Button trainButton;

    @FXML
    private Button vetButton;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private ToggleButton walkToggle;

    private static String selectedPet;

    private Stage stage;

    private Pet pet;

    private Timeline decrementTimer;

    // Method to set the selected pet (called from CharacterSelectController)
    public void setSelectedPet(String pet) {
        selectedPet = pet;
        updatePetDetails();
    }

    // Update the pet image and related UI elements based on the selected pet
    private void updatePetDetails() {
        if (pet.breedToString().equals("Shepherd")) {
            petImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/german_shep_dog.png"))));}
        if (pet.breedToString().equals("Lab")){
            petImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/lab_dog_image.png"))));}
        if (pet.breedToString().equals("Ragdoll")){
            petImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/ragdoll_cat_image.png"))));}
        if (pet.breedToString().equals("Siamese")){
            petImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/siamese_cat_image.png"))));}
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void findselected() {
        if (pet instanceof Cat) {
            setSelectedPet("Cat");

        } else if (pet instanceof Dog) {
            setSelectedPet("Dog");

        } else {
            setSelectedPet("Unknown");
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
        //
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
        boolean isPressed = true;
        pet.feed();
        handleProgressEvent();
        if (isPressed) {
            showMessage(isPressed, unlockedLabel, pauseTransition);
            isPressed = false;
        }

    }

    @FXML
    void playPressed(ActionEvent event) {
        boolean isPressed = true;
        pet.play();
        handleProgressEvent();
        if (isPressed ) {
            showMessage(isPressed, unlockedLabel, pauseTransition);
            isPressed = false;
        }
    }

    @FXML
    void trainPressed(ActionEvent event) {
        boolean isPressed = true;
        // Handle train button press
        pet.train();
        handleProgressEvent();
        if (isPressed) {
            showMessage(true, unlockedLabel, pauseTransition);
            isPressed = false;
        }
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
        // Determine the appropriate background image based on Hardcore Mode
        Image outingBackground;
        if (AppState.isHardcoreMode()) {
            outingBackground = new Image(getClass().getResource("images/hellish_landscape_walk.jpg").toExternalForm());
        } else {
            outingBackground = new Image(getClass().getResource("images/outing.jpg").toExternalForm());
        }

        Image originalBackground = backgroundImage.getImage(); // Save the original background image
        backgroundImage.setImage(outingBackground);

        // Start a PauseTransition to revert back after 2 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {
            backgroundImage.setImage(originalBackground);
            System.out.println("Reverted to the original background.");
        });
        pause.play();

        // Code below runs immediately without waiting for the PauseTransition
        pet.outing();
        handleProgressEvent();
        System.out.println("Walk action completed!");

        // The rest of the UI is still responsive

    boolean isPressed = true;
        pet.outing();
        handleProgressEvent();
        if (isPressed) {
            showMessage(isPressed, unlockedLabel, pauseTransition);
            isPressed = false;
        }

       /* if (isPressed && !messageDisplay){
            isPressed = false;
            unlockedLabel.setText("Achievement\nUnlocked: ");

            pauseTransition.setOnFinished(event1 -> unlockedLabel.setText(""));
            pauseTransition.play();
            messageDisplay = true;
        }*/
    }

    @FXML
    public void initialize() {

        // Bind the background image to the Scene dimensions
        mainpane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                backgroundImage.fitWidthProperty().bind(newScene.widthProperty());
                backgroundImage.fitHeightProperty().bind(newScene.heightProperty());
            }
        });

        pet = CharacterSelectController.getPet();

        System.out.printf("Here is your pet!: %s%n", pet.getName());

        findselected();

        if (selectedPet.equals("Cat")) {
            walkToggle.setText("Let Out");
        }

        updatePetDetails();

        nameLabel.setText(pet.getName());
        typeLabel.setText(selectedPet);
        breedLabel.setText(pet.breedToString());

        // Start the background music when the main menu is initialized
        //MediaManager.playMusic("audio/pixel-dreams-259187.wav");
        startDecrementTimer();

        // Listen for changes in Hardcore Mode
        AppState.hardcoreModeProperty().addListener((observable, oldValue, newValue) -> updateState(newValue));
        updateState(AppState.isHardcoreMode());
    }

    public void updateState(boolean isHardcore) {
        // Check if Hardcore Mode is on and set the background accordingly
        if (isHardcore) {
            // Set the hardcore background
            Image hardcoreBackground = new Image(getClass().getResource("images/dungeon.jpg").toExternalForm());
            backgroundImage.setImage(hardcoreBackground);

            // Optionally, you could also resume the music here if desired
            MediaManager.playMusic("audio/05. BFG Division 2020.wav");
        } else {
            // Set normal background
            Image normalBackground = new Image(getClass().getResource("images/home.jpg").toExternalForm());
            backgroundImage.setImage(normalBackground);

            // Optionally, you could also play normal music here if desired
            MediaManager.playMusic("audio/gentle-fields-194622.wav");
        }
    }

    public void handleProgressEvent() {
        double healthProgress = pet.getHealth() / 100.0;
        double hungerProgress = pet.getHunger() / 100.0;
        double happinessProgress = pet.getHappiness() / 100.0;
        double energyProgress = pet.getEnergy() / 100.0;

        healthBar.setProgress(healthProgress);
        hungerBar.setProgress(hungerProgress);
        happinessBar.setProgress(happinessProgress);
        energyBar.setProgress(energyProgress);

        updateProgressBarStyle(healthBar, healthProgress);
        updateProgressBarStyle(hungerBar, hungerProgress);
        updateProgressBarStyle(happinessBar, happinessProgress);
        updateProgressBarStyle(energyBar, energyProgress);
    }

    private void updateProgressBarStyle(ProgressBar progressBar, double value) {
        if (value > 0.75) {
            progressBar.setStyle("-fx-accent: green;");
        } else if (value > 0.25) {
            progressBar.setStyle("-fx-accent: yellow;");
        } else {
            progressBar.setStyle("-fx-accent: red;");
        }
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
    // Method to display the message only once for a given action
    public void showMessage(boolean isPressed, Label label, PauseTransition pauseTransition) {
        if (!triggeredActions.contains(isPressed)) { // Check if the action has not been triggered
            triggeredActions.contains(isPressed);// Mark this action as triggered

            label.setText("Achievement\nUnlocked: " + isPressed);

            pauseTransition.setOnFinished(event -> label.setText(""));
            pauseTransition.play();

        }
    }

}


