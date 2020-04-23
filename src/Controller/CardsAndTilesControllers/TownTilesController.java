package Controller.CardsAndTilesControllers;

import Model.CardsAndTiles.TownTile;
import Model.GameHandler;
import View.CardsAndTilesViews.TownTileView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TownTilesController {

   public void showTownTilesTable(GameHandler gameHandler)
   {
      VBox wholeTown = new VBox();
      HBox first = new HBox();
      HBox second = new HBox();
      HBox third = new HBox();


      ArrayList<TownTile> townTiles = gameHandler.getCardsAndTiles().getTownTiles();

      for(int i = 0; i < townTiles.size() / 3; i++) {
         first.getChildren().add(new TownTileView(townTiles.get(i)));
      }
      for(int i = townTiles.size() / 3; i < townTiles.size() * 2 / 3 ; i++) {
         second.getChildren().add(new TownTileView(townTiles.get(i)));
      }
      for(int i = townTiles.size() * 2 / 3; i < townTiles.size() ; i++) {
         third.getChildren().add(new TownTileView(townTiles.get(i)));
      }
      wholeTown.getChildren().addAll(first, second, third);
      wholeTown.setPadding( new Insets(100, 0, 0, 50));


      wholeTown.setMinHeight(800);
      wholeTown.setMinWidth(1200);
      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      Scene dialogScene = new Scene(wholeTown, 1100, 600);
      dialog.setScene(dialogScene);
      dialog.setTitle("Town Tiles");
      wholeTown.setBackground(new Background( new BackgroundImage( new Image("town_tiles_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
              BackgroundSize.DEFAULT)));
      dialog.show();
   }
}
