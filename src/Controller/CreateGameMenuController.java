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
import java.io.Serializable;
public class CreateGameMenuController extends MainMenuController implements Initializable, Serializable {

    @FXML
    TextField player1, player2, player3, player4, player5;
    @FXML
    ChoiceBox<String> playerCount;
    @FXML
    Button playButton;
    String playerCountString;

    @FXML
    public void playButtonClicked( MouseEvent event) throws IOException {
        Stage stage;


        stage = (Stage) creditsButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MenuViews/ChooseFactionMenuView.fxml"));
        Parent root = loader.load();

        ChooseFactionMenuController controller = loader.getController();
        controller.getNumbersOfPlayer(playerCount.getSelectionModel().getSelectedItem());
        controller.getNamesOfPlayer(player1.getText(), player2.getText(), player3.getText(), player4.getText(), player5.getText());

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }

    @FXML
    public void playerCountIsSelected(ActionEvent event){

        playerCountString = playerCount.getSelectionModel().getSelectedItem();

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
