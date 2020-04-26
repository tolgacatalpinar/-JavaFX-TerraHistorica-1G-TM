package Controller.ActionsControllers;

import Model.GameHandler;
import Model.Player;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class UpdateSpadeController {

    public void showUpdateSpadeDialogs(GameHandler gameHandler){

        Player player = gameHandler.getPlayerList()[gameHandler.getCurrentPlayer()];
        int priestCost = player.getFaction().SPADE_PRIEST_COST;
        int goldCost = player.getFaction().SPADE_GOLD_COST;
        int workerCost = player.getFaction().SPADE_WORKER_COST;
        int playerPriest = player.getPriestNum();
        int playerGold = player.getGoldNum();
        int playerWorker = player.getWorkerNum();
        if( priestCost<= playerPriest && goldCost <= playerGold &&  workerCost <= playerWorker  && player.getSpadeLevel() < 3){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Upgrade Spade");
            alert.setHeaderText("GOLD COST : " + goldCost +"\n" +
                    "PRIEST COST : " + priestCost + "\n" +
                    "WORKER COST : " + workerCost);
            alert.setContentText("Do you wan to update your spade level \n" +
                    "Current Level : " + player.getSpadeLevel() + "\n" +
                    "New Level : " + (player.getSpadeLevel() + 1) );
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                player.setGoldNum(playerGold-goldCost);
                player.setPriestNum(playerPriest-priestCost);
                player.setSpadeLevel(player.getSpadeLevel() + 1);
                player.setVictoryPointNum(player.getVictoryPointNum() + (player.getSpadeLevel() == 2 ? player.getFaction().SPADE_FIRST_UPGRADE_VICTORY : player.getFaction().SPADE_SECOND_UPGRADE_VICTORY) );
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Spade level");
            if(player.getSpadeLevel() == 3 ){
                alert.setContentText("You have max spade level");
            }
            else{
                alert.setContentText("You have no required cost, priest, worker \n" +
                        "GOLD COST : " + goldCost +"\n" +
                        "PRIEST COST : " + priestCost + "\n" +
                        "WORKER COST : " + workerCost);
            }

            alert.setHeaderText("You cannot do this action!!");
            alert.showAndWait();
        }


    }
}
