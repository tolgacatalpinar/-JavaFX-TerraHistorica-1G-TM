package Controller.ActionsControllers;

import Model.GameHandler;
import Model.Player;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class UpdateShippingController {
    public void showUpdateShippingDialogs(GameHandler gameHandler){

        Player player = gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()];
        int priestCost = player.getFaction().SHIPPING_PRIEST_COST;
        int goldCost = player.getFaction().SHIPPING_GOLD_COST;
        int playerPriest = player.getPriestNum();
        int playerGold = player.getGoldNum();
        if( priestCost<= playerPriest && goldCost <= playerGold && player.getShipLevel() < 3){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Upgrade Shipping");
            alert.setHeaderText("GOLD COST : " + goldCost +"\n" +
                    "PRIEST COST : " + priestCost);
            alert.setContentText("Do you wan to update your shipping level \n" +
                    "Current Level : " + player.getShipLevel() + "\n" +
                    "New Level : " + (player.getShipLevel() + 1) );
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                player.setGoldNum(playerGold-goldCost);
                player.setPriestNum(playerPriest-priestCost);
                player.setShipLevel(player.getShipLevel() + 1);
                player.setVictoryPointNum(player.getVictoryPointNum() + player.getFaction().SHIPPING_UPGRADE_VICTORY_POINTS[player.getShipLevel()]);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Shipping level");
            if(player.getSpadeLevel() == 3 ){
                alert.setContentText("You have max shipping level");
            }
            else
            {
                alert.setContentText("You have no required cost or priest\n" +
                        "GOLD COST : " + goldCost +"\n" +
                        "PRIEST COST : " + priestCost);
            }

            alert.setHeaderText("You cannot do this action!!");
            alert.showAndWait();
        }
    }
}
