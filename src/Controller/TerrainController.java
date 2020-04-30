package Controller;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TerrainController {

   public static void buildDwelling(Button button, String color)
   {
      if( color == "Lakes")
         button.setBackground( new Background(new BackgroundImage( new Image("blueTerrainWithDwelling.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                 BackgroundSize.DEFAULT)));
      else if( color == "Wasteland")
         button.setBackground( new Background(new BackgroundImage( new Image("redTerrainWithDwelling.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                 BackgroundSize.DEFAULT)));
      else if( color == "Mountains")
         button.setBackground( new Background(new BackgroundImage( new Image("whiteTerrainWithDwelling.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                 BackgroundSize.DEFAULT)));
      else if( color == "Desert")
         button.setBackground( new Background(new BackgroundImage( new Image("yellowTerrainWithDwelling.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                 BackgroundSize.DEFAULT)));
      else if( color == "Forest")
         button.setBackground( new Background(new BackgroundImage( new Image("greenTerrainWithDwelling.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                 BackgroundSize.DEFAULT)));
      else if( color == "Plains")
         button.setBackground( new Background(new BackgroundImage( new Image("brownTerrainWithDwelling.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                 BackgroundSize.DEFAULT)));
   }
}
