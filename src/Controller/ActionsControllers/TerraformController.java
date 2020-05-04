package Controller.ActionsControllers;

import Controller.TerrainController;
import Controller.GameController;
import Model.*;
import Controller.GameController;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TerraformController {
    final static int ROW_NUMBER = 9;
    final static int COLUMN_NUMBER = 13;

    public static void updateMapForTerraform(GameHandler gameHandler, Button[][] terrains, Map map){
        for (int i = 0; i < ROW_NUMBER; i++) {
            for (int j = 0; j < COLUMN_NUMBER; j++) {
                if( terrains[i][j] != null)
                    if(!map.spaces[i][j].isOccupied() || !map.spaces[i][j].getType().equals(gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE))
                        terrains[i][j].setDisable(true);
            }
        }
        Space[] adj;
        for (int i = 0; i < ROW_NUMBER; i++) {
            for (int j = 0; j < COLUMN_NUMBER; j++) {
                if( terrains[i][j] != null && map.spaces[i][j] != null)
                    if(map.spaces[i][j].getType().equals(gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE) && map.spaces[i][j].isOccupied()) {
                        adj = map.adjacencyList(map.spaces[i][j]);
                        for(int k = 0; k < adj.length; k++){
                            if(adj[k] != null && terrains[map.getRow(adj[k])][map.getColumn(adj[k])] != null && !map.spaces[map.getRow(adj[k])][map.getColumn(adj[k])].getType().equals("River") && !map.spaces[map.getRow(adj[k])][map.getColumn(adj[k])].isOccupied() && terrains[map.getRow(adj[k])][map.getColumn(adj[k])].isDisable()) {
                                terrains[map.getRow(adj[k])][map.getColumn(adj[k])].setDisable(false);
                                Space[] finalAdj = adj;
                                int finalK = k;
                                terrains[map.getRow(adj[k])][map.getColumn(adj[k])].setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        TerraformController.terraform(gameHandler, terrains, terrains[map.getRow(finalAdj[finalK])][map.getColumn(finalAdj[finalK])], map, map.spaces[map.getRow(finalAdj[finalK])][map.getColumn(finalAdj[finalK])]);
                                    }
                                });
                            }
                        }
                    }
            }
        }
    }

    public static void terraform(GameHandler gameHandler, Button[][]terrains, Button terrain, Map map, Space space){



        List<String> choices = new ArrayList<>();
        choices.add("Wasteland");
        choices.add("Forest");
        choices.add("Lakes");
        choices.add("Desert");
        choices.add("Mountains");
        choices.add("Swamp");
        choices.add("Plains");
        choices.remove(space.getType());

        ChoiceDialog<String> dialog = new ChoiceDialog<>(gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE, choices);
        dialog.setTitle("Terraform");
        dialog.setHeaderText("Choose a Terrain Tile");
        dialog.setContentText("Terrain Tile: " );

        Optional<String> result = dialog.showAndWait();

        if(result.isPresent()) {
            TerrainController.terraform(terrain, result.get());
            space.setType(result.get());

            //Asks if the player wants to build dwelling after terraforming
            if (result.get().equals(gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Build Dwelling");
                alert.setHeaderText("Do you want to build a dwelling?");
                alert.setContentText("Cost will be here");

                Optional<ButtonType> dwellingCheck = alert.showAndWait();
                if (dwellingCheck.get() == ButtonType.OK) {
                    TerrainController.buildDwelling(terrain, result.get());
                    space.setOccupied(true);
                    space.setStructure("Dwelling");
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
        }
        TerrainController.enableTerrains(terrains, map);
        TerrainController.disableButtonClicks(terrains);
    }
}
