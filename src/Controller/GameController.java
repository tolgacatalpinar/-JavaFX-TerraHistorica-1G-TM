package Controller;


import Controller.ActionsControllers.SpecialActionController;
import Controller.ActionsControllers.TerraformController;
import Controller.ActionsControllers.UpdateShippingController;
import Controller.ActionsControllers.UpdateSpadeController;
import Controller.CardsAndTilesControllers.*;
import Model.*;
//import Model.River;
//import Model.TerrainSubclasses.*;
import View.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {
   final int ROW_NUMBER = 9;
   final int COLUMN_NUMBER = 13;
   @FXML
   Pane mapPane;
   @FXML
   BorderPane borderPane;
   @FXML
   Button favorTilesButton;
   @FXML
   Button religions;
   @FXML
   Button bonusCardsButton;
   @FXML
   Button townTilesButton;
   @FXML
   Button scoringTilesButton;
   @FXML
   Button scoreTable;
   @FXML
   Button skipRound;
   @FXML
   Label testText;
   @FXML
   Button specialActions;
   @FXML
   Button upgradeShipping;
   @FXML
   Button upgradeStruct;
   @FXML
   Button sendPriest;
   @FXML
   Button powerActions;
   @FXML
   Button upgradeSpade;



   Button[][] terrains;
   Map map;
   Player[] playerList;
   GameHandler gameHandler;



   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
      System.out.println("Initialize is called");
      int index = 0;
      terrains = new Button[ROW_NUMBER][COLUMN_NUMBER];

      for (int i = 0; i < ROW_NUMBER * COLUMN_NUMBER; i++) {
         int row = i / 13;
         int col = i % 13;
         if (row % 2 == 0)
            terrains[row][col] = (Button) mapPane.getChildren().get(index);
         else if (col == 12) {
            terrains[row][col] = null;
            index--;
         } else
            terrains[row][col] = (Button) mapPane.getChildren().get(index);
         index++;
      }

      createSpaces();
      //testText.setText(playerList[0].getNickName());
      Thread thread = new Thread(new Runnable() {

         @Override
         public void run() {
            Runnable updater = new Runnable() {

               @Override
               public void run() {

                  if(playerList != null)
                  {
                     HBox factionsView = new HBox(5);

                     ArrayList<PlayerView> playerViewList = new ArrayList<>();
                     for( int i = 0; i < playerList.length; i ++)
                     {
                        if(playerList[i] != null)
                        {
                           playerViewList.add(new PlayerView(playerList[i]));

                        }
                     }
                     factionsView.getChildren().addAll(playerViewList);
                     factionsView.setPadding(new Insets(0, 0, 0, 100));
                     borderPane.setBottom(factionsView);


//                     ImageView imview = new ImageView();
//                     imview.setImage(new Image("file:src/Images/FactionImages/Image_AleisterCrowley.jpeg"));
//                     imview.setFitHeight(150);
//                     imview.setFitWidth(50);
//                     borderPane.setBottom(imview);

                  }

               }
            };

            while (true) {
               try {
                  Thread.sleep(1000);
               } catch (InterruptedException ex) {
               }

               // UI update is run on the Application thread
               Platform.runLater(updater);
            }
         }

      });
      // don't let thread prevent JVM shutdown
      thread.setDaemon(true);
      thread.start();

      /*

      for(int i = 0; i < 9; i++)
         for( int j = 0; j < 13; j++){
            if(map.spaces[i][j].getType().equals(gameHandler.getPlayerList()[0].getFaction().TERRAIN_TILE)){
               if(terrains[i][j] != null)
                  terrains[i][j].setDisable(true);
            }
      }

      */






   }

   @FXML
   public void skipRoundClicked()
   {
      int currentPlayerId = gameHandler.getCurrentPlayerId();
      System.out.println("Current player was: " + playerList[currentPlayerId].getNickName());
      if( (currentPlayerId + 1) < playerList.length && playerList[currentPlayerId + 1] != null)
         gameHandler.setCurrentPlayerId(currentPlayerId + 1);
      else
         gameHandler.setCurrentPlayerId(0);

      currentPlayerId = gameHandler.getCurrentPlayerId();
      System.out.println("Current player is now: " + playerList[currentPlayerId].getNickName());
      System.out.println("--------------------------------------------------");
   }
   @FXML
   public void upgradeShippingClicked()
   {
      UpdateShippingController updateShippingController = new UpdateShippingController();
      updateShippingController.showUpdateShippingDialogs(gameHandler);

   }
   @FXML
   public void upgradeStructClicked()
   {
      System.out.println(gameHandler.getPlayerList()[0].getFaction().TERRAIN_TILE);

   }
   @FXML
   public void sentPriestClicked()
   {

   }
   @FXML
   public void powerActionClicked()
   {


   }
   @FXML
   public void upgradeSpadeClicked()
   {
      UpdateSpadeController updateShippingController = new UpdateSpadeController();
      updateShippingController.showUpdateSpadeDialogs(gameHandler);
   }
   @FXML
   public void terraformClicked()
   {

      TerraformController.updateTerraform(gameHandler, terrains, map);

   }
   @FXML
   public void religionsClicked()
   {
      ReligionController religionController = new ReligionController();
      religionController.showReligion(gameHandler);
   }
   @FXML
   public void scoreTableClicked()
   {
      ScoreTableController scoreTableController = new ScoreTableController();
      scoreTableController.showScoreTable(gameHandler);
   }
   @FXML
   public void bonusCardsClicked()
   {
      BonusCardsController bonusCardsController = new BonusCardsController();
      bonusCardsController.showBonusCardsTable(gameHandler);
   }
   @FXML
   public void townTilesClicked()
   {
      TownTilesController townTilesController = new TownTilesController();
      townTilesController.showTownTilesTable(gameHandler);
   }
   @FXML
   public void scoringTilesClicked()
   {
      ScoringTilesController scoringTilesController = new ScoringTilesController();
      scoringTilesController.showScoringTilesTable(gameHandler);

   }
   @FXML
   public void favorTilesClicked()
   {
      FavorTilesController favorTilesController = new FavorTilesController();
      favorTilesController.showFavorTilesTable(gameHandler);

   }
   @FXML
   public void specialActionClicked(){
      SpecialActionController specialActionController = new SpecialActionController();
      specialActionController.showSpeacialActions(gameHandler);
   }
   public void createSpaces()
   {
      System.out.println("Create is called");
      Space[][] spaces = new Space[ROW_NUMBER][COLUMN_NUMBER];
      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            Space space = new Space();
            if( terrains[i][j] == null)
               space.setType("Empty");
            else
            {
               switch (terrains[i][j].getId()) {
                  case "blackHexagon": space.setType("Swamp");
                     break;
                  case "blueHexagon": space.setType("Lakes");
                     break;
                  case "brownHexagon": space.setType("Plains");
                     break;
                  case "greenHexagon": space.setType("Forest");
                     break;
                  case "redHexagon": space.setType("Wasteland");
                     break;
                  case "riverHexagon": space.setType("River");
                     break;
                  case "whiteHexagon": space.setType("Mountains");
                     break;
                  default: space.setType("River");
                     break;
               }
            }
            spaces[i][j] = space;
         }
      }
      map = new Map(spaces);
   }

   public void loadPlayers(ArrayList<Faction> factionList, ArrayList<String> playerNames)
   {
      int size = factionList.size();

      Player[] playerList = new Player[5];
      for( int i = 0; i < size; i ++)
      {
         playerList[i] =  new Player(factionList.get(i), playerNames.get(i), i);
      }
      this.playerList = playerList;

      gameHandler = new GameHandler(playerList, size);

   }
   public void loadInitialMap()
   {
      for(int i = 0; i < 9; i++)
         for( int j = 0; j < 13; j++){
            if(map.spaces[i][j].getType().equals(gameHandler.getPlayerList()[0].getFaction().TERRAIN_TILE)){
               if(terrains[i][j] != null)
                  terrains[i][j].setDisable(true);
            }
         }
   }
}
