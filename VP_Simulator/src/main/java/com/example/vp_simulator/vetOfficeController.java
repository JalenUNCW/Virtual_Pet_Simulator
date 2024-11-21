package com.example.vp_simulator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    @FXML
    private ImageView vetImageView;

    @FXML
    Image vet = new Image(Objects.requireNonNull(getClass().getResourceAsStream("pixil-frame-0.png")));

    @FXML
    public void vetEnters() {
        vetImageView.setImage(vet);
    }
    public void goBackPressed() {

    }


}
