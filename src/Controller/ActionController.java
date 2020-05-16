package Controller;

import Model.*;
import Model.Map;
import Model.StructureSubclasses.Stronghold;
import View.ActionsViews.SpecialActionView;
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

   public static void terraform(Player[] playerArr, int curPlayerId ,Button[][] terrains,Map map, Button[] actions) {

      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if (terrains[i][j] != null)
               if ((!map.spaces[i][j].isOccupied() || !map.spaces[i][j].getType().equals(playerArr[curPlayerId].getFaction().TERRAIN_TILE)))
                  terrains[i][j].setDisable(true);
         }
      }
      Space[] adj;
      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if (terrains[i][j] != null && map.spaces[i][j] != null)
               if (map.spaces[i][j].getType().equals(playerArr[curPlayerId].getFaction().TERRAIN_TILE) && map.spaces[i][j].isOccupied()) {
                  adj = map.adjacencyList(map.spaces[i][j]);
                  for (int k = 0; k < adj.length; k++) {
                     if (adj[k] != null && terrains[map.getRow(adj[k])][map.getColumn(adj[k])] != null && !map.spaces[map.getRow(adj[k])][map.getColumn(adj[k])].getType().equals("River") && !map.spaces[map.getRow(adj[k])][map.getColumn(adj[k])].isOccupied() && terrains[map.getRow(adj[k])][map.getColumn(adj[k])].isDisable()) {
                        terrains[map.getRow(adj[k])][map.getColumn(adj[k])].setDisable(false);
                        Space[] finalAdj = adj;
                        int finalK = k;
                        terrains[map.getRow(adj[k])][map.getColumn(adj[k])].setOnMouseClicked(new EventHandler<MouseEvent>() {
                           @Override
                           public void handle(MouseEvent event) {
                              terraformAction(playerArr, curPlayerId, terrains, terrains[map.getRow(finalAdj[finalK])][map.getColumn(finalAdj[finalK])], map, map.spaces[map.getRow(finalAdj[finalK])][map.getColumn(finalAdj[finalK])], actions);
                           }
                        });
                     }
                  }
               }
         }
         for (int k = 0; k < ROW_NUMBER; k++) {
            for (int l = 0; l < COLUMN_NUMBER; l++) {
               if (terrains[k][l] != null && map.spaces[k][l] != null) {
                  if (map.spaces[k][l].getBridgeConnection() && map.spaces[k][l].getBridgeType().equals(playerArr[curPlayerId].getFaction().TERRAIN_TILE)) {
                     terrains[k][l].setDisable(false);
                     int finalK = k;
                     int finalL = l;
                     terrains[k][l].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                           terraformAction(playerArr, curPlayerId, terrains, terrains[finalK][finalL], map, map.spaces[finalK][finalL], actions);
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
    *
    *
    *
    */
   public static void terraformAction(Player[] playerArr, int curPlayerId, Button[][] terrains, Button terrain, Map map, Space space, Button[] actions) {

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
      choiceBox.setValue(playerArr[curPlayerId].getFaction().TERRAIN_TILE);
      BorderPane pane = new BorderPane();
      HBox choiceBlock = new HBox();
      Button transformButton = new Button("Transform");

      choiceBlock.getChildren().addAll(promptChoice, choiceBox, transformButton);
      VBox whole = new VBox();
      whole.getChildren().addAll(prompt, choiceBlock);
      pane.setCenter(whole);
      pane.setPadding(new Insets(50, 50, 0, 0));
      final Stage terraformStage = DialogueController.getStage("Terraform Action", pane, new Image("favor_tiles_background.jpg"));
      transformButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            String selectedChoice = (String) choiceBox.getValue();
            boolean flag = true;
            if (playerHandler.terraform(playerArr[curPlayerId],space.getType())){
               disableActions(actions);
               TerrainController.terraform(terrain, selectedChoice);
               space.setType(selectedChoice);
            }else{
               flag = false;
               System.out.println("No enough resources");
            }
            terraformStage.close();
            if (selectedChoice.equals(playerArr[curPlayerId].getFaction().TERRAIN_TILE) && flag) {
               Button yesButton = new Button("Yes");
               Button noButton = new Button("No");
               BorderPane pane = DialogueController.getDwellingUpgradePromptPane(playerArr, curPlayerId, "Do you want to build a dwelling into that terrain? The cost will be: ", yesButton, noButton);
               Stage dwellingChoiceStage = DialogueController.getStage("Do you want to build dwelling?", pane, new Image("favor_tiles_background.jpg"));
               dwellingChoiceStage.show();
               yesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                  @Override
                  public void handle(MouseEvent event) {
                     int returnCase =  playerHandler.buildStructure(playerArr[curPlayerId],"Dwelling",false);
                     if( returnCase == 1) {
                        TerrainController.buildDwelling(terrain, selectedChoice);
                        space.setOccupied(true);
                        space.setStructure("Dwelling");
                     }else if( returnCase == -1){
                        System.out.println("Not enough resources");
                     }else
                        System.out.println("Max reached");
                     dwellingChoiceStage.close();
                  }

               });
               noButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
//            TerrainController.terraform(terrain, result.get());
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
//                    TerrainController.buildDwelling(terrain, result.get());
//                    space.setOccupied(true);
//                    space.setStructure("Dwelling");
//
//                } else {
//                    // ... user chose CANCEL or closed the dialog
//                }
//            }
//        }
//        TerrainController.enableTerrains(terrains, map);
//        TerrainController.disableButtonClicks(terrains);
   }

   public  static void upgradeStructure(Player[] playerArr, int currentPlayerId,Button[][] terrains, Map map, Button[] actions) {
      TerrainController.disableTerrains(terrains, map);

      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if (terrains[i][j] != null)
               if (map.spaces[i][j].isOccupied() && map.spaces[i][j].getType().equals(playerArr[currentPlayerId].getFaction().TERRAIN_TILE) && !map.spaces[i][j].getStructure().getBuilding().equals("Stronghold") && !map.spaces[i][j].getStructure().getBuilding().equals("Sanctuary")) {
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
                           upgradeToTradingPost(playerArr,currentPlayerId, terrains, terrains[finalI][finalJ], map, map.spaces[finalI][finalJ],actions);
                        else if (map.spaces[finalI][finalJ].getStructure().getBuilding().equals("Trading Post"))
                           upgradeToStrongholdOrTemple(playerArr,currentPlayerId, terrains, terrains[finalI][finalJ], map, map.spaces[finalI][finalJ], actions);
                        else if (map.spaces[finalI][finalJ].getStructure().getBuilding().equals("Temple"))
                           upgradeToSanctuary(playerArr,currentPlayerId, terrains, terrains[finalI][finalJ], map, map.spaces[finalI][finalJ], actions);
                     }
                  });
               }
         }
      }
   }

   public static void upgradeToTradingPost(Player[] playerArr, int currentPlayerId, Button[][] terrains, Button terrain, Map map, Space space, Button[] actions) {
      PlayerHandler playerHandler = new PlayerHandler();
      Button yesButton = new Button("Yes");
      Button noButton = new Button("No");
      BorderPane pane = DialogueController.getDwellingUpgradePromptPane(playerArr,currentPlayerId, "Do you want to upgrade this Dwelling to a Trading Post?", yesButton, noButton);
      Stage stage = DialogueController.getStage("Upgrade dwelling", pane, new Image("favor_tiles_background.jpg"));
      stage.show();
      yesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            ArrayList<Player> adjacentPlayers = map.adjacentPlayers(space,space.getType());
            System.out.println(adjacentPlayers.size());
            int returnCase = playerHandler.buildStructure(playerArr[currentPlayerId], "TradingPost", adjacentPlayers.size() != 0);
            /**
             * TODO
             * YANDAKİ UŞAKLARA SOR
             */
            if(returnCase == 1){
               disableActions(actions);
               TerrainController.upgradeToTradingPost(terrain, playerArr[currentPlayerId].getFaction().TERRAIN_TILE);
               space.setStructure("Trading Post");
               stage.close();
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
      noButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            stage.close();
         }
      });
      TerrainController.enableTerrains(terrains, map);
      TerrainController.disableButtonClicks(terrains);

//      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//      alert.setTitle("Upgrade Structure");
//      alert.setHeaderText("Do you want to upgrade this Dwelling to a Trading Post?");
//      alert.setContentText("Cost will be here");
//
//      Optional<ButtonType> result = alert.showAndWait();
//      if (((Optional) result).get() == ButtonType.OK) {
//         TerrainController.upgradeToTradingPost(terrain, playerArr[currentPlayerId].getFaction().TERRAIN_TILE);
//         space.setStructure("Trading Post");
//      } else {
//         // ... user chose CANCEL or closed the dialog
//      }
//      TerrainController.enableTerrains(terrains, map);
//      TerrainController.disableButtonClicks(terrains);
   }

   public static void upgradeToStrongholdOrTemple(Player[] playerArr,int curPlayerId,Button[][] terrains, Button terrain, Map map, Space space, Button[] actions) {
      PlayerHandler playerHandler = new PlayerHandler();
      Button yesButton = new Button("Yes");
      Button noButton = new Button("No");
      RadioButton templeButton = new RadioButton("Temple");
      RadioButton strongholdButton = new RadioButton("Stronghold");
      BorderPane pane = DialogueController.getTradingPostUpgradePromptPane(playerArr,curPlayerId, "Do you want to upgrade this Trading Post to a Temple\n at the expense of: ", "Do you want to upgrade this Trading Post to a Stronghold\n at the expense of: ", yesButton, noButton, templeButton, strongholdButton);
      Stage stage = DialogueController.getStage("Upgrade Trading Post", pane, new Image("favor_tiles_background.jpg"));

      yesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            if( templeButton.isSelected() && !strongholdButton.isSelected())
            {
               int returnCase = playerHandler.buildStructure(playerArr[curPlayerId], "Temple", false);
               /**
                * TODO
                * YANDAKİ UŞAKLARA SOR
                */
               if(returnCase == 1){
                  canChooseFavorTile = true;
                  disableActions(actions);
                  TerrainController.upgradeToTemple(terrain, playerArr[curPlayerId].getFaction().TERRAIN_TILE);
                  space.setStructure("Temple");
                  stage.close();
                  actiondone = true;
               }else if (returnCase == -1){
                  System.out.println("Not enough resources");
                  stage.close();
                  actiondone = true;
               }else
                  System.out.println("Max reached");
               stage.close();

            }
            else if(!templeButton.isSelected() && strongholdButton.isSelected())
            {
               int returnCase = playerHandler.buildStructure(playerArr[curPlayerId], "Stronghold", false);
               /**
                * TODO
                * YANDAKİ UŞAKLARA SOR
                */
               if(returnCase == 1){
                  disableActions(actions);
                  TerrainController.upgradeToStronghold(terrain, playerArr[curPlayerId].getFaction().TERRAIN_TILE);
                  space.setStructure("Stronghold");
               }else if (returnCase == -1){
                  System.out.println("Not enough resources");
               }else
                  System.out.println("Max reached");
               stage.close();
            }
            stage.close();
            actiondone = true;
         }
      });
      noButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            stage.close();
            actiondone = true;
         }
      });
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
//         TerrainController.upgradeToStronghold(terrain, playerArr[curPlayerId].getFaction().TERRAIN_TILE);
//         space.setStructure("Stronghold");
//      } else if (result.get() == temple) {
//         TerrainController.upgradeToTemple(terrain, playerArr[curPlayerId].getFaction().TERRAIN_TILE);
//         space.setStructure("Temple");
//      } else {
//         // ... user chose CANCEL or closed the dialog
//      }
//
//      TerrainController.enableTerrains(terrains, map);
//      TerrainController.disableButtonClicks(terrains);

   }

   public static void upgradeToSanctuary(Player[] playerArr, int curPlayerId, Button[][] terrains, Button terrain, Map map, Space space, Button[] actions) {
      PlayerHandler playerHandler = new PlayerHandler();
      Button yesButton = new Button("Yes");
      Button noButton = new Button("No");

      /**
       * TODO
       * DIKKATTTT
       */
      BorderPane pane = DialogueController.getTempleUpgradePromptPane(playerArr,curPlayerId, "Do you want to upgrade this Temple to a Sanctuary\n at the expense of: ", yesButton, noButton);
      Stage stage = DialogueController.getStage("Upgrade Temple", pane, new Image("favor_tiles_background.jpg"));
      stage.show();
      yesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {

            int returnCase = playerHandler.buildStructure(playerArr[curPlayerId], "Stronghold", false);
            /**
             * TODO
             * YANDAKİ UŞAKLARA SOR
             */
            if(returnCase == 1){
               disableActions(actions);
               TerrainController.upgradeToSanctuary(terrain,playerArr[curPlayerId].getFaction().TERRAIN_TILE);
               space.setStructure("Sanctuary");
            }else if (returnCase == -1){
               System.out.println("Not enough resources");
            }else
               System.out.println("Max reached");
            stage.close();
         }
      });
      noButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            stage.close();
         }
      });
      TerrainController.enableTerrains(terrains, map);
      TerrainController.disableButtonClicks(terrains);
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//      alert.setTitle("Upgrade Structure");
//      alert.setHeaderText("Do you want to upgrade this Temple to a Sanctuary?");
//      alert.setContentText("Cost will be here");
//
//      Optional<ButtonType> result = alert.showAndWait();
//      if (((Optional) result).get() == ButtonType.OK) {
//         TerrainController.upgradeToSanctuary(terrain, playerArr[curPlayerId].getFaction().TERRAIN_TILE);
//         space.setStructure("Sanctuary");
//      } else {
//         // ... user chose CANCEL or closed the dialog
//      }
//      TerrainController.enableTerrains(terrains, map);
//      TerrainController.disableButtonClicks(terrains);
   }

   //TODO
   public void  upgradeShipping(int curPlayerId,Player[] playerArr){

   }
   //TODO
   public void sendPriest(Player[] playerArr, int curPlayerId){

   }
   //TODO
   public void usePowerAction(int curPlayerId, Player[] playerArr){

   }
   //TODO
   public void useSpecialAction(int curPlayerId, Player[] playerArr){

   }
   //TODO
   public void exchangeResources(int curPlayerId, Player[] playerArr){

   }
   //TODO
   public static void upgradeSpadeLevel(int curPlayerId, Player[] playerArr){

   }
   //TODO
   public void skipTurn(int curPlayerId, Player[] playerArr){

   }






   /**TODO
    * TAŞINACAK
    * @param gameController
    */
   public static void showSpeacialActions(GameController gameController) {
      VBox wholeFavor = new VBox();
      HBox firstRow = new HBox();
      HBox secondRow = new HBox();
      HBox special1 = new HBox();
      HBox special2 = new HBox();
      HBox special3 = new HBox();
      HBox special4 = new HBox();

      Player[] players = gameController.getPlayerList();
      int playerId = gameController.roundController.getCurrentPlayerId();
      Player player = players[playerId];
      special1.getChildren().add(new SpecialActionView("Spade Action"));
      final int[] choice = {0};
      special1.setOnMouseClicked(new EventHandler<Event>() {
         @Override
         public void handle(Event event) {
            if (player.getSpecialActionToken().isSpade) {
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

            if (player.getSpecialActionToken().isCultTack) {
               System.out.println("Cult Action");
               choice[0] = 2;
               System.out.println(choice[0]);

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
            if (player.getSpecialActionToken().isStrongholdAbility) {
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
            if (player.getSpecialActionToken().isFactionAbility) {
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
      apply.setOnMouseClicked(new EventHandler<Event>() {
         @Override
         public void handle(Event event) {
            if (choice[0] == 1) {
               player.getSpecialActionToken().isSpade = false;
               player.setFreeSpade(player.getFreeSpade() + 1);
               event.consume();
            } else if (choice[0] == 2) {
               player.getSpecialActionToken().isCultTack = false;
               event.consume();
            } else if (choice[0] == 3) {
               player.getSpecialActionToken().isStrongholdAbility = false;
               event.consume();
            } else if (choice[0] == 4) {
               player.getSpecialActionToken().isFactionAbility = false;
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
      firstRow.getChildren().addAll(special1, special2);
      secondRow.getChildren().addAll(special3, special4);
      wholeFavor.getChildren().addAll(firstRow, secondRow);
      wholeFavor.getChildren().add(apply);
      wholeFavor.setPadding(new Insets(100, 0, 0, 50));

      wholeFavor.setMinHeight(800);
      wholeFavor.setMinWidth(1200);
      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      Scene dialogScene = new Scene(wholeFavor, 1100, 600);
      dialog.setScene(dialogScene);
      dialog.setTitle("Special Actions");
      dialog.setResizable(false);
      wholeFavor.setBackground(new Background(new BackgroundImage(new Image("favor_tiles_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
              BackgroundSize.DEFAULT)));
      dialog.show();
   }

   /**TODO
    * TAŞINACAK
    * @param
    */
   public static void showUpdateShippingDialogs(Player[] playerList, int currentPlayerId , Button[] actions) {
      PlayerHandler playerHandler = new PlayerHandler();
      Player player = playerList[currentPlayerId];
      int priestCost = player.getFaction().SHIPPING_PRIEST_COST;
      int goldCost = player.getFaction().SHIPPING_GOLD_COST;
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Upgrade Shipping");
         alert.setHeaderText("GOLD COST : " + goldCost + "\n" +
                 "PRIEST COST : " + priestCost);
         alert.setContentText("Do you wan to update your shipping level \n" +
                 "Current Level : " + player.getShipLevel() + "\n" +
                 "New Level : " + (player.getShipLevel() + 1));
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK) {
            alert.setTitle("Upgrading Shipping Level");
            int returnCase = playerHandler.upgradeShippingLevel(player);
            if (returnCase == 1) {
               disableActions(actions);
               alert.setHeaderText("Upgraded successfully \n");
               alert.setContentText("");
            } else if (returnCase == -1) {
               alert.setHeaderText("No enough resources");
               alert.setContentText("You have no required cost, priest, worker \n" +
                       "GOLD COST : " + goldCost + "\n" +
                       "PRIEST COST : " + priestCost + "\n");
            } else if (returnCase == -2){
               alert.setHeaderText("You have no ability to ship");
               alert.setContentText("");
            }
            else{
               alert.setHeaderText("You have max shipping level");
               alert.setContentText("");
            }
            alert.showAndWait();
         } else {
            // ... user chose CANCEL or closed the dialog
         }
   }


   /**TODO
    * TAŞINACAK
    *
    */
   public static void showUpdateSpadeDialogs(Player[] playerList, int currentPlayerId, Button[] actions) {
      PlayerHandler playerHandler = new PlayerHandler();
      Player player = playerList[currentPlayerId];
      int priestCost = player.getFaction().SPADE_PRIEST_COST;
      int goldCost = player.getFaction().SPADE_GOLD_COST;
      int workerCost = player.getFaction().SPADE_WORKER_COST;
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Upgrade Spade");
         alert.setHeaderText("GOLD COST : " + goldCost + "\n" +
                 "PRIEST COST : " + priestCost + "\n" +
                 "WORKER COST : " + workerCost);
         alert.setContentText("Do you want to update your spade level \n" +
                 "Current Level : " + player.getSpadeLevel() + "\n"
                 );
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK) {
            alert.setTitle("Update Spade level");
            int returnCase = playerHandler.upgradeSpadeLevel(player);
            if (returnCase == 1){
               disableActions(actions);
               alert.setHeaderText("Upgraded successfully \n");
               alert.setContentText("");
            }else if (returnCase == -1) {
               alert.setHeaderText("No enough resources");
               alert.setContentText("You have no required cost, priest, worker \n" +
                       "GOLD COST : " + goldCost + "\n" +
                       "PRIEST COST : " + priestCost + "\n" +
                       "WORKER COST : " + workerCost);
            }else{
               alert.setHeaderText("You have max spade level");
               alert.setContentText("");
            }
            alert.showAndWait();
         } else {
            // ... user chose CANCEL or closed the dialog
         }



   }

   /**TODO
    * RAPORDA YOK
    */
   public static void buildBridge(GameHandler gameHandler, Button[][] terrains, Map map){

      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if (terrains[i][j] != null)
                  terrains[i][j].setDisable(true);
         }
      }
      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if (terrains[i][j] != null && map.spaces[i][j] != null) {
               if (j < 12 && j > 1 && i < 8 && map.spaces[i + 1][j].getType().equals("River") && map.spaces[i + 1][j - 1].getType().equals("River")) {
                  if (!map.spaces[i][j].getType().equals("River")) {
                     //terrains[i][j].setDisable(false);
                  }
               }
               else if (j < 12 && j > 1 && i < 8 &&  map.spaces[i+1][j].getType().equals("River") &&  map.spaces[i][j-1].getType().equals("River") ) {
                  if (!map.spaces[i][j].getType().equals("River")) {
                     //terrains[i][j].setDisable(false);
                  }
               }

               else if (j < 12  && i < 8  &&  map.spaces[i][j+1].getType().equals("River") && map.spaces[i+1][j+1].getType().equals("River")) {
                  if (!map.spaces[i][j].getType().equals("River")) {
                     //terrains[i][j].setDisable(false);
                  }
               }
            }
         }
      }



   }

   public static void disableActions(Button[] actions){
      for(int i = 0; i < actions.length; i++)
         actions[i].setDisable(true);
   }


}
