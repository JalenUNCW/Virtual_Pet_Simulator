package com.example.vp_simulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class VP_Simulator extends Application {

    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double SCREEN_WIDTH = screenSize.getWidth();
    double SCREEN_HEIGHT = screenSize.getHeight();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
        Parent root = loader.load();

        MainMenuController controller = loader.getController();
        controller.setStage(primaryStage);  // Passing stage to controller

        // Set the preferred window size
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        primaryStage.setScene(scene);

        // Optional: disable resizing
        primaryStage.setResizable(false);

        primaryStage.setTitle("Virtual Pet Simulator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
