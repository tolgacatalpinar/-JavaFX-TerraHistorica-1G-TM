package Controller;

import Model.Map;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.io.Serializable;
public class TerrainController implements Serializable {

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
