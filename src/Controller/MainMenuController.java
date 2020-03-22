package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import javafx.stage.Stage;

import static java.util.Objects.requireNonNull;

public class MainMenuController   implements Initializable {

    @FXML
    Button createGameButton, loadGameButton, settingsButton, helpButton, creditsButton, exitButton, helpLink;




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
//    public static void openWebpage(String urlString) {
//        try {
//            Desktop.getDesktop().browse(new URL(urlString).toURI());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    @FXML
//    public void helpLinkButtonClicked( MouseEvent event) {
//
//        openWebpage("http://www.feuerland-spiele.de/dateien/Terra_Mystica_EN_1.0_.pdf");
//
//    }


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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    /*
    @Override
    public void start(Stage stage) throws Exception {


        creditsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("creditsMenu.fxml"));
                    stage.getScene().setRoot(root);
                    System.out.print("girdi");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


     */
}
