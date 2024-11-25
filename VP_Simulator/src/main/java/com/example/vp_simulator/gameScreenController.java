package com.example.vp_simulator;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Controller for the game screen, managing user interactions, pet attributes, and game logic.
 */
public class gameScreenController {

    // Transition for displaying temporary messages, such as achievements
    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(3));
    private boolean messageDisplay = false;

    // Set to track triggered actions for achievements
    private Set<String> triggeredActions = new HashSet<>();

    // Achievement counters
    private int feedCount = 0;
    private int walkCount = 0;
    private int trainCount = 0;
    private int playCount = 0;
    private int vetVisitCount = 0;

    // FXML UI components
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

    public static Pet pet;

    // Timeline for decrementing pet attributes periodically
    private Timeline decrementTimer;

    /**
     * Sets the selected pet for the game session.
     *
     * @param pet the name of the selected pet
     */
    public void setSelectedPet(String pet) {
        selectedPet = pet;
        updatePetDetails();
    }

    /**
     * Updates the pet details on the UI, including the image and relevant attributes.
     */
    private void updatePetDetails() {
        // Update pet image based on breed
        if (pet.breedToString().equals("Shepherd")) {
            petImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/german_shep_dog.png"))));
        } else if (pet.breedToString().equals("Lab")) {
            petImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/lab_dog_image.png"))));
        } else if (pet.breedToString().equals("Ragdoll")) {
            petImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/ragdoll_cat_image.png"))));
        } else if (pet.breedToString().equals("Siamese")) {
            petImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/siamese_cat_image.png"))));
        }
    }

    /**
     * Sets the stage for the current controller.
     *
     * @param stage the stage to be used
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Identifies the type of the current pet and updates the UI accordingly.
     */
    public void findselected() {
        if (pet instanceof Cat) {
            setSelectedPet("Cat");
        } else if (pet instanceof Dog) {
            setSelectedPet("Dog");
        } else {
            setSelectedPet("Unknown");
        }
    }

    /**
     * Periodically decreases the pet's attributes (health, happiness, hunger, energy).
     */
    @FXML
    public void decrementProgress() {
        pet.setHealth(pet.getHealth() - 1);
        pet.setHappiness(pet.getHappiness() - 1);
        pet.setHunger(pet.getHunger() - 1);
        pet.setEnergy(pet.getEnergy() - 1);

        // Update progress bars to reflect new values
        handleProgressEvent();

        // Check if any attribute has fallen to critical levels
        if (pet.getHealth() <= 0 || pet.getEnergy() <= 0 || pet.getHunger() <= 0 || pet.getHappiness() <= 0) {
            showMessage(false, unlockedLabel, pauseTransition);
        }
    }

    /**
     * Returns the user to the main menu.
     */
    @FXML
    void menuPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
        Parent root = loader.load();

        MainMenuController mainMenuController = loader.getController();
        Stage stage = (Stage) menuButton.getScene().getWindow();
        mainMenuController.setStage(stage);

        Scene mainMenuScene = new Scene(root, 1200, 800);

        stage.setScene(mainMenuScene);
        stage.setFullScreen(true);
        stage.setTitle("Main Menu");
        stage.show();
        stopDecrementTimer();
    }

    /**
     * Displays the achievements screen.
     */
    @FXML
    void achievementPressed(ActionEvent event) throws IOException {
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

    /**
     * Feeds the pet and triggers related achievements.
     */
    @FXML
    void feedPressed(ActionEvent event) {
        pet.feed();
        handleProgressEvent();
        if (feedCount == 0) {
            showMessage(true, unlockedLabel, pauseTransition);
            feedCount++;
        }
        AchievementController.setHungerGames(true);
    }

    /**
     * Allows the user to play with the pet and triggers related achievements.
     */
    @FXML
    void playPressed(ActionEvent event) {
        pet.play();
        handleProgressEvent();
        if (playCount == 0) {
            showMessage(true, unlockedLabel, pauseTransition);
            playCount++;
        }
    }

    /**
     * Trains the pet and triggers related achievements.
     */
    @FXML
    void trainPressed(ActionEvent event) {
        pet.train();
        handleProgressEvent();
        if (trainCount == 0) {
            showMessage(true, unlockedLabel, pauseTransition);
            trainCount++;
        }
        AchievementController.setTrain(true);
    }

    /**
     * Sends the pet to the vet and displays the vet office screen.
     */
    @FXML
    void vetPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vetOffice.fxml"));
        Parent root = loader.load();

        if (vetVisitCount == 0) {
            showMessage(true, unlockedLabel, pauseTransition);
            vetVisitCount++;
        }

        vetOfficeController vetController = loader.getController();
        Stage stage = (Stage) vetButton.getScene().getWindow();
        vetController.setStage(stage);

        Scene vetOfficeScene = new Scene(root, 2000, 1200);
        stage.setScene(vetOfficeScene);
        stage.setFullScreen(true);
        stage.setTitle("Vet Office");
        stage.show();
        stopDecrementTimer();

        AchievementController.setVetVisit(true);
    }

    /**
     * Takes the pet on a walk and triggers related achievements.
     */
    @FXML
    void walkPressed(ActionEvent event) {
        Image outingBackground = AppState.isHardcoreMode() ?
                new Image(getClass().getResource("images/hellish_landscape_walk.jpg").toExternalForm()) :
                new Image(getClass().getResource("images/outing.jpg").toExternalForm());

        Image originalBackground = backgroundImage.getImage();
        backgroundImage.setImage(outingBackground);

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> backgroundImage.setImage(originalBackground));
        pause.play();

        pet.outing();
        handleProgressEvent();

        if (walkCount == 0) {
            showMessage(true, unlockedLabel, pauseTransition);
            walkCount++;
        }

        AchievementController.setFirstWalk(true);
    }

    /**
     * Initializes the game screen, setting up bindings and starting timers.
     */
    @FXML
    public void initialize() {
        mainpane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                backgroundImage.fitWidthProperty().bind(newScene.widthProperty());
                backgroundImage.fitHeightProperty().bind(newScene.heightProperty());
            }
        });

        pet = CharacterSelectController.getPet();
        findselected();

        if (selectedPet.equals("Cat")) {
            walkToggle.setText("Let Out");
        }

        updatePetDetails();

        nameLabel.setText(pet.getName());
        typeLabel.setText(selectedPet);
        breedLabel.setText(pet.breedToString());

        startDecrementTimer();
        AppState.hardcoreModeProperty().addListener((observable, oldValue, newValue) -> updateState(newValue));
        updateState(AppState.isHardcoreMode());
    }

    /**
     * Updates the game state based on Hardcore Mode settings.
     *
     * @param isHardcore whether Hardcore Mode is enabled
     */
    public void updateState(boolean isHardcore) {
        if (isHardcore) {
            backgroundImage.setImage(new Image(getClass().getResource("images/dungeon.jpg").toExternalForm()));
            MediaManager.playMusic("audio/05. BFG Division 2020.wav");
        } else {
            backgroundImage.setImage(new Image(getClass().getResource("images/home.jpg").toExternalForm()));
            MediaManager.playMusic("audio/gentle-fields-194622.wav");
        }
    }

    /**
     * Updates progress bars and their styles based on pet attributes.
     */
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

    /**
     * Updates the style of a progress bar based on its value.
     *
     * @param progressBar the progress bar to update
     * @param value       the value of the progress bar
     */
    private void updateProgressBarStyle(ProgressBar progressBar, double value) {
        if (value > 0.75) {
            progressBar.setStyle("-fx-accent: green;");
        } else if (value > 0.25) {
            progressBar.setStyle("-fx-accent: yellow;");
        } else {
            AchievementController.setBadOwner(true);
            progressBar.setStyle("-fx-accent: red;");
        }
    }

    /**
     * Starts the decrement timer to periodically reduce pet attributes.
     */
    private void startDecrementTimer() {
        decrementTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> decrementProgress()));
        decrementTimer.setCycleCount(Timeline.INDEFINITE);
        decrementTimer.play();
    }

    /**
     * Stops the decrement timer.
     */
    public void stopDecrementTimer() {
        if (decrementTimer != null) {
            decrementTimer.stop();
        }
    }

    /**
     * Displays a temporary message on the screen for a specific action.
     *
     * @param isPressed      the action triggering the message
     * @param label          the label to display the message
     * @param pauseTransition the pause transition for message timing
     */
    public void showMessage(boolean isPressed, Label label, PauseTransition pauseTransition) {
        if (!triggeredActions.contains(String.valueOf(isPressed))) {
            triggeredActions.add(String.valueOf(isPressed));
            label.setText("Achievement\nUnlocked: " + isPressed);

            pauseTransition.setOnFinished(event -> label.setText(""));
            pauseTransition.play();
        }
    }
}
