package Controller.TerrainSubclasses;

import Controller.Terrain;
import javafx.scene.image.ImageView;

public class Mountains extends Terrain {

   public Mountains()
   {
      int size = 50;
      ImageView imView = new ImageView("whiteTerrain.png");
      imView.setFitHeight(size);
      imView.setFitWidth(Math.sqrt(0.75) * size);
      setGraphic(imView);
   }
}
