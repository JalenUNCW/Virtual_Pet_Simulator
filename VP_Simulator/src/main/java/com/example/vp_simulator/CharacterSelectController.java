package com.example.vp_simulator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CharacterSelectController {


    public HBox hbox;
    public StackPane dogPane;
    public StackPane catPane;
    public VBox vbox;
    public HBox imageHBox;
    private VP_Simulator vpSimulator;

    public void setVpSimulator(VP_Simulator vpSimulator) { this.vpSimulator = vpSimulator; }
    // Declare UI elements
    @FXML
    private Button dogButton;

    @FXML
    private Button catButton;

    @FXML
    private ImageView dogImageView;

    @FXML
    private ImageView catImageView;

    // Stage to switch scenes
    private Stage stage;

    public ImageView characterselectimage;

    public AnchorPane anchorPane;

    // Setter method to pass the stage to the controller
    public void setStage(Stage stage) {
        this.stage = stage;

    }

    // Method to initialize any required UI setup when the scene is loaded
    @FXML
    private void initialize() {
        // Bind ImageView to cover the full AnchorPane
        characterselectimage.fitWidthProperty().bind(anchorPane.widthProperty());
        characterselectimage.fitHeightProperty().bind(anchorPane.heightProperty());

        // Other initialization code (centering VBox, etc.)
        characterselectimage.setPreserveRatio(false);
        // Center the VBox dynamically inside the AnchorPane
        AnchorPane.setLeftAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(vbox, 0.0);
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setBottomAnchor(vbox, 0.0);

        // Center VBox based on AnchorPane size
        vbox.setLayoutX(anchorPane.getWidth() / 2 - vbox.getWidth() / 2);
        vbox.setLayoutY(anchorPane.getHeight() / 2 - vbox.getHeight() / 2);

        // Resize VBox on window resize
        anchorPane.widthProperty().addListener((_, _, _) -> {
            vbox.setLayoutX(anchorPane.getWidth() / 2 - vbox.getWidth() / 2);
        });

        anchorPane.heightProperty().addListener((_, _, _) -> {
            vbox.setLayoutY(anchorPane.getHeight() / 2 - vbox.getHeight() / 2);
        });
    }


    // Handle Dog button action
    @FXML
    private void handleDogButtonAction() throws IOException {
        // Print to confirm the action
        System.out.println("Dog button clicked");

        // Load the game screen scene
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game-screen.fxml"));
            Parent root = loader.load();
            gameScreenController controller = loader.getController();
            controller.setSelectedPet("Dog");
            Scene gameScene = new Scene(root, 2000, 1200);
            stage.setScene(gameScene);
            stage.setFullScreen(true);
            stage.setTitle("Game Screen - Dog Selected ");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading the game screen.");
        }

        System.out.println("Scene switched to game screen with Dog selected");
    }

    // Handle Cat button action
    @FXML
    private void handleCatButtonAction() throws IOException {
        // Print to confirm the action
        System.out.println("Cat button clicked");

        // Load the game screen scene
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game-screen.fxml"));
            Parent root = loader.load();
            gameScreenController controller = loader.getController();
            controller.setSelectedPet("Cat");
            Scene gameScene = new Scene(root, 1200, 800);
            stage.setScene(gameScene);
            stage.setFullScreen(true);
            stage.setTitle("Game Screen - Cat Selected");
            // Disable the ESC key tooltip that appears in full-screen mode
            gameScene.setOnKeyPressed(event1 -> {
                if (event1.getCode() == KeyCode.ESCAPE) {
                    // Prevent the ESC key from triggering the full-screen tooltip
                    event1.consume();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading the game screen.");
        }

        System.out.println("Scene switched to game screen with Cat selected");
    }


}




