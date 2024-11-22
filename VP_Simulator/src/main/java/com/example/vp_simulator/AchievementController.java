package com.example.vp_simulator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
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

public class AchievementController extends VP_Simulator {
    public void setStage(Stage stage) {}
    enum isUnlocked {Locked, Unlocked};

    public void start(Stage stage) throws IOException {
        achievementGridPane.prefWidthProperty().bind(hbox.widthProperty());
        achievementGridPane.prefHeightProperty().bind(hbox.heightProperty());

        headerGridPane.prefHeightProperty().bind(hbox.heightProperty());
        headerGridPane.prefWidthProperty().bind(hbox.widthProperty());

        achievementLabel.prefHeightProperty().bind(hbox.heightProperty());
        achievementLabel.prefWidthProperty().bind(hbox.widthProperty());

        Parent root = FXMLLoader.load(getClass().getResource("finalAchievements.fxml"));

        ScrollPane scrollpane = new ScrollPane();
        scrollpane.setContent(root);

        scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        int targetRow = achievementGridPane.getRowCount();
        achievementGridPane.getChildren().forEach(node -> {
            Integer rowIndex = GridPane.getRowIndex(node);

            if (rowIndex == null) rowIndex = 0;
            if (rowIndex == targetRow){
                unlockAchivement(isUnlocked.Unlocked);
            }
        });


        Scene scene = new Scene(root);
        stage.setTitle("Pet Achievements");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private ListView<String> achievementsList;

    @FXML
    private GridPane achievementGridPane;

    @FXML
    private Button allButton;

    @FXML
    private Label achievementLabel;

    @FXML
    private TextArea achievementTextArea;

    @FXML
    private Button bmmButton;

    @FXML
    private GridPane headerGridPane;

    @FXML
    private HBox hbox;

    @FXML
    private Button miscButtons;

    @FXML
    private Button petfeatsButton;

    @FXML
    private ScrollPane scrollPane;

    private Stage stage;

    @FXML
    private void allButtonAction() {
        System.out.println("All Button Pressed: ");
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allFeats.fxml"));
            Parent root = loader.load();

            MainMenuController mainMenu = loader.getController();
            mainMenu.setStage(stage);

            Scene mainmenuScene = new Scene(root, 1200, 800);
            mainmenuScene.getStylesheets().add(getClass().getResource("/com/example/vp_simulator/styles/styles.css").toExternalForm());
            stage.setScene(mainmenuScene);
            stage.setFullScreen(true);
            stage.setTitle("Main Menu");
            stage.show();
        }catch (IOException e){
            System.err.println("Failed to load Screen: "+ e.getMessage());
        }
    }

    @FXML
    private void petfeatsButtonAction() {
        System.out.println("Pet Feats Button Pressed: ");

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("petFeats.fxml"));
            Parent root = loader.load();

            MainMenuController mainMenu = loader.getController();
            mainMenu.setStage(stage);

            Scene mainmenuScene = new Scene(root, 1200, 800);
            mainmenuScene.getStylesheets().add(getClass().getResource("/com/example/vp_simulator/styles/styles.css").toExternalForm());
            stage.setScene(mainmenuScene);
            stage.setFullScreen(true);
            stage.setTitle("Main Menu");
            stage.show();
        }catch (IOException e){
            System.err.println("Failed to load Screen: "+ e.getMessage());
        }
    }

    @FXML
    private void miscButtonAction() {
        System.out.println("Miscellaneous Button Pressed: ");

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("miscFeats.fxml"));
            Parent root = loader.load();

            MainMenuController mainMenu = loader.getController();
            mainMenu.setStage(stage);

            Scene mainmenuScene = new Scene(root, 1200, 800);
            mainmenuScene.getStylesheets().add(getClass().getResource("/com/example/vp_simulator/styles/styles.css").toExternalForm());
            stage.setScene(mainmenuScene);
            stage.setFullScreen(true);
            stage.setTitle("Main Menu");
            stage.show();
        }catch (IOException e){
            System.err.println("Failed to load Screen: "+ e.getMessage());
        }
    }

    @FXML
    private void bmmButtonAction() {
        System.out.println("Back to Main Menu Pressed: ");

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
            Parent root = loader.load();

            MainMenuController mainMenu = loader.getController();
            mainMenu.setStage(stage);

            Scene mainmenuScene = new Scene(root, 1200, 800);
            mainmenuScene.getStylesheets().add(getClass().getResource("/com/example/vp_simulator/styles/styles.css").toExternalForm());
            stage.setScene(mainmenuScene);
            stage.setFullScreen(true);
            stage.setTitle("Main Menu");
            stage.show();
        }catch (IOException e){
            System.err.println("Failed to load Screen: "+ e.getMessage());
        }
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

}

