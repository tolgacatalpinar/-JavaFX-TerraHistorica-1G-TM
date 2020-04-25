package Controller.CardsAndTilesControllers;

import Model.GameHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ScoreTableController {


   public void showScoreTable(GameHandler gameHandler)
   {
      Pane emptyPane = new Pane();

      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      Scene dialogScene = new Scene(emptyPane, 1100, 600);
      dialog.setScene(dialogScene);
      dialog.setTitle("Town Tiles");
      dialog.setResizable(false);
      emptyPane.setBackground(new Background( new BackgroundImage( new Image("score_table_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
              BackgroundSize.DEFAULT)));
      dialog.show();
   }
}
