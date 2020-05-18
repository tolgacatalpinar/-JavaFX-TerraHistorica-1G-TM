package Controller;

import Model.*;
import Model.CardsAndTiles.CardsAndTiles;
import Model.FactionSubclasses.*;
import Model.Map;
import View.ActionsViews.SpecialActionView;
import View.DialogueViews.DialogueImageButton;
import View.DialogueViews.DialogueView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.Serializable;
import java.util.*;

public class ActionController implements Serializable {
   final static int ROW_NUMBER = 9;
   final static int COLUMN_NUMBER = 13;
   private static int selection = -1;
   public static boolean actiondone = false;
   public static boolean canChooseFavorTile  = false;

   public static void terraform(Player current ,Button[][] terrains,Map map, Button[] actions, CardsAndTiles cardsAndTiles, Religion[] religions) {

      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if (terrains[i][j] != null)
               if ((!map.spaces[i][j].isOccupied() || !map.spaces[i][j].getType().equals(current.getFaction().TERRAIN_TILE)))
                  terrains[i][j].setDisable(true);
         }
      }
      Space[] adj;
      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if (terrains[i][j] != null && map.spaces[i][j] != null)
               if (map.spaces[i][j].getType().equals(current.getFaction().TERRAIN_TILE) && map.spaces[i][j].isOccupied()) {
                  adj = map.getAdjacentNonRivers(map.spaces[i][j]);
                  ArrayList<Space> reachableTerrains = new ArrayList<>();
                  map.getShippingEnabledTerrains(current.getShipLevel(), map.spaces[i][j], reachableTerrains);
                  for( int k = 0; k < adj.length; k ++)
                  {
                     if( !reachableTerrains.contains(adj[k]))
                     {
                        reachableTerrains.add(adj[k]);
                     }
                  }
                  for( int k = 0; k < reachableTerrains.size(); k ++)
                  {
                     if (reachableTerrains.get(k) != null && terrains[map.getRow(reachableTerrains.get(k))][map.getColumn(reachableTerrains.get(k))] != null && !map.spaces[map.getRow(reachableTerrains.get(k))][map.getColumn(reachableTerrains.get(k))].getType().equals("River") && !map.spaces[map.getRow(reachableTerrains.get(k))][map.getColumn(reachableTerrains.get(k))].isOccupied() && terrains[map.getRow(reachableTerrains.get(k))][map.getColumn(reachableTerrains.get(k))].isDisable()) {
                        terrains[map.getRow(reachableTerrains.get(k))][map.getColumn(reachableTerrains.get(k))].setDisable(false);
                        ArrayList<Space> finalAdj = reachableTerrains;
                        int finalK = k;
                        terrains[map.getRow(reachableTerrains.get(k))][map.getColumn(reachableTerrains.get(k))].setOnMouseClicked(new EventHandler<MouseEvent>() {
                           @Override
                           public void handle(MouseEvent event) {
                              terraformAction(current, terrains, terrains[map.getRow(finalAdj.get(finalK))][map.getColumn(finalAdj.get(finalK))], map, map.spaces[map.getRow(finalAdj.get(finalK))][map.getColumn(finalAdj.get(finalK))], actions,cardsAndTiles,religions,map.getRow(finalAdj.get(finalK)),map.getColumn(finalAdj.get(finalK)));
                           }
                        });
                     }


                  }
                  ///////////////////////////////////////
//                  for (int k = 0; k < adj.length; k++) {
//                     if (adj[k] != null && terrains[map.getRow(adj[k])][map.getColumn(adj[k])] != null && !map.spaces[map.getRow(adj[k])][map.getColumn(adj[k])].getType().equals("River") && !map.spaces[map.getRow(adj[k])][map.getColumn(adj[k])].isOccupied() && terrains[map.getRow(adj[k])][map.getColumn(adj[k])].isDisable()) {
//                        terrains[map.getRow(adj[k])][map.getColumn(adj[k])].setDisable(false);
//                        Space[] finalAdj = adj;
//                        int finalK = k;
//                        terrains[map.getRow(adj[k])][map.getColumn(adj[k])].setOnMouseClicked(new EventHandler<MouseEvent>() {
//                           @Override
//                           public void handle(MouseEvent event) {
//                              terraformAction(playerArr, curPlayerId, terrains, terrains[map.getRow(finalAdj[finalK])][map.getColumn(finalAdj[finalK])], map, map.spaces[map.getRow(finalAdj[finalK])][map.getColumn(finalAdj[finalK])], actions);
//                           }
//                        });
//                     }
//                  }
               }
         }
         for (int k = 0; k < ROW_NUMBER; k++) {
            for (int l = 0; l < COLUMN_NUMBER; l++) {
               if (terrains[k][l] != null && map.spaces[k][l] != null) {
                  if (map.spaces[k][l].getBridgeConnection() && map.spaces[k][l].getBridgeType().equals(current.getFaction().TERRAIN_TILE)) {
                     terrains[k][l].setDisable(false);
                     int finalK = k;
                     int finalL = l;
                     terrains[k][l].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                           terraformAction(current, terrains, terrains[finalK][finalL], map, map.spaces[finalK][finalL], actions,cardsAndTiles,religions ,finalK, finalL);
                        }
                     });
                  }
               }
            }
         }
      }
   }

   /**
    * BUNU TAŞIYALIM
    *
    */
   public static void terraformAction(Player current, Button[][] terrains, Button terrain, Map map, Space space, Button[] actions,CardsAndTiles cardsAndTiles, Religion[] religions, int x, int y) {
      List<String> choices = new ArrayList<>();
      choices.add("Wasteland");
      choices.add("Forest");
      choices.add("Lakes");
      choices.add("Desert");
      choices.add("Mountains");
      choices.add("Swamp");
      choices.add("Plains");
      choices.remove(space.getType());
      PlayerHandler playerHandler = new PlayerHandler();

      Label prompt = new Label("Please choose the terrain to be transformed into.");
      Label promptChoice = new Label("Terrain type: ");
      ChoiceBox choiceBox = new ChoiceBox();
      choiceBox.getItems().addAll(choices);
      choiceBox.setValue(current.getFaction().TERRAIN_TILE);
      BorderPane pane = new BorderPane();
      HBox choiceBlock = new HBox();
      Button transformButton = new Button("Transform");

      choiceBlock.getChildren().addAll(promptChoice, choiceBox, transformButton);
      VBox whole = new VBox();
      whole.getChildren().addAll(prompt, choiceBlock);
      pane.setCenter(whole);
      pane.setPadding(new Insets(50, 50, 0, 0));
      final Stage terraformStage = DialogueView.getStage("Terraform Action", pane, new Image("favor_tiles_background.jpg"));
      transformButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            String selectedChoice = (String) choiceBox.getValue();
            boolean flag = true;
            if (playerHandler.terraform(current,space.getType())){
               disableActions(actions);
               TerrainController.terraform(terrain, selectedChoice);
               space.setType(selectedChoice);
            }else{
               flag = false;
               System.out.println("No enough resources");
            }
            terraformStage.close();
            if (selectedChoice.equals(current.getFaction().TERRAIN_TILE) && flag) {
               DialogueImageButton dwellingButton = new DialogueImageButton("dialogueDwelling.png");
               DialogueImageButton emptyTerrainButton = new DialogueImageButton("dialogueEmptyTerrain.png");
               VBox pane = DialogueView.getDwellingAfterTerraformPromptPane(current, dwellingButton, emptyTerrainButton);
               Stage dwellingChoiceStage = DialogueView.getStage("Do you want to build dwelling?", pane, new Image("dialogueBackground.png"));
               dwellingChoiceStage.show();
               dwellingButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                  @Override
                  public void handle(MouseEvent event) {
                     ArrayList<Player> adjacentPlayers = map.adjacentPlayers(space,space.getType());
                     int returnCase =  playerHandler.buildStructure(current,"Dwelling",false);
                     if( returnCase == 1) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        for(int i = 0; i < adjacentPlayers.size(); i++) {
                           alert.setTitle("Offer for " + adjacentPlayers.get(i).getNickName());
                           alert.setHeaderText("Do you want to spend victory points to get power?");
                           alert.setContentText("Cost will be here");
                           Optional<ButtonType> result = alert.showAndWait();
                           if (((Optional) result).get() == ButtonType.OK) {
                              playerHandler.acceptPowerFromAdjacentOpponent(1, adjacentPlayers.get(i));
                           } else {
                              // ... user chose CANCEL or closed the dialog
                           }
                        }
                        TerrainController.buildDwelling(terrain, selectedChoice);
                        space.setPlayer(current);
                        space.setOccupied(true);
                        space.setStructure("Dwelling");
                        int townScore = map.calculateTownScore(x,y, current.getFaction().TERRAIN_TILE, current.getTownPowerValue());
                        if(townScore >= current.getTownPowerValue()){
                           playerHandler.townFound(current);
                           CardsAndTilesController cardsAndTilesController = new CardsAndTilesController();
                           cardsAndTilesController.showTownTilesTable(cardsAndTiles, current,religions,true);
                        }
                        System.out.println("Town score is *************"+ townScore);
                     }else if( returnCase == -1){
                        System.out.println("Not enough resources");
                     }else
                        System.out.println("Max reached");
                     dwellingChoiceStage.close();
                  }

               });
               emptyTerrainButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                  @Override
                  public void handle(MouseEvent event) {
                     dwellingChoiceStage.close();
                  }
               });

            }
         }
      });
      terraformStage.show();
      TerrainController.enableTerrains(terrains, map);
      TerrainController.disableButtonClicks(terrains);


//        ChoiceDialog<String> dialog = new ChoiceDialog<>(gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE, choices);
//        dialog.setTitle("Terraform");
//        dialog.setHeaderText("Choose a Terrain Tile");
//        dialog.setContentText("Terrain Tile: " );
//        Optional<String> result = dialog.showAndWait();
//        if(result.isPresent()) {
//            Controller.TerrainController.terraform(terrain, result.get());
//            space.setType(result.get());
//
//            //Asks if the player wants to build dwelling after terraforming
//            if (result.get().equals(gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE)) {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Build Dwelling");
//                alert.setHeaderText("Do you want to build a dwelling?");
//                alert.setContentText("Cost will be here");
//
//                Optional<ButtonType> dwellingCheck = alert.showAndWait();
//                if (dwellingCheck.get() == ButtonType.OK) {
//                    Controller.TerrainController.buildDwelling(terrain, result.get());
//                    space.setOccupied(true);
//                    space.setStructure("Dwelling");
//
//                } else {
//                    // ... user chose CANCEL or closed the dialog
//                }
//            }
//        }
//        Controller.TerrainController.enableTerrains(terrains, map);
//        Controller.TerrainController.disableButtonClicks(terrains);
   }

   public  static void upgradeStructure(Player current,Button[][] terrains, Map map, Button[] actions, CardsAndTiles cardsAndTiles, Religion[] religions) {
      TerrainController.disableTerrains(terrains, map);

      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if (terrains[i][j] != null)
               if (map.spaces[i][j].isOccupied() && map.spaces[i][j].getType().equals(current.getFaction().TERRAIN_TILE) && !map.spaces[i][j].getStructure().getBuilding().equals("Stronghold") && !map.spaces[i][j].getStructure().getBuilding().equals("Sanctuary")) {
                  terrains[i][j].setDisable(false);
                  int finalI = i;
                  int finalJ = j;
                  terrains[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                     @Override
                     public void handle(MouseEvent event) {
                        /**
                         * TODO
                         * LOGİC DEĞİŞİTOR
                         */
                        //gameHandler.upgradeStructure(map.spaces[finalI][finalJ], map.spaces[finalI][finalJ].getStructure().getBuilding())
                        if (map.spaces[finalI][finalJ].getStructure().getBuilding().equals("Dwelling"))
                           upgradeToTradingPost(current, terrains, terrains[finalI][finalJ], map, map.spaces[finalI][finalJ],actions,cardsAndTiles,religions,finalI, finalJ);
                        else if (map.spaces[finalI][finalJ].getStructure().getBuilding().equals("Trading Post"))
                           upgradeToStrongholdOrTemple(current, terrains, terrains[finalI][finalJ], map, map.spaces[finalI][finalJ], actions, cardsAndTiles, religions, finalI, finalJ);
                        else if (map.spaces[finalI][finalJ].getStructure().getBuilding().equals("Temple"))
                           upgradeToSanctuary(current, terrains, terrains[finalI][finalJ], map, map.spaces[finalI][finalJ], actions, cardsAndTiles, religions, finalI, finalJ);

                     }
                  });
               }
         }
      }
   }

   public static void upgradeToTradingPost(Player current, Button[][] terrains, Button terrain, Map map, Space space, Button[] actions,CardsAndTiles cardsAndTiles, Religion[] religions, int x, int y) {
      PlayerHandler playerHandler = new PlayerHandler();
      DialogueImageButton discardButton = new DialogueImageButton("dialogueDiscardDoor.png");
      DialogueImageButton tradingPostButton = new DialogueImageButton("dialogueTradingPost.png");
      VBox pane = DialogueView.getDwellingUpgradePromptPane(current, discardButton, tradingPostButton);

      Stage stage = DialogueView.getStage("Upgrade Dwelling", pane, new Image("dialogueBackground.png"));

      stage.show();
      tradingPostButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            ArrayList<Player> adjacentPlayers = map.adjacentPlayers(space,space.getType());
            System.out.println(adjacentPlayers.size());
            int returnCase = playerHandler.buildStructure(current, "TradingPost", adjacentPlayers.size() != 0);
            /**
             * YANDAKİ UŞAKLARA SOR
             */

            if(returnCase == 1){
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               for(int i = 0; i < adjacentPlayers.size(); i++) {
                  if(adjacentPlayers.get(i) != null) {
                     alert.setTitle("Offer for " + adjacentPlayers.get(i).getNickName());
                     alert.setHeaderText("Do you want to spend victory points to get power?");
                     alert.setContentText("Cost will be here");
                     Optional<ButtonType> result = alert.showAndWait();
                     if (((Optional) result).get() == ButtonType.OK) {
                        playerHandler.acceptPowerFromAdjacentOpponent(2, adjacentPlayers.get(i));
                     } else {
                        // ... user chose CANCEL or closed the dialog
                     }
                  }

               }
               disableActions(actions);
               TerrainController.upgradeToTradingPost(terrain, current.getFaction().TERRAIN_TILE);
               space.setStructure("Trading Post");
               stage.close();
               int townScore = map.calculateTownScore(x,y, current.getFaction().TERRAIN_TILE, current.getTownPowerValue());
               if(townScore >= current.getTownPowerValue()){
                  playerHandler.townFound(current);
                  CardsAndTilesController cardsAndTilesController = new CardsAndTilesController();
                  cardsAndTilesController.showTownTilesTable(cardsAndTiles, current,religions,true);

               }
               System.out.println("Town score is *************"+ townScore);
               actiondone = true;
            }else if (returnCase == -1){
               System.out.println("Not enough resources");
               stage.close();
               actiondone = true;
            }else
               System.out.println("Max reached");
            stage.close();
            actiondone = true;

         }
      });
      discardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            stage.close();
         }
      });
      TerrainController.enableTerrains(terrains, map);
      TerrainController.disableButtonClicks(terrains);
   }

   public static void upgradeToStrongholdOrTemple(Player current, Button[][] terrains, Button terrain, Map map, Space space, Button[] actions, CardsAndTiles cardsAndTiles, Religion[] religions, int x, int y) {
      PlayerHandler playerHandler = new PlayerHandler();
      DialogueImageButton templeButton = new DialogueImageButton("dialogueTemple.png");
      DialogueImageButton strongholdButton = new DialogueImageButton("dialogueStronghold.png");
      VBox pane = DialogueView.getTradingPostUpgradePromptPane(current, templeButton, strongholdButton);

      Stage stage = DialogueView.getStage("Upgrade Trading Post", pane, new Image("dialogueBackground.png"));


      templeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            ArrayList<Player> adjacentPlayers = map.adjacentPlayers(space,space.getType());
            int returnCase = playerHandler.buildStructure(current, "Temple", false);
            /**
             * TODO
             * YANDAKİ UŞAKLARA SOR
             */
            if(returnCase > 0){
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               for(int i = 0; i < adjacentPlayers.size(); i++) {
                  alert.setTitle("Offer for " + adjacentPlayers.get(i).getNickName());
                  alert.setHeaderText("Do you want to spend victory points to get power?");
                  alert.setContentText("Cost will be here");
                  Optional<ButtonType> result = alert.showAndWait();
                  if (((Optional) result).get() == ButtonType.OK) {
                     playerHandler.acceptPowerFromAdjacentOpponent(3, adjacentPlayers.get(i));
                  } else {
                     // ... user chose CANCEL or closed the dialog
                  }
               }
               canChooseFavorTile = true;
               disableActions(actions);
               TerrainController.upgradeToTemple(terrain, current.getFaction().TERRAIN_TILE);
               CardsAndTilesController cardsAndTilesController = new CardsAndTilesController();
               for(int i = 0; i < returnCase; i++) {
                  cardsAndTilesController.showFavorTilesTable(cardsAndTiles, current, religions,true);
               }
               space.setStructure("Temple");
               stage.close();
               int townScore = map.calculateTownScore(x,y, current.getFaction().TERRAIN_TILE, current.getTownPowerValue());
               if(townScore >= current.getTownPowerValue()){
                  playerHandler.townFound(current);
                  cardsAndTilesController.showTownTilesTable(cardsAndTiles, current,religions,true);
               }
               System.out.println("Town score is *************"+ townScore);
               actiondone = true;
            }else if (returnCase == -1){
               System.out.println("Not enough resources");
               stage.close();
               actiondone = true;
            }else
               System.out.println("Max reached");
            stage.close();
            actiondone = true;
         }
      });
      strongholdButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            ArrayList<Player> adjacentPlayers = map.adjacentPlayers(space,space.getType());
            int returnCase = playerHandler.buildStructure(current, "Stronghold", false);
            /**
             * TODO
             * YANDAKİ UŞAKLARA SOR
             */
            if(returnCase == 1){
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               for(int i = 0; i < adjacentPlayers.size(); i++) {
                  alert.setTitle("Offer for " + adjacentPlayers.get(i).getNickName());
                  alert.setHeaderText("Do you want to spend victory points to get power?");
                  alert.setContentText("Cost will be here");
                  Optional<ButtonType> result = alert.showAndWait();
                  if (((Optional) result).get() == ButtonType.OK) {
                     playerHandler.acceptPowerFromAdjacentOpponent(3, adjacentPlayers.get(i));
                  } else {
                     // ... user chose CANCEL or closed the dialog
                  }
               }
               disableActions(actions);
               TerrainController.upgradeToStronghold(terrain, current.getFaction().TERRAIN_TILE);
               space.setStructure("Stronghold");
               current.getSpecialActionToken().setStrongholdAbility(true);

               int townScore = map.calculateTownScore(x,y, current.getFaction().TERRAIN_TILE, current.getTownPowerValue());
               if(townScore >= current.getTownPowerValue()){
                  playerHandler.townFound(current);
                  CardsAndTilesController cardsAndTilesController = new CardsAndTilesController();
                  cardsAndTilesController.showTownTilesTable(cardsAndTiles, current,religions,true);
               }
               System.out.println("Town score is *************"+ townScore);
            }else if (returnCase == -1){
               System.out.println("Not enough resources");
            }else
               System.out.println("Max reached");
            stage.close();
         }
      });
//      noButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
//         @Override
//         public void handle(MouseEvent event) {
//            stage.close();
//            actiondone = true;
//         }
//      });
      TerrainController.enableTerrains(terrains, map);
      TerrainController.disableButtonClicks(terrains);
      stage.showAndWait();


//      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//      alert.setTitle("Upgrade Structure");
//      alert.setHeaderText("Do you want to upgrade this Trading Post to a Stronghold or Temple?");
//      alert.setContentText("Cost will be here");
//
//      ButtonType stronghold = new ButtonType("Stronghold");
//      ButtonType temple = new ButtonType("Temple");
//      ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//      alert.getButtonTypes().setAll(stronghold, temple, buttonTypeCancel);
//
//      Optional<ButtonType> result = alert.showAndWait();
//      if (result.get() == stronghold) {
//         Controller.TerrainController.upgradeToStronghold(terrain, current.getFaction().TERRAIN_TILE);
//         space.setStructure("Stronghold");
//      } else if (result.get() == temple) {
//         Controller.TerrainController.upgradeToTemple(terrain, current.getFaction().TERRAIN_TILE);
//         space.setStructure("Temple");
//      } else {
//         // ... user chose CANCEL or closed the dialog
//      }
//
//      Controller.TerrainController.enableTerrains(terrains, map);
//      Controller.TerrainController.disableButtonClicks(terrains);

   }

   public static void upgradeToSanctuary(Player current, Button[][] terrains, Button terrain, Map map, Space space, Button[] actions, CardsAndTiles cardsAndTiles, Religion[] religions,int x, int y) {
      PlayerHandler playerHandler = new PlayerHandler();
      DialogueImageButton discardButton = new DialogueImageButton("dialogueDiscardDoor.png");
      DialogueImageButton sanctuaryButton = new DialogueImageButton("dialogueSanctuary.png");
      VBox pane = DialogueView.getTempleUpgradePromptPane(current, discardButton, sanctuaryButton);

      Stage stage = DialogueView.getStage("Upgrade Temple", pane, new Image("dialogueBackground.png"));

      /**
       * TODO
       * DIKKATTTT
       */
//      BorderPane pane = DialogueView.getTempleUpgradePromptPane(current, "Do you want to upgrade this Temple to a Sanctuary\n at the expense of: ", yesButton, noButton);
//      DropShadow borderGlow = new DropShadow();
//      borderGlow.setColor(Color.ORANGE);
//      Stage stage = DialogueView.getStage("Upgrade Temple", pane, new Image("town_tiles_background.jpg"));

      stage.show();
      discardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            ArrayList<Player> adjacentPlayers = map.adjacentPlayers(space,space.getType());
            int returnCase = playerHandler.buildStructure(current, "Sanctuary", false);
            /**
             * TODO
             * YANDAKİ UŞAKLARA SOR
             */
            if(returnCase > 0){
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               for(int i = 0; i < adjacentPlayers.size(); i++) {
                  alert.setTitle("Offer for " + adjacentPlayers.get(i).getNickName());
                  alert.setHeaderText("Do you want to spend victory points to get power?");
                  alert.setContentText("Cost will be here");
                  Optional<ButtonType> result = alert.showAndWait();
                  if (((Optional) result).get() == ButtonType.OK) {
                     playerHandler.acceptPowerFromAdjacentOpponent(4, adjacentPlayers.get(i));
                  } else {
                     // ... user chose CANCEL or closed the dialog
                  }
               }
               disableActions(actions);
               TerrainController.upgradeToSanctuary(terrain,current.getFaction().TERRAIN_TILE);
               CardsAndTilesController cardsAndTilesController = new CardsAndTilesController();
               for(int i = 0; i < returnCase; i++) {
                  cardsAndTilesController.showFavorTilesTable(cardsAndTiles, current, religions,true);
               }
               space.setStructure("Sanctuary");
               int townScore = map.calculateTownScore(x,y, current.getFaction().TERRAIN_TILE, current.getTownPowerValue());
               if(townScore >= current.getTownPowerValue()){
                  playerHandler.townFound(current);
                  cardsAndTilesController.showTownTilesTable(cardsAndTiles, current,religions,true);
               }
               System.out.println("Town score is *************"+ townScore);
            }else if (returnCase == -1){
               System.out.println("Not enough resources");
            }else
               System.out.println("Max reached");
            stage.close();
         }
      });
      sanctuaryButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            stage.close();
         }
      });
      TerrainController.enableTerrains(terrains, map);
      TerrainController.disableButtonClicks(terrains);

   }

   public static void strongholdAbility(Button[][] terrains, Map map, Button[] actions, Player current) {
      if(current.getFaction() instanceof MorganLeFay){
         TerrainController.disableTerrains(terrains,map);
         for(int i = 0; i < ROW_NUMBER; i++)
            for(int j = 0; j < COLUMN_NUMBER; j++)
               if(map.spaces[i][j] != null && !map.spaces[i][j].isOccupied() && map.spaces[i][j].getType().equals(current.getFaction().TERRAIN_TILE)) {
                  terrains[i][j].setDisable(false);
                  int finalI = i;
                  int finalJ = j;
                  terrains[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                     @Override
                     public void handle(MouseEvent event) {
                        map.spaces[finalI][finalJ].setStructure("Dwelling");
                        TerrainController.buildDwelling(terrains[finalI][finalJ], current.getFaction().TERRAIN_TILE);
                        map.spaces[finalI][finalJ].setOccupied(true);
                        map.spaces[finalI][finalJ].setStructure("Dwelling");
                        TerrainController.disableButtonClicks(terrains);
                        TerrainController.enableTerrains(terrains, map);
                        disableActions(actions);
                        current.setDwellingNum(current.getDwellingNum()+1);

                     }
                  });
               }

      }
      else if(current.getFaction() instanceof AliesterCrowley){

      }

      else if(current.getFaction() instanceof DariusTheGreat){

      }
      else if(current.getFaction() instanceof ErikTheRed){

      }
      else if(current.getFaction() instanceof Gilgamesh){

      }
      else if(current.getFaction() instanceof HusseinTheTeaMaker){

      }
      else if(current.getFaction() instanceof MarieCurie){

      }

   }


   /**TODO
    * TAŞINACAK
    */
   public static void showSpeacialActions(Player[] playerList,Religion[] religions,int currentPlayerId,Map map, Pane mapPane, Button[][] terrains,Button[] actions, CardsAndTiles cardsAndTiles, Religion[] religion) {
      VBox wholeFavor = new VBox();
      HBox firstRow = new HBox();
      HBox secondRow = new HBox();
      HBox special1 = new HBox();
      HBox special2 = new HBox();
      HBox special3 = new HBox();
      HBox special4 = new HBox();
      Player currentPlayer = playerList[currentPlayerId];
      special1.getChildren().add(new SpecialActionView("Spade Action"));
      final int[] choice = {0};
      final Stage dialog = new Stage();

      Scene dialogScene = new Scene(wholeFavor, 1100, 600);
      dialog.setScene(dialogScene);
      dialog.setTitle("Special Actions");
      dialog.setResizable(false);
      dialog.initModality(Modality.APPLICATION_MODAL);
      special1.setOnMouseClicked(new EventHandler<Event>() {
         @Override
         public void handle(Event event) {
            if (currentPlayer.getSpecialActionToken().isSpade) {
               System.out.println("Spade Action");
               choice[0] = 1;
               System.out.println(choice[0]);
               event.consume();
            } else {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Special Action Error");
               alert.setContentText("You have not special spade action");
               alert.setHeaderText("You cannot do this action");
               alert.showAndWait();
            }
         }
      });
      special2.getChildren().add(new SpecialActionView("Cult Action"));
      special2.setOnMouseClicked(new EventHandler<Event>() {
         @Override
         public void handle(Event event) {
            ReligionController religionController = new ReligionController();
            if (currentPlayer.getSpecialActionToken().isCultTack) {
               System.out.println("Cult Action");
               choice[0] = 2;
               System.out.println(choice[0]);
               religionController.showReligion(playerList,2,religions,currentPlayerId, true);
               event.consume();
            } else {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Special Action Error");
               alert.setHeaderText("You cannot do this action");
               alert.setContentText("You have not special cult action");
               alert.showAndWait();

            }
         }
      });

      special3.getChildren().add(new SpecialActionView("Stronghold Ability"));
      special3.setOnMouseClicked(new EventHandler<Event>() {
         @Override
         public void handle(Event event) {
            if (currentPlayer.getSpecialActionToken().isStrongholdAbility) {
               System.out.println("Stronghold Ability");
               choice[0] = 3;
               System.out.println(choice[0]);
               event.consume();
            } else {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Special Action Error");
               alert.setContentText("You have not special stronghold ability");
               alert.setHeaderText("You cannot do this action");
               alert.showAndWait();
            }
         }
      });
      special4.getChildren().add(new SpecialActionView("Faction Ability"));
      special4.setOnMouseClicked(new EventHandler<Event>() {
         @Override
         public void handle(Event event) {
            if (currentPlayer.getSpecialActionToken().isFactionAbility) {
               System.out.println("Faction Ability");
               choice[0] = 4;
               System.out.println(choice[0]);



               event.consume();
            } else {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Special Action Error");
               alert.setContentText("You have not special faction ability");
               alert.setHeaderText("You cannot do this action!!");
               alert.showAndWait();
            }
         }
      });
      Button apply = new Button();
      apply.setText("Apply");
      apply.setFont(Font.font(20));
      System.out.println(choice[0]);
      apply.setOnMouseClicked(new EventHandler<Event>() {
         @Override
         public void handle(Event event) {
            if (choice[0] == 1) {
               currentPlayer.getSpecialActionToken().isSpade = false;
               terraform(currentPlayer, terrains, map, actions, cardsAndTiles, religions);
               event.consume();
            } else if (choice[0] == 2) {
               currentPlayer.getSpecialActionToken().isCultTack = false;
               event.consume();
            } else if (choice[0] == 3) {
               currentPlayer.getSpecialActionToken().isStrongholdAbility = false;
               strongholdAbility(terrains,map,actions,playerList[currentPlayerId]);
               event.consume();
            } else if (choice[0] == 4) {
               System.out.println("geldimm");
               currentPlayer.getSpecialActionToken().isFactionAbility = true;
               factionAbility(playerList[currentPlayerId], map, mapPane, terrains, actions);
               event.consume();
            } else {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Special Action Error");
               alert.setContentText("You have not special faction ability");
               alert.setHeaderText("You cannot do this action!!");
               alert.showAndWait();
            }

            dialog.close();
         }
      });

      firstRow.getChildren().addAll(special1, special2);
      secondRow.getChildren().addAll(special3, special4);
      wholeFavor.getChildren().addAll(firstRow, secondRow);
      wholeFavor.getChildren().add(apply);
      wholeFavor.setPadding(new Insets(100, 0, 0, 50));

      wholeFavor.setMinHeight(800);

      wholeFavor.setMinWidth(1200);

      wholeFavor.setBackground(new Background(new BackgroundImage(new Image("the_background_4.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
              new BackgroundSize(1.0, 1.0, true, true, false, false))));
      dialog.show();
   }

   private static void factionAbility(Player current, Map map, Pane mapPane, Button[][] terrains, Button[] actions) {


      if(current.getFaction() instanceof DariusTheGreat){

      }

      else if(current.getFaction() instanceof  LeonardoDaVinci) {

         if(current.getWorkerNum() >= 2){

            boolean checkBridgability = false;
            for(int i = 0; i < ROW_NUMBER; i++) {
               for (int j = 0; j < COLUMN_NUMBER; j++) {
                  if (map.spaces[i][j].getType().equals(current.getFaction().TERRAIN_TILE) && map.spaces[i][j].getBridgability() && !map.spaces[i][j].getBridgeConnection() && map.spaces[i][j].isOccupied()) {
                     System.out.println("lala");
                     checkBridgability = true;
                  }
               }
            }

            if(checkBridgability) {

               System.out.println("Girdi");
               TerrainController.buildBridge(current.getFaction().TERRAIN_TILE, terrains, map, mapPane, actions);
               System.out.println("Köprü kuruldu");
               current.setWorkerNum(current.getWorkerNum() - 2);

            }
         }


//         boolean checkBridgability = false;
//         for(int i = 0; i < ROW_NUMBER; i++) {
//            for (int j = 0; j < COLUMN_NUMBER; j++) {
//               if (map.spaces[i][j].getType().equals(playerList[roundController.currentPlayerId].getFaction().TERRAIN_TILE) && map.spaces[i][j].getBridgability() && !map.spaces[i][j].getBridgeConnection() && map.spaces[i][j].isOccupied()) {
//                  System.out.println("lala");
//                  checkBridgability = true;
//               }
//            }
//         }
//         if(checkBridgability) {
//            if (playerHandler.usePowerAction(0, currentPlayer)) {
//               System.out.println("Girdi");
//               disableAllTerrains();
//               TerrainController.buildBridge(playerList[roundController.currentPlayerId].getFaction().TERRAIN_TILE, terrains, map, mapPane, actions);
//               System.out.println("Köprü kuruldu");
//            }
//         }

      }

      else if(current.getFaction() instanceof MarieCurie) {
         BorderPane border = new BorderPane();
         BackgroundImage bg = new BackgroundImage(new Image("religion_bg.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
         border.setBackground(new Background(bg));
         GridPane gridPane = new GridPane();
         gridPane.setHgap(10);
         gridPane.setVgap(10);
         Button select = new Button("Select");
         select.setMaxHeight(100);
         select.setMinWidth(100);
         BorderPane border_bottom = new BorderPane();
         border.setBottom(border_bottom);
         border_bottom.setCenter(select);

         ImageView power_middle = new ImageView("arrow.png");
         ImageView gold = new ImageView("gold.png");
         ImageView victory_point = new ImageView("victory_point.png");
         Label label1 = new Label("\n1");
         label1.setTextFill(Color.WHITE);
         Label label2 = new Label("\n1");
         label2.setTextFill(Color.WHITE);
         label1.setFont(new Font("Stencil", 40));
         label2.setFont(new Font("Stencil", 40));
         label1.setOpacity(0.6);
         label2.setOpacity(0.6);
         power_middle.setFitHeight(150);
         power_middle.setFitWidth(150);
         gold.setFitWidth(150);
         gold.setFitHeight(150);
         HBox option;
         for(int i = 0;i < 2; i++) {
            if (i == 0) {
               label2.setText("\n1");
               option = new HBox(victory_point, label1, power_middle, gold, label2);
            } else {
               label1.setText("\n2");
               option = new HBox(gold, label1, power_middle, victory_point, label2);
            }
            GridPane tempPane = new GridPane();
            option.setMaxWidth(tempPane.getWidth() / 3);
            option.setMaxHeight(tempPane.getHeight() / 3);
            tempPane.add(option, 0, 0);
            tempPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
                  DropShadow borderGlow = new DropShadow();
                  borderGlow.setColor(Color.ORANGE);
                  borderGlow.setOffsetX(0f);
                  borderGlow.setOffsetY(0f);
                  borderGlow.setWidth(50);
                  borderGlow.setHeight(50);
                  tempPane.setEffect(borderGlow);
               }
            });
            int finalI = i;
            tempPane.setOnMouseExited(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
                  if (selection != finalI)
                     tempPane.setEffect(null);
               }
            });

            tempPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
                  selection = finalI;
                  for (int i = 0; i < 2; i++) {
                     if (i != selection)
                        gridPane.getChildren().get(i).setEffect(null);
                  }
               }
            });
            gridPane.add(tempPane, i % 2, i / 2);
         }
         border.setCenter(gridPane);
         final Stage dialog = new Stage();
         dialog.initModality(Modality.APPLICATION_MODAL);
         Scene dialogScene = new Scene(border, 1100, 600);
         dialog.setScene(dialogScene);
         dialog.setTitle("Faction Ability");
         dialog.setResizable(false);

         select.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
               int chosen = selection;
               System.out.println("Selection: " + chosen);
               dialog.close();
               if (selection == 0) {
                  System.out.println("2 coin for 1 vp");
               }
            }
         });
         dialog.show();

      }

   }

   /**TODO
    * TAŞINACAK
    * @param
    */
   public static void showUpdateShippingDialogs(Player player , Button[] actions) {

      PlayerHandler playerHandler = new PlayerHandler();
      DialogueImageButton discardButton = new DialogueImageButton("dialogueDiscardDoor.png");
      DialogueImageButton shippingButton = new DialogueImageButton("dialogueShipping.png");
      VBox pane = DialogueView.getUpgradeShippingPromptPane(player, discardButton, shippingButton);

      Stage stage = DialogueView.getStage("Upgrade Shipping", pane, new Image("dialogueBackground.png"));
      stage.show();
      shippingButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            int returnCase = playerHandler.upgradeShippingLevel(player);
            if (returnCase == 1) {
               disableActions(actions);
//               alert.setHeaderText("Upgraded successfully \n");
//               alert.setContentText("");
            } else if (returnCase == -1) {
//               alert.setHeaderText("No enough resources");
//               alert.setContentText("You have no required cost, priest, worker \n" +
//                       "GOLD COST : " + goldCost + "\n" +
//                       "PRIEST COST : " + priestCost + "\n");
            } else if (returnCase == -2){
//               alert.setHeaderText("You have no ability to ship");
//               alert.setContentText("");
            }
            else{
//               alert.setHeaderText("You have max shipping level");
//               alert.setContentText("");
            }
            stage.close();
            Stage errorStage = DialogueView.getStage(DialogueView.getErrorMessage("Errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr!"));
            errorStage.show();
            DialogueView.delayErrorMessage(errorStage);
         }
      });

      discardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            stage.close();
         }
      });
   }


   /**TODO
    * TAŞINACAK
    *
    */
   public static void showUpdateSpadeDialogs(Player player, Button[] actions) {
      PlayerHandler playerHandler = new PlayerHandler();

      DialogueImageButton discardButton = new DialogueImageButton("dialogueDiscardDoor.png");
      DialogueImageButton shippingButton = new DialogueImageButton("dialogueSpade.png");
      VBox pane = DialogueView.getUpgradeSpadePromptPane(player, discardButton, shippingButton);

      Stage stage = DialogueView.getStage("Upgrade Spade", pane, new Image("dialogueBackground.png"));
      stage.show();
      shippingButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            int returnCase = playerHandler.upgradeSpadeLevel(player);
            if (returnCase == 1){
               disableActions(actions);
//               alert.setHeaderText("Upgraded successfully \n");
//               alert.setContentText("");
            }else if (returnCase == -1) {
//               alert.setHeaderText("No enough resources");
//               alert.setContentText("You have no required cost, priest, worker \n" +
//                       "GOLD COST : " + goldCost + "\n" +
//                       "PRIEST COST : " + priestCost + "\n" +
//                       "WORKER COST : " + workerCost);
            }else{
//               alert.setHeaderText("You have max spade level");
//               alert.setContentText("");
            }
            stage.close();
         }
      });
      discardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            stage.close();
         }
      });


   }

   public static void disableActions(Button[] actions){
      for(int i = 0; i < actions.length; i++)
         actions[i].setDisable(true);
   }

   public static void enableActions(Button[] actions){
      for(int i = 0; i < actions.length; i++)
         actions[i].setDisable(false);
   }

}