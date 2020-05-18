package View.CardsAndTilesViews;

import Model.CardsAndTiles.BonusCard;
import Model.CardsAndTiles.FavorTile;
import javafx.geometry.Insets;
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
        if( this.bonusCard.getShippingRange() > 0)
            add( new Label("Shipping Range: " + "+" + this.bonusCard.getShippingRange()));
        if( this.bonusCard.getNotTakenBonus() > 0)
            add( new Label("Extra Bonus Gold : " + this.bonusCard.getNotTakenBonus()));
        if( this.bonusCard.getPowerBonus() > 0)
            add( new Label("Power : " + this.bonusCard.getPowerBonus()));
        if(this.bonusCard.getWorkerBonus() > 0)
            add( new Label("Worker : " + this.bonusCard.getWorkerBonus()));
        if(this.bonusCard.getPriestBonus() > 0)
            add( new Label("Priest : " + this.bonusCard.getPriestBonus()));
        if(this.bonusCard.getGoldBonus() > 0)
            add( new Label("Gold : " + this.bonusCard.getGoldBonus()));
        if(this.bonusCard.isSpecialCult())
            add( new Label("Special Cult"));
        if(this.bonusCard.isSpacialSpade())
            add( new Label("Special Spade"));
        if(this.bonusCard.isTradeHouse())
            add(new Label("Trading - Vict : " + 2 ));
        if(this.bonusCard.isDwelling())
            add( new Label("Dwelling - Vict : " + 1));
        if(this.bonusCard.isSanctuary())
            add( new Label("StrongHold or Sanctuary - Vict : " + 4));
        getChildren().add(card);
    }
    public void add(Node node)
    {
       if( node instanceof Label)
       {
          ((Label)node).setTextFill(Color.WHITE);
       }
       card.getChildren().add(node);
    }
}
