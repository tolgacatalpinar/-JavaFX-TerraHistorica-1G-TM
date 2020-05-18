package Controller;

import Model.FileManager;
import Model.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import static java.util.Objects.requireNonNull;
import java.io.Serializable;
public class MainMenuController   implements Initializable, Serializable {
    @FXML
    Button createGameButton, loadGameButton, settingsButton, helpButton, creditsButton, exitButton, helpLink;
    @FXML
    Pane mapPane;
    @FXML
    public void createGameButtonClicked( MouseEvent event) throws IOException {
        Stage stage;
        stage = (Stage) creditsButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MenuViews/CreateGameMenuView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadGameButtonClicked( MouseEvent event) throws IOException {
        Stage stage;
        stage = (Stage) creditsButton.getScene().getWindow();
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("/View/MenuViews/LoadGameMenuView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void settingsButtonClicked( MouseEvent event) throws IOException {
        Stage stage;
        stage = (Stage) creditsButton.getScene().getWindow();
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("/View/MenuViews/SettingsMenuView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void helpButtonClicked( MouseEvent event) throws IOException {
        Stage stage;
        stage = (Stage) creditsButton.getScene().getWindow();
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("/View/MenuViews/HelpMenuView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();

    }

    public static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void helpLinkButtonClicked( MouseEvent event) {
        openWebpage("http://www.feuerland-spiele.de/dateien/Terra_Mystica_EN_1.0_.pdf");
    }

    @FXML
    public void creditsButtonClicked( MouseEvent event) throws Exception {
        Stage stage;
        stage = (Stage) creditsButton.getScene().getWindow();
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("/View/MenuViews/CreditsMenuView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void exitButtonClicked( MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void loadGameClicked(MouseEvent event)throws IOException{
        Stage stage;
        stage = (Stage) loadGameButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/GameView.fxml"));
        Parent root = loader.load();
        System.out.println("Load Game Button Clicked!");
        File save = new File("15.txt");
        GameController game = loader.getController();
        Button [][] terrains = new Button[9][13];
        for (int i = 0; i < 117; i++) {

            int row = i / 13;
            int col = i % 13;
            terrains[row][col] = new Button();
        }
        Map map = game.getMap();
        game = FileManager.loadGame(save,game);
        game.updateMap(terrains, map);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setX(0);
        stage.setY(0);
        stage.show();
        game.loadGameClicked();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       //SoundController.playIntroMusic(5);
    }

}
