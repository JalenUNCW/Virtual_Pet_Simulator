package com.example.vp_simulator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AchievementController extends VP_Simulator {
    enum isUnlocked {Locked, Unlocked};

    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Achievements.fxml"));

        ScrollPane scrollpane = new ScrollPane();
        scrollpane.setContent(root);

        scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);



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
    private Label bathroomLabel;

    @FXML
    private TextArea bathroomTextArea;

    @FXML
    private Label bdayLabel;

    @FXML
    private TextArea bdayTextArea;

    @FXML
    private Button bmmButton;

    @FXML
    private Label firstwalkLabel;

    @FXML
    private TextArea firstwalkTextArea;

    @FXML
    private Label hungerLabel;

    @FXML
    private TextArea hungerTextArea;

    @FXML
    private Button miscButtons;

    @FXML
    private TextArea nightTextArea;

    @FXML
    private Button petfeatsButton;

    @FXML
    private ScrollBar scrollBar;

    @FXML
    private Label sleepLabel;

    @FXML
    private Label tdLabel;

    @FXML
    private TextArea trainingdayTextArea;

    @FXML
    private Label vvLabel;

    @FXML
    private TextArea vvTextArea;

    @FXML
    private void allButtonAction() {
        System.out.println("All Button Pressed: ");

    }

    @FXML
    private void petfeatsButtonAction() {
        System.out.println("Pet Feats Button Pressed: ");
    }

    @FXML
    private void miscButtonAction() {
        System.out.println("Miscellaneous Button Pressed: ");
    }

    @FXML
    private void bmmButtonAction() {
        Stage stage = (Stage) bmmButton.getScene().getWindow();
        System.out.println("Back to Main Menu Pressed: ");
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

