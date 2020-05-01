package Controller;

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
}
