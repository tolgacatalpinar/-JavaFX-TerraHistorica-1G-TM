package Controller.ActionsControllers;

import Model.GameHandler;
import Model.Map;
import Controller.GameController;
import Model.Player;
import Model.Space;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class TerraformController {

    public static void updateTerraform(GameHandler gameHandler, Button[][] terrains, Map map){

        Player player = gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()];
        int workerCost = player.getTerraformWorkerCost();
        int spadeLevel = player.getSpadeLevel();
        int workerNum = player.getWorkerNum();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Terraform");

        if(workerNum < workerCost) {
            alert.setContentText("You do not have enough workers to buy a spade\n" +
                    "Worker cost to buy a spade: " + workerCost + "\n" +
                    "Current Worker: " + workerNum);
            alert.setHeaderText("You cannot do this action!");
            alert.showAndWait();
        }
        else{

        }

    }
}
