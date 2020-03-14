package Model.TerrainSubclasses;

import Model.Terrain;
import javafx.scene.image.ImageView;

public class Desert extends Terrain {

   public Desert()
   {
      int size = 50;
      ImageView imView = new ImageView("yellowTerrain.png");
      imView.setFitHeight(size);
      imView.setFitWidth(Math.sqrt(0.75) * size);
      setGraphic(imView);
   }
}
