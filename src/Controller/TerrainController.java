package Controller;

import Model.Map;
import Model.Player;
import Model.PlayerHandler;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;

import java.io.Serializable;
public class TerrainController implements Serializable {

   final static int ROW_NUMBER = 9;
   final static int COLUMN_NUMBER = 13;

   public static void buildDwelling(Button button, String color)
   {
      if( color == "Lakes")
         button.setStyle("-fx-background-image: url('/lakesWithDwelling.png');");
      else if( color == "Wasteland")
         button.setStyle("-fx-background-image: url('/wastelandWithDwelling.png');");
      else if( color == "Mountains")
         button.setStyle("-fx-background-image: url('/mountainsWithDwelling.png');");
      else if( color == "Desert")
         button.setStyle("-fx-background-image: url('/desertWithDwelling.png');");
      else if( color == "Forest")
         button.setStyle("-fx-background-image: url('/forestWithDwelling.png');");
      else if( color == "Plains")
         button.setStyle("-fx-background-image: url('/plainsWithDwelling.png');");
      else if( color == "Swamp")
         button.setStyle("-fx-background-image: url('/swampWithDwelling.png');");

   }

   public static void terraform(Button button, String color)
   {
      if( color == "Lakes")
         button.setStyle("-fx-background-image: url('/lakes.png');");
      else if( color == "Wasteland")
         button.setStyle("-fx-background-image: url('/wasteland.png');");
      else if( color == "Mountains")
         button.setStyle("-fx-background-image: url('/mountains.png');");
      else if( color == "Desert")
         button.setStyle("-fx-background-image: url('/desert.png');");
      else if( color == "Forest")
         button.setStyle("-fx-background-image: url('/forest.png');");
      else if( color == "Plains")
         button.setStyle("-fx-background-image: url('/plains.png');");
      else if( color == "Swamp")
         button.setStyle("-fx-background-image: url('/swamp.png');");

   }

   public static void upgradeToTradingPost(Button button, String color)
   {
      if(color.equals("Lakes"))
         button.setStyle("-fx-background-image: url('/lakesWithTradingPost.png');");
      else if(color.equals("Wasteland"))
         button.setStyle("-fx-background-image: url('/wastelandWithTradingPost.png');");
      else if(color.equals("Mountains"))
         button.setStyle("-fx-background-image: url('/mountainsWithTradingPost.png');");
      else if(color.equals("Desert"))
         button.setStyle("-fx-background-image: url('/desertWithTradingPost.png');");
      else if(color.equals("Forest"))
         button.setStyle("-fx-background-image: url('/forestWithTradingPost.png');");
      else if(color.equals("Plains"))
         button.setStyle("-fx-background-image: url('/plainsWithTradingPost.png');");
      else if(color.equals("Swamp"))
         button.setStyle("-fx-background-image: url('/swampWithTradingPost.png');");

   }

   public static void upgradeToStronghold(Button button, String color)
   {
      if(color.equals("Lakes"))
         button.setStyle("-fx-background-image: url('/lakesWithStronghold.png');");
      else if(color.equals("Wasteland"))
         button.setStyle("-fx-background-image: url('/wastelandWithStronghold.png');");
      else if(color.equals("Mountains"))
         button.setStyle("-fx-background-image: url('/mountainsWithStronghold.png');");
      else if(color.equals("Desert"))
         button.setStyle("-fx-background-image: url('/desertWithStronghold.png');");
      else if(color.equals("Forest"))
         button.setStyle("-fx-background-image: url('/forestWithStronghold.png');");
      else if(color.equals("Plains"))
         button.setStyle("-fx-background-image: url('/plainsWithStronghold.png');");
      else if(color.equals("Swamp"))
         button.setStyle("-fx-background-image: url('/swampWithStronghold.png');");

   }

   public static void upgradeToTemple(Button button, String color)
   {
      if(color.equals("Lakes"))
         button.setStyle("-fx-background-image: url('/lakesWithTemple.png');");
      else if(color.equals("Wasteland"))
         button.setStyle("-fx-background-image: url('/wastelandWithTemple.png');");
      else if(color.equals("Mountains"))
         button.setStyle("-fx-background-image: url('/mountainsWithTemple.png');");
      else if(color.equals("Desert"))
         button.setStyle("-fx-background-image: url('/desertWithTemple.png');");
      else if(color.equals("Forest"))
         button.setStyle("-fx-background-image: url('/forestWithTemple.png');");
      else if(color.equals("Plains"))
         button.setStyle("-fx-background-image: url('/plainsWithTemple.png');");
      else if(color.equals("Swamp"))
         button.setStyle("-fx-background-image: url('/swampWithTemple.png');");

   }
   public static void setButtonClickForInitialDwellings(Button[][] terrains, Map map, Button skipTurn, Player current, RoundController roundController) {
      PlayerHandler playerHandler = new PlayerHandler();
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
                     //playerHandler.buildInitialDwelling(current);
                     map.spaces[row][col].setPlayer(current);

                     TerrainController.buildDwelling(terrains[row][col], map.spaces[row][col].getType());
                     map.spaces[row][col].setStructure("Dwelling");
                     for (int i = 0; i < ROW_NUMBER; i++) {
                        for (int j = 0; j < COLUMN_NUMBER; j++) {
                           if (terrains[i][j] != null)
                              terrains[i][j].setDisable(true);
                        }
                     }
                  }
               });
            }
         }
      }
   }
   public static void upgradeToSanctuary(Button button, String color)
   {
      if(color.equals("Lakes"))
         button.setStyle("-fx-background-image: url('/lakesWithSanctuary.png');");
      else if(color.equals("Wasteland"))
         button.setStyle("-fx-background-image: url('/wastelandWithSanctuary.png');");
      else if(color.equals("Mountains"))
         button.setStyle("-fx-background-image: url('/mountainsWithSanctuary.png');");
      else if(color.equals("Desert"))
         button.setStyle("-fx-background-image: url('/desertWithSanctuary.png');");
      else if(color.equals("Forest"))
         button.setStyle("-fx-background-image: url('/forestWithSanctuary.png');");
      else if(color.equals("Plains"))
         button.setStyle("-fx-background-image: url('/plainsWithSanctuary.png');");
      else if(color.equals("Swamp"))
         button.setStyle("-fx-background-image: url('/swampWithSanctuary.png');");

   }

   public static void showTown(Button[][] terrains, Map map)
   {
      for (int i = 0; i < 9; i++) {
         for (int j = 0; j < 13; j++) {
            if(map.spaces[i][j].isMarked()) {
               terrains[i][j].getStyleClass().add("-fx-effect: dropshadow( gaussian , rgba(255,255,255,255) , 30,0.5,0,1 );");
            }
         }
      }
   }

   public static void enableTerrains(Button[][] terrains, Map map)
   {
      for (int i = 0; i < 9; i++) {
         for (int j = 0; j < 13; j++) {
            if( terrains[i][j] != null && !map.spaces[i][j].getType().equals("River"))
               terrains[i][j].setDisable(false);
         }
      }
   }


   public static void disableTerrains(Button[][] terrains, Map map)
   {
      for (int i = 0; i < 9; i++) {
         for (int j = 0; j < 13; j++) {
            if( terrains[i][j] != null && !map.spaces[i][j].getType().equals("River"))
               terrains[i][j].setDisable(true);
         }
      }
   }

   public static void disableButtonClicks(Button[][] terrains){
      for (int i = 0; i < 9; i++) {
         for (int j = 0; j < 13; j++) {
            if( terrains[i][j] != null)
               terrains[i][j].setOnMouseClicked(null);
         }
      }
   }

   public static void buildBridge(String type, Button[][] terrains, Map map, Pane mapPane, Button[] actions) {
      for(int i = 0; i < ROW_NUMBER; i++){
         for( int j = 0; j < COLUMN_NUMBER; j++){
            if(terrains[i][j] != null && map.spaces[i][j] != null)
               if(type.equals(map.spaces[i][j].getType()) && map.spaces[i][j].isOccupied() && map.spaces[i][j].getBridgability()) {
                  terrains[i][j].setDisable(false);
                  double x1 = terrains[i][j].getLayoutX();
                  double y1 = terrains[i][j].getLayoutY();
                  int finalI = i;
                  int finalJ = j;
                  terrains[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                     @Override
                     public void handle(MouseEvent event) {
                        terrains[finalI][finalJ].setDisable(true);
                        for (int k = 0; k < ROW_NUMBER; k++) {
                           for (int l = 0; l < COLUMN_NUMBER; l++) {
                              if (map.canBuildBridge(map.spaces[finalI][finalJ], map.spaces[k][l]) && !map.spaces[k][l].getBridgeConnection()) {
                                 terrains[k][l].setDisable(false);
                                 double x2 = terrains[k][l].getLayoutX();
                                 double y2 = terrains[k][l].getLayoutY();
                                 int finalK = k;
                                 int finalL = l;
                                 terrains[k][l].setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                       for(int a = 0; a < ROW_NUMBER; a++){
                                          for( int b = 0; b < COLUMN_NUMBER; b++) {
                                             if(terrains[a][b] != null) {
                                                terrains[a][b].setDisable(false);
                                             }
                                          }
                                       }
                                       double x = (x1 + x2)/2;
                                       double y = (y1 + y2)/2;

                                       if(Math.abs(x1- x2) < 5){
                                          Image imProfile = new Image(getClass().getResourceAsStream("/Images/bridgeLittle.png"));
                                          ImageView imView = new ImageView(imProfile);
                                          Translate translate = new Translate();
                                          translate.setX(x+22);
                                          translate.setY(y+12);
                                          mapPane.getChildren().add(imView);
                                          imView.getTransforms().addAll(translate);

                                       }

                                       else if((x2- x1 > 5 && y2 - y1 > 5) || (x1- x2 > 5 && y1 - y2 > 5)){
                                          Image imProfile = new Image(getClass().getResourceAsStream("/Images/bridgeMinus60.png"));
                                          ImageView imView = new ImageView(imProfile);
                                          Translate translate = new Translate();
                                          translate.setX(x+9);
                                          translate.setY(y+13);
                                          mapPane.getChildren().add(imView);
                                          imView.getTransforms().addAll(translate);
                                       }

                                       else if((x2- x1 > 5 && y1 - y2 > 5) || (x1- x2 > 5 && y2 - y1 > 5)){
                                          Image imProfile = new Image(getClass().getResourceAsStream("/Images/bridgePlus60.png"));
                                          ImageView imView = new ImageView(imProfile);
                                          Translate translate = new Translate();
                                          translate.setX(x+11);
                                          translate.setY(y+13);
                                          mapPane.getChildren().add(imView);
                                          imView.getTransforms().addAll(translate);
                                       }



                                       System.out.println("x1: " + x1 + "y1: "  + y1 + "\nx2: " + x2 + "y2: " + y2);
                                       map.spaces[finalK][finalL].setBridgeConnection(true);
                                       map.spaces[finalK][finalL].setBridgeType(type);
                                       map.spaces[finalI][finalJ].setBridgeConnection(true);
                                       map.spaces[finalI][finalJ].setBridgeType(type);
                                       for(int i = 0; i < actions.length; i++)
                                          actions[i].setDisable(true);
                                    }
                                 });
                              }
                           }
                        }
                     }
                  });
               }
         }
      }
   }

}
