package com.example.vp_simulator;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class vetOfficeController {

    private Stage stage;

    @FXML
    private ProgressBar checkUpProgressBar;

    @FXML
    private Label groomProgressBarLabel;

    @FXML
    private Label checkUpProgressBarLabel;

    @FXML
    private ImageView petImageView;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button achievementsButton;

    @FXML
    private Button checkUpButton;

    @FXML
    private Button goBackButton;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button groomButton;

    @FXML
    public void setStage(Stage stage){ this.stage = stage; }

    @FXML
    private ImageView backgroundImageView; // Background image
    @FXML
    private AnchorPane rootPane;  // Root layout
    @FXML
    private ImageView vetImageView;
    @FXML
    public GridPane gridPane;

    // variable to be used for progress bar for checkup
    private double progress;

    // The image of the vet to be displayed when "Vet Enters"
    private Image vetImage = new Image(getClass().getResourceAsStream("images/pixil-frame-0.png"));

    // image of the selected pet will go here
    private Image petImage;

    @FXML
    public void initialize() {

        // Bind background image to resize with the window
        backgroundImageView.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundImageView.fitHeightProperty().bind(rootPane.heightProperty());

        // Set GridPane to grow with the window
        gridPane.prefWidthProperty().bind(rootPane.widthProperty());
        gridPane.prefHeightProperty().bind(rootPane.heightProperty());

        // Bind the vet image to fill the right side of the screen
        vetImageView.fitWidthProperty().bind(gridPane.widthProperty().multiply(0.5)); // Make the vet image 50% of the screen width
        vetImageView.fitHeightProperty().bind(gridPane.heightProperty()); // Make the vet image the full height of the GridPane

        // Optional: Set an initial state for the vet image to be hidden or not visible at first
        vetImageView.setVisible(false);

        // add a way to set the pet image in the initializer so it will load with the selected pet
        petImageView.setImage(getPetImage());
        petImageView.setVisible(true);

    }

    // this method will find which pet is selected so it can place the image in the vet scene
    public Image getPetImage(){
        String pet_breed = CharacterSelectController.getPet().breedToString();
        if (pet_breed.equals("Shepherd")) {
            petImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/german_shep_dog.png")));}
        if (pet_breed.equals("Lab")){
            petImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/lab_dog_image.png")));}
        if (pet_breed.equals("Ragdoll")){
            petImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/ragdoll_cat_image.png")));}
        if (pet_breed.equals("Siamese")){
            petImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/siamese_cat_image.png")));}
        return petImage;
    }

    @FXML
    public void vetEnters() {
        // Display the vet image
        vetImageView.setImage(vetImage);
        vetImageView.setVisible(true);

        // make the progress bar and label visible
        checkUpProgressBar.setVisible(true);
        checkUpProgressBarLabel.setVisible(true);

        // set progress to 0 and pass that to the progress bar
        progress = 0.0;
        checkUpProgressBar.setProgress(progress);

        // create a Timeline to increment the progress bar
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE); // this will allow the progress par to disappear after being full

        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(200),
                event -> {
                    progress += 0.1;
                    checkUpProgressBar.setProgress(progress); // update the progress bar

                    // while the progress is less than 1 (full)
                    if (progress >= 1.0) {
                        timeline.stop();

                        // hide the progress bar and label after it's full
                        checkUpProgressBar.setVisible(false);
                        checkUpProgressBarLabel.setVisible(false);
                        // also hide the vet b/c his job is done!
                        vetImageView.setVisible(false);

                        // now increase the pet's health value
                        int petHealth = gameScreenController.pet.getHealth();
                        if (petHealth < 60) {
                            gameScreenController.pet.setHealth(petHealth + 40);
                        } else {
                            gameScreenController.pet.setHealth(100);
                        }
                    }
                }
        ));

        timeline.play();
    }

    @FXML
    public void handleBackButtonPressed() throws IOException {
        // Load the game screen scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-screen.fxml"));
        Parent root = loader.load();

        // Get the controller for the gameScreenController
        gameScreenController gameScreenController = loader.getController();

        // Pass the current stage to the gameScreenController
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        gameScreenController.setStage(stage);

        // Set the new scene
        Scene gameScreen = new Scene(root, 1200, 800); // Set the size of the game scene

        stage.setScene(gameScreen);
        stage.setFullScreen(true);
        stage.setTitle("Game Menu");
        stage.show();  // Show the game scene

    }
    @FXML
    public void handleMainMenuButtonPressed() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
        Parent root = loader.load();

        MainMenuController mainMenuController = loader.getController();

        Stage stage = (Stage) mainMenuButton.getScene().getWindow();
        mainMenuController.setStage(stage);

        Scene mainMenuScene = new Scene(root, 1200, 800);
        stage.setScene(mainMenuScene);
        stage.setFullScreen(true);
        stage.setTitle("Main Menu");
        stage.show();
    }

    @FXML
    public void handleAchievementsButtonPressed() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("finalAchievements.fxml"));
        Parent root = loader.load();

        AchievementController achievementController = loader.getController();

        Stage stage = (Stage) achievementsButton.getScene().getWindow();
        achievementController.setStage(stage);

        Scene achievementsScene = new Scene(root,1200,800);
        stage.setScene(achievementsScene);
        stage.setFullScreen(true);
        stage.setTitle("Achievements");
        stage.show();
    }

    public void groomButtonPressed(ActionEvent actionEvent) {
        // Display the vet image
        vetImageView.setImage(vetImage);
        vetImageView.setVisible(true);

        // make the progress bar and label visible
        checkUpProgressBar.setVisible(true);
        groomProgressBarLabel.setVisible(true);

        // set progress to 0 and pass that to the progress bar
        progress = 0.0;
        checkUpProgressBar.setProgress(progress);

        // create a Timeline to increment the progress bar
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE); // this will allow the progress par to disappear after being full

        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(200),
                event -> {
                    progress += 0.1;
                    checkUpProgressBar.setProgress(progress); // update the progress bar

                    // while the progress is less than 1 (full)
                    if (progress >= 1.0) {
                        timeline.stop();

                        // hide the progress bar and label after it's full
                        checkUpProgressBar.setVisible(false);
                        groomProgressBarLabel.setVisible(false);
                        // also hide the vet b/c his job is done!
                        vetImageView.setVisible(false);

                        // now increase the pet's health value
                        int petHealth = gameScreenController.pet.getHealth();
                        if (petHealth < 60) {
                            gameScreenController.pet.setHealth(petHealth + 40);
                        } else {
                            gameScreenController.pet.setHealth(100);
                        }
                    }
                }
        ));
        timeline.play();
    }
}
