package Controller;

import Model.Map;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TerrainController {

   public static void buildDwelling(Button button, String color)
   {
      if( color == "Lakes")
         button.setStyle("-fx-background-image: url('/blueTerrainWithDwelling.png');");
      else if( color == "Wasteland")
         button.setStyle("-fx-background-image: url('/redTerrainWithDwelling.png');");
      else if( color == "Mountains")
         button.setStyle("-fx-background-image: url('/whiteTerrainWithDwelling.png');");
      else if( color == "Desert")
         button.setStyle("-fx-background-image: url('/yellowTerrainWithDwelling.png');");
      else if( color == "Forest")
         button.setStyle("-fx-background-image: url('/greenTerrainWithDwelling.png');");
      else if( color == "Plains")
         button.setStyle("-fx-background-image: url('/brownTerrainWithDwelling.png');");
      else if( color == "Swamp")
         button.setStyle("-fx-background-image: url('/blackTerrainWithDwelling.png');");

   }

   public static void terraform(Button button, String color)
   {
      if( color == "Lakes")
         button.setStyle("-fx-background-image: url('/blueTerrain.png');");
      else if( color == "Wasteland")
         button.setStyle("-fx-background-image: url('/redTerrain.png');");
      else if( color == "Mountains")
         button.setStyle("-fx-background-image: url('/whiteTerrain.png');");
      else if( color == "Desert")
         button.setStyle("-fx-background-image: url('/yellowTerrain.png');");
      else if( color == "Forest")
         button.setStyle("-fx-background-image: url('/greenTerrain.png');");
      else if( color == "Plains")
         button.setStyle("-fx-background-image: url('/brownTerrain.png');");
      else if( color == "Swamp")
         button.setStyle("-fx-background-image: url('/blackTerrain.png');");

   }

   public static void upgradeToTradingPost(Button button, String color)
   {
      if(color.equals("Lakes"))
         button.setStyle("-fx-background-image: url('/blueTerrainWithTradingPost.png');");
      else if(color.equals("Wasteland"))
         button.setStyle("-fx-background-image: url('/redTerrainWithTradingPost.png');");
      else if(color.equals("Mountains"))
         button.setStyle("-fx-background-image: url('/whiteTerrainWithTradingPost.png');");
      else if(color.equals("Desert"))
         button.setStyle("-fx-background-image: url('/yellowTerrainWithTradingPost.png');");
      else if(color.equals("Forest"))
         button.setStyle("-fx-background-image: url('/greenTerrainWithTradingPost.png');");
      else if(color.equals("Plains"))
         button.setStyle("-fx-background-image: url('/brownTerrainWithTradingPost.png');");
      else if(color.equals("Swamp"))
         button.setStyle("-fx-background-image: url('/blackTerrainWithTradingPost.png');");

   }

   public static void upgradeToStronghold(Button button, String color)
   {
      if(color.equals("Lakes"))
         button.setStyle("-fx-background-image: url('/blueTerrainWithStronghold.png');");
      else if(color.equals("Wasteland"))
         button.setStyle("-fx-background-image: url('/redTerrainWithStronghold.png');");
      else if(color.equals("Mountains"))
         button.setStyle("-fx-background-image: url('/whiteTerrainWithStronghold.png');");
      else if(color.equals("Desert"))
         button.setStyle("-fx-background-image: url('/yellowTerrainWithStronghold.png');");
      else if(color.equals("Forest"))
         button.setStyle("-fx-background-image: url('/greenTerrainWithStronghold.png');");
      else if(color.equals("Plains"))
         button.setStyle("-fx-background-image: url('/brownTerrainWithStronghold.png');");
      else if(color.equals("Swamp"))
         button.setStyle("-fx-background-image: url('/blackTerrainWithStronghold.png');");

   }

   public static void upgradeToTemple(Button button, String color)
   {
      if(color.equals("Lakes"))
         button.setStyle("-fx-background-image: url('/blueTerrainWithCastle.png');");
      else if(color.equals("Wasteland"))
         button.setStyle("-fx-background-image: url('/redTerrainWithCastle.png');");
      else if(color.equals("Mountains"))
         button.setStyle("-fx-background-image: url('/whiteTerrainWithCastle.png');");
      else if(color.equals("Desert"))
         button.setStyle("-fx-background-image: url('/yellowTerrainWithCastle.png');");
      else if(color.equals("Forest"))
         button.setStyle("-fx-background-image: url('/greenTerrainWithCastle.png');");
      else if(color.equals("Plains"))
         button.setStyle("-fx-background-image: url('/brownTerrainWithCastle.png');");
      else if(color.equals("Swamp"))
         button.setStyle("-fx-background-image: url('/blackTerrainWithCastle.png');");

   }

   public static void upgradeToSanctuary(Button button, String color)
   {
      if(color.equals("Lakes"))
         button.setStyle("-fx-background-image: url('/blueTerrainWithSanctuary.png');");
      else if(color.equals("Wasteland"))
         button.setStyle("-fx-background-image: url('/redTerrainWithSanctuary.png');");
      else if(color.equals("Mountains"))
         button.setStyle("-fx-background-image: url('/whiteTerrainWithSanctuary.png');");
      else if(color.equals("Desert"))
         button.setStyle("-fx-background-image: url('/yellowTerrainWithSanctuary.png');");
      else if(color.equals("Forest"))
         button.setStyle("-fx-background-image: url('/greenTerrainWithSanctuary.png');");
      else if(color.equals("Plains"))
         button.setStyle("-fx-background-image: url('/brownTerrainWithSanctuary.png');");
      else if(color.equals("Swamp"))
         button.setStyle("-fx-background-image: url('/blackTerrainWithSanctuary.png');");

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

}
