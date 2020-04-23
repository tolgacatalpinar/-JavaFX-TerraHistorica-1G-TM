package Controller.CardsAndTilesControllers;

import Model.CardsAndTiles.BonusCard;
import Model.GameHandler;
import View.CardsAndTilesViews.BonusCardView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BonusCardsController {

   public void showBonusCardsTable(GameHandler gameHandler)
   {
      HBox wholeBonus = new HBox();

      ArrayList<BonusCard> bonusCards = gameHandler.getCardsAndTiles().getSelectedBonusCards();

      for (int i = 0; i < bonusCards.size(); i++){
         HBox temp = new HBox();
         temp.getChildren().add(new BonusCardView(bonusCards.get(i)));
         wholeBonus.getChildren().add(temp);
      }
      //wholeBonus.getChildren().addAll(bonusBox1,bonusBox2,bonusBox3,bonusBox4);
      wholeBonus.setPadding(new Insets(100,0,0,50));
      wholeBonus.setMinHeight(800);
      wholeBonus.setMinWidth(1200);
      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      Scene dialogScene = new Scene(wholeBonus, 1100, 600);
      dialog.setScene(dialogScene);
      dialog.setTitle("Bonus Cards ");
      dialog.show();
   }
}
