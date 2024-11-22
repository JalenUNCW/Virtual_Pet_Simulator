package com.example.vp_simulator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class vetOfficeController {


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



    private Stage stage;

    @FXML
    public void setStage(Stage stage){ this.stage = stage; }

    @FXML
    private ImageView backgroundImageView; // Background image
    @FXML
    private StackPane rootPane;  // Root layout
    @FXML
    private ImageView vetImageView;
    @FXML
    public GridPane gridPane;

    // The image of the vet to be displayed when "Vet Enters"
    private Image vetImage = new Image(getClass().getResourceAsStream("images/pixil-frame-0.png"));

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
    }

    @FXML
    public void vetEnters() {
        // When the vet button is clicked, display the vet image
        vetImageView.setImage(vetImage);
        vetImageView.setVisible(true);
    }

    @FXML
    public void handleBackButtonPressed() throws IOException {
        // Load the main menu scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-screen.fxml"));
        Parent root = loader.load();

        // Get the controller for the MainMenuController
        MainMenuController mainMenuController = loader.getController();

        // Pass the current stage to the MainMenuController
        Stage stage = (Stage) mainMenuButton.getScene().getWindow();
        mainMenuController.setStage(stage);

        // Set the new scene
        Scene mainMenuScene = new Scene(root, 1200, 800); // Set the size of the main menu scene

        stage.setScene(mainMenuScene);
        stage.setFullScreen(true);
        stage.setTitle("Main Menu");
        stage.show();  // Show the main menu scene

    }


}
