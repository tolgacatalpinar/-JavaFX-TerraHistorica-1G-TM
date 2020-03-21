package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseFactionMenuController extends CreateGameMenuController implements Initializable {

    @FXML
    Button backButton;

    @FXML
    ImageView wastelandFaction, forestFaction, lakesFaction, desertFaction, mountainsFaction, swampFaction, plainsFaction;

    @FXML
    public void backButtonClicked( MouseEvent event) throws IOException {
        Stage stage;


        stage = (Stage) backButton.getScene().getWindow();
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("/View/MenuViews/MainMenuView.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*

        Image imProfile = new Image(getClass().getResourceAsStream("/sample/MainMenuBackground.png"));
        wastelandFaction.setImage(imProfile);


         */


    }


}
