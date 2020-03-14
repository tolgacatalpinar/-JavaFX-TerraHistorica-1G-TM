package Controller.TerrainSubclasses;

import Controller.Terrain;
import javafx.scene.image.ImageView;

public class Wasteland extends Terrain {

   public Wasteland()
   {
      int size = 50;
      ImageView imView = new ImageView("redTerrain.png");
      imView.setFitHeight(size);
      imView.setFitWidth(Math.sqrt(0.75) * size);
      setGraphic(imView);
   }
}
