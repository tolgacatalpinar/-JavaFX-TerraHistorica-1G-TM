package Controller.CardsAndTilesControllers;

import Model.CardsAndTiles.FavorTile;
import Model.GameHandler;
import View.CardsAndTilesViews.FavorTileView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FavorTilesController {


   public void showFavorTilesTable(GameHandler gameHandler)
   {
      VBox wholeFavor = new VBox();
      HBox favorBox1 = new HBox();
      HBox favorBox2 = new HBox();
      HBox favorBox3 = new HBox();

      ArrayList<FavorTile> favorTiles = gameHandler.getCardsAndTiles().getFavorTiles();
      for( int i = 0; i < (int) Math.ceil((double)favorTiles.size() / 3); i ++)
      {
         favorBox1.getChildren().add(new FavorTileView(favorTiles.get(i)));
      }
      for( int i = (int) Math.ceil((double)favorTiles.size() / 3); i < (int) Math.ceil((double)favorTiles.size() * 2 / 3); i ++)
      {
         favorBox2.getChildren().add(new FavorTileView(favorTiles.get(i)));
      }
      for( int i = (int) Math.ceil((double)favorTiles.size() * 2 / 3); i < (int) Math.ceil(favorTiles.size()); i ++)
      {
         favorBox3.getChildren().add(new FavorTileView(favorTiles.get(i)));
      }
      wholeFavor.getChildren().addAll(favorBox1, favorBox2, favorBox3);
      wholeFavor.setPadding( new Insets(100, 0, 0, 50));


      wholeFavor.setMinHeight(800);
      wholeFavor.setMinWidth(1200);
      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      Scene dialogScene = new Scene(wholeFavor, 1100, 600);
      dialog.setScene(dialogScene);
      dialog.setTitle("Favor Tiles");
      wholeFavor.setBackground(new Background( new BackgroundImage( new Image("favor_tiles_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
              BackgroundSize.DEFAULT)));
      dialog.show();
   }
}
