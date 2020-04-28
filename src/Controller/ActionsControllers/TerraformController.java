package Controller.ActionsControllers;

import Model.GameHandler;
import Model.Map;
import Controller.GameController;
import Model.Player;
import javafx.scene.control.Alert;

public class TerraformController {

    public void showTerraformTable(GameHandler gameHandler){

        Player player = gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()];
        int workerCost = player.getTerraformWorkerCost();
        int spadeLevel = player.getSpadeLevel();
        int workerNum = player.getWorkerNum();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Terraform");

        if(workerNum < workerCost) {
            alert.setContentText("You do not have enough workers to buy a spade\n" +
                    "Worker cust to buy a spade: " + workerCost + "\n" +
                    "Current Worker: " + workerNum);
            alert.setHeaderText("You cannot do this action!");
            alert.showAndWait();
        }

    }
}
