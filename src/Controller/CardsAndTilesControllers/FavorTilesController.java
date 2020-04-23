package Controller.CardsAndTilesControllers;

import Model.CardsAndTiles.FavorTile;
import Model.GameHandler;
import View.CardsAndTilesViews.FavorTileView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
      for( int i = 0; i < favorTiles.size() / 3; i ++)
      {
         favorBox1.getChildren().add(new FavorTileView(favorTiles.get(i)));
      }
      for( int i = favorTiles.size() / 3; i < favorTiles.size() * 2 / 3; i ++)
      {
         favorBox2.getChildren().add(new FavorTileView(favorTiles.get(i)));
      }
      for( int i = favorTiles.size() * 2 / 3; i < favorTiles.size(); i ++)
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
      dialog.show();
   }
}
