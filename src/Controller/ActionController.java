package Controller;

import Model.GameHandler;
import Model.Map;
import Model.Player;
import Model.Space;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActionController {

   final static int ROW_NUMBER = 9;
   final static int COLUMN_NUMBER = 13;
   private static int selection = -1;

   public static void terraform(Player[] playerArr, int curPlayerId ,Button[][] terrains,Map map) {

      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if (terrains[i][j] != null)
               if (!map.spaces[i][j].isOccupied() || !map.spaces[i][j].getType().equals(playerArr[curPlayerId].getFaction().TERRAIN_TILE))
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
                              //BUNA DİKKAT
                              // terraformAlertBox(gameHandler, terrains, terrains[map.getRow(finalAdj[finalK])][map.getColumn(finalAdj[finalK])], map, map.spaces[map.getRow(finalAdj[finalK])][map.getColumn(finalAdj[finalK])]);
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
   public static void terraformAlertBox(GameHandler gameHandler, Button[][] terrains, Button terrain, Map map, Space space) {

      List<String> choices = new ArrayList<>();
      choices.add("Wasteland");
      choices.add("Forest");
      choices.add("Lakes");
      choices.add("Desert");
      choices.add("Mountains");
      choices.add("Swamp");
      choices.add("Plains");
      choices.remove(space.getType());

      Label prompt = new Label("Please choose the terrain to be transformed into.");
      Label promptChoice = new Label("Terrain type: ");
      ChoiceBox choiceBox = new ChoiceBox();
      choiceBox.getItems().addAll(choices);
      choiceBox.setValue(gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE);
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

            //gameHandler.terraform(space, selectedChoice);
            TerrainController.terraform(terrain, selectedChoice);
            space.setType(selectedChoice);

            terraformStage.close();
            if (selectedChoice.equals(gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE)) {
               Button yesButton = new Button("Yes");
               Button noButton = new Button("No");
               BorderPane pane = DialogueController.getDwellingUpgradePromptPane(gameHandler, "Do you want to build a dwelling into that terrain? The cost will be: ", yesButton, noButton);

               Stage dwellingChoiceStage = DialogueController.getStage("Do you want to build dwelling?", pane, new Image("favor_tiles_background.jpg"));
               dwellingChoiceStage.show();
               yesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                  @Override
                  public void handle(MouseEvent event) {

                     //gameHandler.buildDwelling(space);
                     TerrainController.buildDwelling(terrain, selectedChoice);
                     space.setOccupied(true);
                     space.setStructure("Dwelling");

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

   public static void upgradeStructure(Player[] playerArr, int currentPlayerId,Button[][] terrains, Map map) {
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
                           upgradeToTradingPost(playerArr,currentPlayerId, terrains, terrains[finalI][finalJ], map, map.spaces[finalI][finalJ]);
                        else if (map.spaces[finalI][finalJ].getStructure().getBuilding().equals("Trading Post"))
                           upgradeToStrongholdOrTemple(playerArr,currentPlayerId, terrains, terrains[finalI][finalJ], map, map.spaces[finalI][finalJ]);
                        else if (map.spaces[finalI][finalJ].getStructure().getBuilding().equals("Temple"))
                           upgradeToSanctuary(playerArr,currentPlayerId, terrains, terrains[finalI][finalJ], map, map.spaces[finalI][finalJ]);
                     }
                  });
               }
         }
      }
   }

   public static void upgradeToTradingPost(Player[] playerArr, int currentPlayerId, Button[][] terrains, Button terrain, Map map, Space space) {
      Button yesButton = new Button("Yes");
      Button noButton = new Button("No");
      /**TODO
       * BUNLARA DA BAKILMASI LAZIM
       */
      //BorderPane pane = DialogueController.getDwellingUpgradePromptPane(gameHandler, "Do you want to upgrade this Dwelling to a Trading Post?", yesButton, noButton);
      //Stage stage = DialogueController.getStage("Upgrade dwelling", pane, new Image("favor_tiles_background.jpg"));
      //stage.show();
      yesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            TerrainController.upgradeToTradingPost(terrain, playerArr[currentPlayerId].getFaction().TERRAIN_TILE);
            space.setStructure("Trading Post");
           // stage.close();
         }
      });
      noButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            //stage.close();
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
//         TerrainController.upgradeToTradingPost(terrain, gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE);
//         space.setStructure("Trading Post");
//      } else {
//         // ... user chose CANCEL or closed the dialog
//      }
//      TerrainController.enableTerrains(terrains, map);
//      TerrainController.disableButtonClicks(terrains);
   }

   public static void upgradeToStrongholdOrTemple(Player[] playerArr,int curPlayerId,Button[][] terrains, Button terrain, Map map, Space space) {
      Button yesButton = new Button("Yes");
      Button noButton = new Button("No");
      RadioButton templeButton = new RadioButton("Temple");
      RadioButton strongholdButton = new RadioButton("Stronghold");
      //BorderPane pane = DialogueController.getTradingPostUpgradePromptPane(gameHandler, "Do you want to upgrade this Trading Post to a Temple\n at the expense of: ", "Do you want to upgrade this Trading Post to a Stronghold\n at the expense of: ", yesButton, noButton, templeButton, strongholdButton);
      /**TODO
       * DIKKATTTTTTTT
       */
      //Stage stage = DialogueController.getStage("Upgrade Trading Post", pane, new Image("favor_tiles_background.jpg"));
      //stage.show();
      yesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            if( templeButton.isSelected() && !strongholdButton.isSelected())
            {
               TerrainController.upgradeToTemple(terrain, playerArr[curPlayerId].getFaction().TERRAIN_TILE);
               space.setStructure("Temple");
            }
            else if(!templeButton.isSelected() && strongholdButton.isSelected())
            {
               TerrainController.upgradeToStronghold(terrain, playerArr[curPlayerId].getFaction().TERRAIN_TILE);
               space.setStructure("Stronghold");
            }
            //stage.close();
         }
      });
      noButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            //stage.close();
         }
      });
      TerrainController.enableTerrains(terrains, map);
      TerrainController.disableButtonClicks(terrains);



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
//         TerrainController.upgradeToStronghold(terrain, gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE);
//         space.setStructure("Stronghold");
//      } else if (result.get() == temple) {
//         TerrainController.upgradeToTemple(terrain, gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE);
//         space.setStructure("Temple");
//      } else {
//         // ... user chose CANCEL or closed the dialog
//      }
//
//      TerrainController.enableTerrains(terrains, map);
//      TerrainController.disableButtonClicks(terrains);

   }

   public static void upgradeToSanctuary(Player[] playerArr, int curPlayerId, Button[][] terrains, Button terrain, Map map, Space space) {
      Button yesButton = new Button("Yes");
      Button noButton = new Button("No");
      /**
       * TODO
       * DIKKATTTT
       */
      //BorderPane pane = DialogueController.getTempleUpgradePromptPane(gameHandler, "Do you want to upgrade this Temple to a Sanctuary\n at the expense of: ", yesButton, noButton);

      //Stage stage = DialogueController.getStage("Upgrade Temple", pane, new Image("favor_tiles_background.jpg"));
      //stage.show();
      yesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            TerrainController.upgradeToSanctuary(terrain,playerArr[curPlayerId].getFaction().TERRAIN_TILE);
            space.setStructure("Sanctuary");
            //stage.close();
         }
      });
      noButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            //stage.close();
         }
      });
      TerrainController.enableTerrains(terrains, map);
      TerrainController.disableButtonClicks(terrains);




//      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//      alert.setTitle("Upgrade Structure");
//      alert.setHeaderText("Do you want to upgrade this Temple to a Sanctuary?");
//      alert.setContentText("Cost will be here");
//
//      Optional<ButtonType> result = alert.showAndWait();
//      if (((Optional) result).get() == ButtonType.OK) {
//         TerrainController.upgradeToSanctuary(terrain, gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE);
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
   public void upgradeSpace(int curPlayerId, Player[] playerArr){

   }
   //TODO
   public void skipTurn(int curPlayerId, Player[] playerArr){

   }



   public static int getSelection() {
      return selection;
   }

   private static void setSelection(int selection) {
      ActionController.selection = selection;
   }

   /**TODO
    * TAŞINACAK HERHALDE BU DA
    * @param
    */
   public static void showPowerActions() {

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

      for (int i = 0; i < 6; i++) {
         GridPane tempPane = new GridPane();
         ImageView power_left = new ImageView("chris_track.png");
         ImageView power_right = new ImageView("juda_track.png");
         ImageView power_middle = new ImageView("purple_arrow.png");
         power_middle.setFitWidth(tempPane.getWidth() / 3);
         power_middle.setFitHeight(tempPane.getHeight() / 3);
         power_left.setFitWidth(tempPane.getWidth() / 3);
         power_left.setFitHeight(tempPane.getHeight() / 3);
         power_right.setFitWidth(tempPane.getWidth() / 3);
         power_right.setFitHeight(tempPane.getHeight() / 3);
         tempPane.add(power_left, 0, 0);
         tempPane.add(power_middle, 1, 0);
         tempPane.add(power_right, 2, 0);
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
               ;
            }
         });

         tempPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               setSelection(finalI);
               for (int i = 0; i < 6; i++) {
                  if (i != getSelection())
                     gridPane.getChildren().get(i).setEffect(null);
               }
            }
         });
         gridPane.add(tempPane, i % 3, i / 3);
      }
      select.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {

            int chosen = getSelection();
            System.out.println("Selected" + chosen);
         }
      });
      border.setCenter(gridPane);
      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      Scene dialogScene = new Scene(border, 1100, 600);
      dialog.setScene(dialogScene);
      dialog.setTitle("Power Action");
      dialog.setResizable(false);
      //update(gridPane,status);
      //Find religion to add as size (y coordinate)%(1/4 of anchor pane's size) to replace choice box.
      dialog.show();

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
   public static void showUpdateShippingDialogs(Player[] playerList, int currentPlayerId ) {

      Player player = playerList[currentPlayerId];
      int priestCost = player.getFaction().SHIPPING_PRIEST_COST;
      int goldCost = player.getFaction().SHIPPING_GOLD_COST;
      int playerPriest = player.getPriestNum();
      int playerGold = player.getGoldNum();
      if (priestCost <= playerPriest && goldCost <= playerGold && player.getShipLevel() < 3) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Upgrade Shipping");
         alert.setHeaderText("GOLD COST : " + goldCost + "\n" +
                 "PRIEST COST : " + priestCost);
         alert.setContentText("Do you wan to update your shipping level \n" +
                 "Current Level : " + player.getShipLevel() + "\n" +
                 "New Level : " + (player.getShipLevel() + 1));
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK) {
            player.setGoldNum(playerGold - goldCost);
            player.setPriestNum(playerPriest - priestCost);
            player.setShipLevel(player.getShipLevel() + 1);
            player.setVictoryPointNum(player.getVictoryPointNum() + player.getFaction().SHIPPING_UPGRADE_VICTORY_POINTS[player.getShipLevel()]);
         } else {
            // ... user chose CANCEL or closed the dialog
         }
      } else {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Update Shipping level");
         if (player.getSpadeLevel() == 3) {
            alert.setContentText("You have max shipping level");
         } else {
            alert.setContentText("You have no required cost or priest\n" +
                    "GOLD COST : " + goldCost + "\n" +
                    "PRIEST COST : " + priestCost);
         }

         alert.setHeaderText("You cannot do this action!!");
         alert.showAndWait();
      }
   }

   /**TODO
    * TAŞINACAK
    *
    */
   public static void showUpdateSpadeDialogs(Player[] playerList, int currentPlayerId) {

      Player player = playerList[currentPlayerId];
      int priestCost = player.getFaction().SPADE_PRIEST_COST;
      int goldCost = player.getFaction().SPADE_GOLD_COST;
      int workerCost = player.getFaction().SPADE_WORKER_COST;
      int playerPriest = player.getPriestNum();
      int playerGold = player.getGoldNum();
      int playerWorker = player.getWorkerNum();
      if (priestCost <= playerPriest && goldCost <= playerGold && workerCost <= playerWorker && player.getSpadeLevel() < 3) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Upgrade Spade");
         alert.setHeaderText("GOLD COST : " + goldCost + "\n" +
                 "PRIEST COST : " + priestCost + "\n" +
                 "WORKER COST : " + workerCost);
         alert.setContentText("Do you wan to update your spade level \n" +
                 "Current Level : " + player.getSpadeLevel() + "\n" +
                 "New Level : " + (player.getSpadeLevel() + 1));
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK) {
            player.setGoldNum(playerGold - goldCost);
            player.setPriestNum(playerPriest - priestCost);
            player.setSpadeLevel(player.getSpadeLevel() + 1);
            player.setVictoryPointNum(player.getVictoryPointNum() + (player.getSpadeLevel() == 2 ? player.getFaction().SPADE_FIRST_UPGRADE_VICTORY : player.getFaction().SPADE_SECOND_UPGRADE_VICTORY));
         } else {
            // ... user chose CANCEL or closed the dialog
         }
      } else {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Update Spade level");
         if (player.getSpadeLevel() == 3) {
            alert.setContentText("You have max spade level");
         } else {
            alert.setContentText("You have no required cost, priest, worker \n" +
                    "GOLD COST : " + goldCost + "\n" +
                    "PRIEST COST : " + priestCost + "\n" +
                    "WORKER COST : " + workerCost);
         }

         alert.setHeaderText("You cannot do this action!!");
         alert.showAndWait();
      }
   }

   /**TODO
    * RAPORDA YOK
    * @param gameHandler
    * @param terrains
    * @param map
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


}
