package Controller;

import Model.*;
//import Model.River;
//import Model.TerrainSubclasses.*;
import Model.CardsAndTiles.CardsAndTiles;
import View.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
   Button exchangeResourcesButton;
   @FXML
   Button scoreTable;
   @FXML
   Button passRound;
   @FXML
   Button skipTurn;
   @FXML
   Label testText;
   @FXML
   Button specialActions;
   @FXML
   Button terraform;
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


   ArrayList<PlayerView> playerViewList;


   Button[][] terrains;
   Button[] actions;
   Map map;
   int curPlayerId;
   Religion[] religionArr;
   Player[] playerList;
   GameHandler gameHandler;
   CardsAndTiles cardsAndTiles;
   CardsAndTilesController cardsAndTilesController;
   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
      actions = new Button[]{specialActions, terraform, upgradeShipping, upgradeStruct, sendPriest, powerActions, upgradeSpade};
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

                  if (playerList != null) {
                     HBox factionsView = new HBox(5);

                     playerViewList = new ArrayList<>();
                     for (int i = 0; i < playerList.length; i++) {
                        if (playerList[i] != null) {
                           playerViewList.add(new PlayerView(playerList[i]));

                        }
                     }
                     factionsView.getChildren().addAll(playerViewList);
                     //displayPlayerTurn(playerViewList);
                     factionsView.setPadding(new Insets(0, 0, 0, 100));
                     borderPane.setBottom(factionsView);
                     displayPlayerTurn(playerViewList);
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
                  Thread.sleep(100);
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
      setButtonClickForInitialDwellings();

   }
   @FXML
   public void chooseAction(int choice){

   }

   @FXML
   public void skipTurnClicked() {
      disableButtonClicks();
      enableTerrains();
      enableActions();
      int currentPlayerId = gameHandler.getCurrentPlayerId();
//      System.out.println("Current player was: " + playerList[currentPlayerId].getNickName());
      if ((currentPlayerId + 1) < playerList.length && playerList[currentPlayerId + 1] != null)
         gameHandler.setCurrentPlayerId(currentPlayerId + 1);
      else
         gameHandler.setCurrentPlayerId(0);

      Player currentPlayer = gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()];
      System.out.println("current dwelling: " + currentPlayer.getDwellingNum());
      if (currentPlayer.getDwellingNum() < currentPlayer.getFaction().startingDwellingNum) {
         loadInitialMap();
         setButtonClickForInitialDwellings();
      }


//      System.out.println("Current player is now: " + playerList[currentPlayerId].getNickName());
//      System.out.println("--------------------------------------------------");
   }

   @FXML
   public void skipRoundClicked(){

   }

   @FXML
   public void upgradeShippingClicked() {
      ActionController.showUpdateShippingDialogs(gameHandler);
   }

   @FXML
   public void sentPriestClicked() {
      ReligionController religionController = new ReligionController();
      religionController.showChoices(gameHandler);
   }

   @FXML
   public void powerActionClicked() {
      ActionController.showPowerActions(gameHandler);
   }

   @FXML
   public void upgradeSpadeClicked() {

      ActionController.showUpdateSpadeDialogs(gameHandler);
   }

   @FXML
   public void terraformClicked() {
      ActionController.terraform(playerList,curPlayerId, terrains, map);
   }

   @FXML
   public void upgradeStructureClicked() {
      ActionController.upgradeStructure(playerList, curPlayerId,terrains, map);
   }

   @FXML
   public void religionsClicked() {
      ReligionController religionController = new ReligionController();
      religionController.showReligion(gameHandler,0);
   }

   @FXML
   public void scoreTableClicked() {
      cardsAndTilesController.showScoreTable(this);
   }

   @FXML
   public void bonusCardsClicked() {
      cardsAndTilesController.showBonusCardsTable(this);
   }

   @FXML
   public void townTilesClicked() {
      cardsAndTilesController.showTownTilesTable(this);
   }

   @FXML
   public void scoringTilesClicked() {

      cardsAndTilesController.showScoringTilesTable(this);
   }

   @FXML   public void exchangeResourcesClicked(){

   }

   @FXML
   public void favorTilesClicked() {

      cardsAndTilesController.showFavorTilesTable(this);

   }
   //TODO
   public void skipTurn(){

   }

   @FXML
   public void specialActionClicked() {
      ActionController.showSpeacialActions(gameHandler);
   }

   public void createSpaces() {
      System.out.println("Create is called");
      Space[][] spaces = new Space[ROW_NUMBER][COLUMN_NUMBER];
      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            Space space = new Space();
            if (terrains[i][j] == null)
               space.setType("Empty");
            else {
               switch (terrains[i][j].getId()) {
                  case "blackHexagon":
                     space.setType("Swamp");
                     break;
                  case "blueHexagon":
                     space.setType("Lakes");
                     break;
                  case "brownHexagon":
                     space.setType("Plains");
                     break;
                  case "greenHexagon":
                     space.setType("Forest");
                     break;
                  case "yellowHexagon": space.setType("Desert");
                     break;
                  case "redHexagon": space.setType("Wasteland");
                     break;
                  case "riverHexagon":
                     space.setType("River");
                     break;
                  case "whiteHexagon":
                     space.setType("Mountains");
                     break;
                  default:
                     space.setType("River");
                     break;
               }
            }
            spaces[i][j] = space;
         }
      }
      map = new Map(spaces);
   }

   public void loadPlayers(ArrayList<Faction> factionList, ArrayList<String> playerNames) {
      int size = factionList.size();

      Player[] playerList = new Player[5];
      for (int i = 0; i < size; i++) {
         playerList[i] = new Player(factionList.get(i), playerNames.get(i), i);
      }
      this.playerList = playerList;

      gameHandler = new GameHandler(playerList, size);
      cardsAndTiles = new CardsAndTiles(playerList.length,playerList);
      cardsAndTilesController = new CardsAndTilesController();
   }

   public void loadInitialMap() {

      disableActions();
      disableAllTerrains();
      for (int i = 0; i < 9; i++)
         for (int j = 0; j < 13; j++) {
            if (map.spaces[i][j].getType().equals(gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE)) {
               if (terrains[i][j] != null && !map.spaces[i][j].isOccupied())
                  terrains[i][j].setDisable(false);
            }
         }
   }

   /**TODO
    * TAŞINACAK
    */
   public void setButtonClickForInitialDwellings() {
      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            final int row = i;
            final int col = j;
            if (terrains[i][j] != null) {
               terrains[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                  @Override
                  public void handle(MouseEvent event) {
                     skipTurn.setDisable(false);
                     map.buildDwelling(map.spaces[row][col], map.spaces[row][col].getType(), true);
                     PlayerHandler.buildInitialDwelling(gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()]);
                     TerrainController.buildDwelling(terrains[row][col], map.spaces[row][col].getType());
                     map.spaces[row][col].setStructure("Dwelling");
                     for (int i = 0; i < ROW_NUMBER; i++) {
                        for (int j = 0; j < COLUMN_NUMBER; j++) {
                           if( terrains[i][j] != null)
                              terrains[i][j].setDisable(true);
                        }
                     }
                  }
               });
            }
         }
      }
   }

   /**TODO
    * TAŞINACAK
    */
   public void disableButtonClicks(){
      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if( terrains[i][j] != null)
               terrains[i][j].setOnMouseClicked(null);
         }
      }
   }
   /**TODO
    * TAŞINACAK
    */
   public void enableTerrains()
   {
      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if( terrains[i][j] != null && map.spaces[i][j].getType() != "River")
               terrains[i][j].setDisable(false);
         }
      }
   }
   /**TODO
    * TAŞINACAK
    */
   public void disableAllTerrains()
   {
      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if( terrains[i][j] != null)
               terrains[i][j].setDisable(true);
         }
      }
   }
   /**TODO
    * TAŞINACAK
    */
   public void disableActions(){
      terraform.setDisable(true);
      upgradeShipping.setDisable(true);
      upgradeStruct.setDisable(true);
      sendPriest.setDisable(true);
      powerActions.setDisable(true);
      specialActions.setDisable(true);
      upgradeSpade.setDisable(true);
      passRound.setDisable(true);
      skipTurn.setDisable(true);
   }
   /**TODO
    * TAŞINACAK
    */
   public void enableActions(){
      terraform.setDisable(false);
      upgradeShipping.setDisable(false);
      upgradeStruct.setDisable(false);
      sendPriest.setDisable(false);
      powerActions.setDisable(false);
      specialActions.setDisable(false);
      upgradeSpade.setDisable(false);
      passRound.setDisable(false);
      skipTurn.setDisable(false);
   }
   /**TODO
    * TAŞINACAK
    */
   public void displayPlayerTurn(ArrayList<PlayerView> playerViewList){

      for (PlayerView playerView : playerViewList) {
         playerView.setStyle("");
      }
      
      switch (gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().TERRAIN_TILE) {
         case "Wasteland":
            playerViewList.get(gameHandler.getCurrentPlayerId()).setStyle("-fx-effect: dropshadow( gaussian , rgba(224, 15, 0, 1) , 30,0.5,0,1 );");
            break;
         case "Forest":
            playerViewList.get(gameHandler.getCurrentPlayerId()).setStyle("-fx-effect: dropshadow( gaussian , rgba(23, 150, 26, 1) , 30,0.5,0,1 );");
            break;
         case "Lakes":
            playerViewList.get(gameHandler.getCurrentPlayerId()).setStyle("-fx-effect: dropshadow( gaussian , rgba(0, 189, 214, 1) , 30,0.5,0,1 );");
            break;
         case "Desert":
            playerViewList.get(gameHandler.getCurrentPlayerId()).setStyle("-fx-effect: dropshadow( gaussian , rgba(214, 175, 0, 1) , 30,0.5,0,1 );");
            break;
         case "Mountains":
            playerViewList.get(gameHandler.getCurrentPlayerId()).setStyle("-fx-effect: dropshadow( gaussian , rgba(191, 191, 191, 1), 30,0.5,0,1 );");
            break;
         case "Swamp":
            playerViewList.get(gameHandler.getCurrentPlayerId()).setStyle("-fx-effect: dropshadow( gaussian , rgba(22, 20, 8, 1), 30,0.5,0,1 );");
            break;
         case "Plains":
            playerViewList.get(gameHandler.getCurrentPlayerId()).setStyle("-fx-effect: dropshadow( gaussian , rgba(152, 93, 27, 1), 30,0.5,0,1 );");
            break;
      }

   }

   public CardsAndTiles getCardsAndTiles() {
      return cardsAndTiles;
   }
}
