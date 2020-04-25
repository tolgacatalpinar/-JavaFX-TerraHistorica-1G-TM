package Controller.CardsAndTilesControllers;

import Model.CardsAndTiles.ScoringTile;
import Model.GameHandler;
import View.CardsAndTilesViews.ScoringTileView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ScoringTilesController {

   public void showScoringTilesTable(GameHandler gameHandler)
   {
      HBox wholeScoring = new HBox();
      VBox first = new VBox();
      VBox second = new VBox();

      ArrayList<ScoringTile> scoringTiles = gameHandler.getCardsAndTiles().getSelectedScoringTiles();

      for(int i = 0; i < (int) Math.ceil((double)scoringTiles.size() / 2); i++) {
         first.getChildren().add(new ScoringTileView(scoringTiles.get(i)));
      }
      for(int i = (int) Math.ceil((double)scoringTiles.size()/2); i < scoringTiles.size() ; i++) {
         second.getChildren().add(new ScoringTileView(scoringTiles.get(i)));
      }
      wholeScoring.getChildren().addAll(first, second);
      wholeScoring.setPadding( new Insets(100, 0, 0, 50));


      wholeScoring.setMinHeight(800);
      wholeScoring.setMinWidth(1200);
      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      Scene dialogScene = new Scene(wholeScoring, 1100, 600);
      dialog.setScene(dialogScene);
      dialog.setTitle("Scoring Tiles");
      dialog.setResizable(false);
      wholeScoring.setBackground(new Background( new BackgroundImage( new Image("scoring_tiles_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
              BackgroundSize.DEFAULT)));
      dialog.show();
   }
}
