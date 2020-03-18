package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

public class CreateGameMenuController extends MainMenuController implements Initializable {

    @FXML
    TextField player1, player2, player3, player4, player5;

    @FXML
    ChoiceBox<String> playerCount;

    @FXML
    public void playerCountIsSelected(ActionEvent event){

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

}
