package Controller.CardsAndTilesControllers;

import Model.CardsAndTiles.TownTile;
import Model.GameHandler;
import View.CardsAndTilesViews.TownTileView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TownTilesController {

   public void showTownTilesTable(GameHandler gameHandler)
   {
      HBox wholeTown = new HBox();
      VBox first = new VBox();
      VBox second = new VBox();

      ArrayList<TownTile> townTiles = gameHandler.getCardsAndTiles().getTownTiles();

      for(int i = 0; i < townTiles.size() / 2; i++) {
         first.getChildren().add(new TownTileView(townTiles.get(i)));
      }
      for(int i = townTiles.size()/2; i < townTiles.size() ; i++) {
         second.getChildren().add(new TownTileView(townTiles.get(i)));
      }
      wholeTown.getChildren().addAll(first, second);
      wholeTown.setPadding( new Insets(100, 0, 0, 50));


      wholeTown.setMinHeight(800);
      wholeTown.setMinWidth(1200);
      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      Scene dialogScene = new Scene(wholeTown, 1100, 600);
      dialog.setScene(dialogScene);
      dialog.setTitle("Town Tiles");
      dialog.show();
   }
}
