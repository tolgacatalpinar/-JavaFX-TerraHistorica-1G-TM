package Model.TerrainSubclasses;

import Model.Terrain;
import javafx.scene.image.ImageView;

public class Plains extends Terrain {

   public Plains()
   {
      int size = 50;
      ImageView imView = new ImageView("brownTerrain.png");
      imView.setFitHeight(size);
      imView.setFitWidth(Math.sqrt(0.75) * size);
      setGraphic(imView);
   }
}
