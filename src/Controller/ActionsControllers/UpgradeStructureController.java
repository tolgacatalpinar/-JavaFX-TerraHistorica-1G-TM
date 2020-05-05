package Controller.ActionsControllers;

import Controller.TerrainController;
import Model.*;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class UpgradeStructureController {
    final static int ROW_NUMBER = 9;
    final static int COLUMN_NUMBER = 13;

    public static void updateMapForUpgradeStructure(GameHandler gameHandler, Button[][] terrains, Map map) {
        TerrainController.disableTerrains(terrains, map);

        for (int i = 0; i < ROW_NUMBER; i++) {
            for (int j = 0; j < COLUMN_NUMBER; j++) {
                if( terrains[i][j] != null)
                    if(map.spaces[i][j].isOccupied() && map.spaces[i][j].getType().equals(gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE) && !map.spaces[i][j].getStructure().getBuilding().equals("Stronghold") && !map.spaces[i][j].getStructure().getBuilding().equals("Sanctuary")) {
                        terrains[i][j].setDisable(false);
                        int finalI = i;
                        int finalJ = j;
                        terrains[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if(map.spaces[finalI][finalJ].getStructure().getBuilding().equals("Dwelling"))
                                    upgradeToTradingPost(gameHandler, terrains, terrains[finalI][finalJ], map, map.spaces[finalI][finalJ]);
                                else if(map.spaces[finalI][finalJ].getStructure().getBuilding().equals("Trading Post"))
                                    upgradeToStrongholdOrTemple(gameHandler, terrains, terrains[finalI][finalJ], map, map.spaces[finalI][finalJ]);
                                else if(map.spaces[finalI][finalJ].getStructure().getBuilding().equals("Temple"))
                                    upgradeToSanctuary(gameHandler, terrains, terrains[finalI][finalJ], map, map.spaces[finalI][finalJ]);
                            }
                        });
                    }
            }
        }
    }

    public static void upgradeToTradingPost(GameHandler gameHandler, Button[][]terrains, Button terrain, Map map, Space space){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Upgrade Structure");
        alert.setHeaderText("Do you want to upgrade this Dwelling to a Trading Post?");
        alert.setContentText("Cost will be here");

        Optional<ButtonType> result = alert.showAndWait();
        if (((Optional) result).get() == ButtonType.OK){
            TerrainController.upgradeToTradingPost(terrain, gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE);
            space.setStructure("Trading Post");
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        TerrainController.enableTerrains(terrains, map);
        TerrainController.disableButtonClicks(terrains);
    }

    public static void upgradeToStrongholdOrTemple(GameHandler gameHandler, Button[][]terrains, Button terrain, Map map, Space space){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Upgrade Structure");
        alert.setHeaderText("Do you want to upgrade this Trading Post to a Stronghold or Temple?");
        alert.setContentText("Cost will be here");

        ButtonType stronghold = new ButtonType("Stronghold");
        ButtonType temple = new ButtonType("Temple");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(stronghold, temple, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == stronghold){
            TerrainController.upgradeToStronghold(terrain, gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE);
            space.setStructure("Stronghold");
        } else if (result.get() == temple) {
            TerrainController.upgradeToTemple(terrain, gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE);
            space.setStructure("Temple");
        } else {
            // ... user chose CANCEL or closed the dialog
        }

        TerrainController.enableTerrains(terrains, map);
        TerrainController.disableButtonClicks(terrains);

    }

    public static void upgradeToSanctuary(GameHandler gameHandler, Button[][]terrains, Button terrain, Map map, Space space){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Upgrade Structure");
        alert.setHeaderText("Do you want to upgrade this Temple to a Sanctuary?");
        alert.setContentText("Cost will be here");

        Optional<ButtonType> result = alert.showAndWait();
        if (((Optional) result).get() == ButtonType.OK){
            TerrainController.upgradeToSanctuary(terrain, gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE);
            space.setStructure("Sanctuary");
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        TerrainController.enableTerrains(terrains, map);
        TerrainController.disableButtonClicks(terrains);

    }
}