package Controller.TerrainSubclasses;

import Controller.Terrain;
import javafx.scene.image.ImageView;

public class Swamp extends Terrain {

   public Swamp()
   {
      int size = 50;
      ImageView imView = new ImageView("blackTerrain.png");
      imView.setFitHeight(size);
      imView.setFitWidth(Math.sqrt(0.75) * size);
      setGraphic(imView);
   }
}
