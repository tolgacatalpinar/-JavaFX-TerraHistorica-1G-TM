package View.CardsAndTilesViews;

import Model.CardsAndTiles.BonusCard;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BonusCardView extends VBox {
    BonusCard bonusCard;
    CardView card;
    public BonusCardView( BonusCard bonusCard)
    {
        this.bonusCard = bonusCard;
        card = new CardView();
        card.addPlayerSlots(1);
        if( this.bonusCard.getShippingRange() > 0) {
            ImageView powerIm = new ImageView( new Image("shipping.png"));
            Label powerLabel = new Label("" + this.bonusCard.getShippingRange());
            addResourceBox(powerIm, powerLabel);
        }
        if( this.bonusCard.getNotTakenBonus() > 0) {

            add(new Label("Extra Bonus Gold : " + this.bonusCard.getNotTakenBonus()));
        }
        if( this.bonusCard.getPowerBonus() > 0) {
            ImageView powerIm = new ImageView( new Image("power.png"));
            Label powerLabel = new Label("" + this.bonusCard.getPowerBonus());
            addResourceBox(powerIm, powerLabel);
        }
        if(this.bonusCard.getWorkerBonus() > 0) {
            ImageView powerIm = new ImageView( new Image("worker.png"));
            Label powerLabel = new Label("" + this.bonusCard.getWorkerBonus());
            addResourceBox(powerIm, powerLabel);
        }
        if(this.bonusCard.getPriestBonus() > 0) {
            ImageView powerIm = new ImageView( new Image("priest.png"));
            Label powerLabel = new Label("" + this.bonusCard.getPriestBonus());
            addResourceBox(powerIm, powerLabel);
        }
        if(this.bonusCard.getGoldBonus() > 0) {
            ImageView powerIm = new ImageView( new Image("gold.png"));
            Label powerLabel = new Label("" + this.bonusCard.getGoldBonus());
            addResourceBox(powerIm, powerLabel);
        }
        if(this.bonusCard.isSpecialCult()) {
            add(new Label("Special Cult"));
        }
        if(this.bonusCard.isSpacialSpade()) {
            add(new Label("Special Spade"));
        }
        if(this.bonusCard.isTradeHouse()) {
            add(new Label("Trading - Vict : " + 2));
        }
        if(this.bonusCard.isDwelling()) {
            add(new Label("Dwelling - Vict : " + 1));
        }
        if(this.bonusCard.isSanctuary()) {
            add(new Label("StrongHold or Sanctuary - Vict : " + 4));
        }
        getChildren().add(card);
    }
    public void add(Node node)
    {
       if( node instanceof Label)
       {
          ((Label)node).setTextFill(Color.WHITE);
          ((Label) node).setAlignment(Pos.CENTER);
       }
       card.getChildren().add(node);
    }
   public void styleLabel(Label label)
   {
      label.setTextFill(Color.WHITE);
   }
   public void styleImageView(ImageView imageView)
   {
      imageView.setFitHeight(30);
      imageView.setFitWidth(30);
   }
   public void addResourceBox(ImageView imageView, Label label)
   {
      HBox powerBox = new HBox();
      powerBox.setAlignment(Pos.CENTER);
      styleLabel( label);
      styleImageView(imageView);
      powerBox.getChildren().addAll(imageView, label);
      add(powerBox);
   }


}
