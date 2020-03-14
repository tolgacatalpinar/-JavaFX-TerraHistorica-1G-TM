package Model.TerrainSubclasses;

import Model.Terrain;
import javafx.scene.image.ImageView;

public class Lakes extends Terrain {
   public Lakes()
   {
      int size = 50;
      ImageView imView = new ImageView("blueTerrain.png");
      imView.setFitHeight(size);
      imView.setFitWidth(Math.sqrt(0.75) * size);
      setGraphic(imView);
   }
}
