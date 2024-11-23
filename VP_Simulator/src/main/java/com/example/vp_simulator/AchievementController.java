package com.example.vp_simulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AchievementController{
    private Stage stage;
    public void setStage(Stage stage){this.stage = stage;};

    enum isUnlocked {Locked, Unlocked};

    @FXML
    private ImageView checkMark1;
    @FXML
    private ImageView checkMark2;
    @FXML
    private ImageView checkMark3;
    @FXML
    private ImageView checkMark4;
    @FXML
    private ImageView checkMark5;
    @FXML
    private ImageView checkMark6;
    @FXML
    private ImageView checkMark7;
    @FXML
    private ImageView checkMark8;
    @FXML
    private ImageView checkMark9;

    @FXML
    private ListView<String> achievementsList;

    @FXML
    private GridPane achievementGridPane;

    @FXML
    private Label achievementLabel;

    @FXML
    private TextArea achievementTextArea;

    @FXML
    private GridPane headerGridPane;

    @FXML
    private HBox hbox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button btgButton;

    @FXML
    private Button bmmButton;

    @FXML
    private void btgButtonAction() throws IOException{
        System.out.println("Back to Game: ");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-screen.fxml"));
        Parent root = loader.load();


        gameScreenController gamescreenController = loader.getController();

        Stage stage = (Stage) btgButton.getScene().getWindow();
        gamescreenController.setStage(stage);

        Scene gamescreenScene = new Scene(root, 1200, 800);

        stage.setScene(gamescreenScene);
        stage.setFullScreen(true);
        stage.setTitle("Game Screen");
        stage.show();

    }

    @FXML
    private void bmmButtonAction() throws IOException {
        System.out.println("Back to Main Menu Pressed: ");
        // Load the main menu scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
        Parent root = loader.load();

        // Get the controller for the MainMenuController
        MainMenuController mainMenuController = loader.getController();

        // Pass the current stage to the MainMenuController
        Stage stage = (Stage) bmmButton.getScene().getWindow();
        mainMenuController.setStage(stage);

        // Set the new scene
        Scene mainMenuScene = new Scene(root, 1200, 800); // Set the size of the main menu scene

        stage.setScene(mainMenuScene);
        stage.setFullScreen(true);
        stage.setTitle("Main Menu");
        stage.show();  // Show the main menu scene
    }

    public void unlockAchivement(isUnlocked status) {
        if (status == isUnlocked.Unlocked) {
            achievementGridPane.getChildren().forEach(node -> {
                Integer rowIndex = GridPane.getRowIndex(node);
                if (rowIndex != null && status == isUnlocked.Unlocked) {
                    node.setEffect(new DropShadow(10, Color.WHITE));

                    node.setStyle("-fx-background-color: brown;");
                }
            });

        }
    }
    public void lockedAchievement(isUnlocked status){
        if (status == isUnlocked.Locked){
            achievementGridPane.getChildren().forEach(node -> {
                Integer rowIndex = GridPane.getRowIndex(node);
                if (rowIndex != null && status == isUnlocked.Locked){
                    node.setEffect(new DropShadow(10, Color.WHITE));

                    node.setStyle("-fx-background-color: grey;");
                }
            });
        }
    }

    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("finalAchievements.fxml"));

        ScrollPane scrollpane = new ScrollPane();
        scrollpane.setContent(root);

        scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        Scene scene = new Scene(root);
        stage.setTitle("Pet Achievements");
        stage.setScene(scene);
        stage.show();
    }

}

