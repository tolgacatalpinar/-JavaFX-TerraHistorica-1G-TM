package Controller.CardsAndTilesControllers;

import Model.CardsAndTiles.BonusCard;
import Model.GameHandler;
import View.CardsAndTilesViews.BonusCardView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BonusCardsController {

   public void showBonusCardsTable(GameHandler gameHandler)
   {
      VBox wholeBonus = new VBox();
      HBox firstRow = new HBox();
      HBox secondRow = new HBox();

      ArrayList<BonusCard> bonusCards = gameHandler.getCardsAndTiles().getSelectedBonusCards();

      for (int i = 0; i < (int) Math.ceil((double) bonusCards.size() / 2); i++){

         System.out.println("The number: " + (int) Math.ceil(bonusCards.size() / 2));
         firstRow.getChildren().add(new BonusCardView(bonusCards.get(i)));
      }
      for (int i = (int) Math.ceil((double)bonusCards.size() / 2); i < bonusCards.size(); i++){

         secondRow.getChildren().add(new BonusCardView(bonusCards.get(i)));
      }
      wholeBonus.getChildren().addAll(firstRow, secondRow);
      wholeBonus.setPadding(new Insets(100,0,0,50));
      wholeBonus.setMinHeight(800);
      wholeBonus.setMinWidth(1200);
      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      Scene dialogScene = new Scene(wholeBonus, 1100, 600);
      dialog.setScene(dialogScene);
      dialog.setTitle("Bonus Cards ");
      wholeBonus.setBackground(new Background( new BackgroundImage( new Image("bonus_cards_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
              BackgroundSize.DEFAULT)));
      dialog.show();
   }
}
