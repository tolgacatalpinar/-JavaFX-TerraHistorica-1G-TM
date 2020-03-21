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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateGameMenuController extends MainMenuController implements Initializable {

    @FXML
    TextField player1, player2, player3, player4, player5;

    @FXML
    ChoiceBox<String> playerCount;

    @FXML
    Button playButton;

    String playerNumber;



    @FXML
    public void playButtonClicked( MouseEvent event) throws IOException {
        Stage stage;


        stage = (Stage) creditsButton.getScene().getWindow();
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("/View/MenuViews/ChooseFactionMenuView.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);

        stage.show();

    }





    @FXML
    public void playerCountIsSelected(ActionEvent event){

        playerNumber = playerCount.getSelectionModel().getSelectedItem();


        if(playerCount.getSelectionModel().getSelectedItem().equals("2")){
            player3.setVisible(false);
            player4.setVisible(false);
            player5.setVisible(false);
        }
        else if(playerCount.getSelectionModel().getSelectedItem().equals("3")){
            player3.setVisible(true);
            player4.setVisible(false);
            player5.setVisible(false);
        }
        else if(playerCount.getSelectionModel().getSelectedItem().equals("4")){
            player3.setVisible(true);
            player4.setVisible(true);
            player5.setVisible(false);
        }
        else if(playerCount.getSelectionModel().getSelectedItem().equals("5")){
            player3.setVisible(true);
            player4.setVisible(true);
            player5.setVisible(true);
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }





}
