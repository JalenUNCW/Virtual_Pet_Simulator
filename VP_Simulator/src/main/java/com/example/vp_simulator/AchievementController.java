package com.example.vp_simulator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class AchievementController{
    public Label descriptionLabel;
    public Label achievementLabelHunger;
    public Label achievementLabelWalk;
    public Label achievementLabelTraining;
    public Label achievementLabelVet;
    public Label achievementLabelPoor;
    public TextArea achievementTextHunger;
    public TextArea achievementTextWalk;
    public TextArea achievementTextTraining;
    public TextArea achievementTextVet;
    public TextArea achievementTextPoor;
    public TextArea achievementTextFive;
    public Label achievementLabelShowman;
    public ImageView checkfive;
    public ImageView checkhunger;
    public ImageView checkwalk;
    public ImageView checktrain;
    public ImageView checkvet;
    public ImageView checkpoor;
    private Stage stage;
    public void setStage(Stage stage){this.stage = stage;};

    // create achievement variables as well as accessors and mutators
    private static boolean showman;
    public static boolean getShowman() { return showman; }
    public static void setShowman(boolean bool) { showman = bool; }

    private static boolean hungerGames;
    public static boolean getHungerGames() { return hungerGames; }
    public static void setHungerGames(boolean bool) { hungerGames = bool; }

    private static boolean firstWalk;
    public static boolean getFirstWalk() { return firstWalk; }
    public static void setFirstWalk(boolean bool) { firstWalk = bool; }

    private static boolean train;
    public static boolean getTrain() { return train; }
    public static void setTrain(boolean bool) { train = bool; }

    private static boolean vetVisit;
    public static boolean getVetVisit() { return vetVisit; }
    public static void setVetVisit(boolean bool) { vetVisit = bool; }

    private static boolean badOwner;
    public static boolean getBadOwner() { return badOwner; }
    public static void setBadOwner(boolean bool) { badOwner = bool; }

    @FXML
    private Button btgButton;

    @FXML
    private Button bmmButton;

    private List<ImageView> checkmarks;

    //Creates a public method that goes back to the game screen
    @FXML
    private void btgButtonAction() throws IOException{
        System.out.println("Back to Game: ");
        //Loads the Game Screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-screen.fxml"));
        Parent root = loader.load();

        //Gets the controller for the game screen
        gameScreenController gamescreenController = loader.getController();

        //passes the current stage to the game stage
        Stage stage = (Stage) btgButton.getScene().getWindow();
        gamescreenController.setStage(stage);

        //Sets the new scene
        Scene gamescreenScene = new Scene(root, 1200, 800);

        stage.setScene(gamescreenScene);
        //sets the screen to full screen
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
        //sets the screen to full screen
        stage.setFullScreen(true);
        stage.setTitle("Main Menu");
        stage.show();  // Show the main menu scene
    }

    public void initialize() {

        int count = 0;

        if (showman) {
            count++;
        }
        if (hungerGames) {
            count++;
        }
        if (badOwner) {
            count++;
        }
        if (vetVisit) {
            count++;
        }
        if (train) {
            count++;
        }
        if (firstWalk) {
            count++;
        }
        if (count >= 5) {
            setShowman(true);
        }

        checkfive.setVisible(showman);
        checkhunger.setVisible(hungerGames);
        checkpoor.setVisible(badOwner);
        checkvet.setVisible(vetVisit);
        checktrain.setVisible(train);
        checkwalk.setVisible(firstWalk);
    }
    //Loads the Achievement Screen
    public void start(Stage stage) throws IOException {


        showCheckmarksBasedOnCondition();


        Parent root = FXMLLoader.load(getClass().getResource("finalAchievements.fxml"));
        //Sets a scroll pane for the Achievement window but is not used didnt have enough time to implement it
        ScrollPane scrollpane = new ScrollPane();
        scrollpane.setContent(root);
        //Sets the vertical scroll bar as needed
        scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        //Sets hbar to never
        scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        Scene scene = new Scene(root);
        stage.setTitle("Pet Achievements");
        stage.setScene(scene);
        stage.show();
    }
    public void showCheckmarksBasedOnCondition() {
        boolean[] conditions = {true, false, true, false, true, false, true, false, true};
        for (int i = 0; i < checkmarks.size(); i++) {
            checkmarks.get(i).setVisible(conditions[i]); // Apply condition to each checkmark
        }
    }


}

