package Controller.TerrainSubclasses;

import Controller.Terrain;
import javafx.scene.image.ImageView;

public class Forest extends Terrain {

   public Forest()
   {
      int size = 50;
      ImageView imView = new ImageView("greenTerrain.png");
      imView.setFitHeight(size);
      imView.setFitWidth(Math.sqrt(0.75) * size);
      setGraphic(imView);
   }
}
