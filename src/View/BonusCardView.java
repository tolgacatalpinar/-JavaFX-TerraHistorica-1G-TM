package View;

import Model.CardsAndTiles.BonusCard;
import Model.CardsAndTiles.FavorTile;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BonusCardView extends HBox {

    BonusCard bonusCard;


    public BonusCardView( BonusCard bonusCard)
    {
        this.bonusCard = bonusCard;
        setStyle("-fx-border-color: red;\n" +
                "-fx-padding: 30;\n" +
                "-fx-border-width: 5;\n" +
                "-fx-border-style: solid;\n");
        if( this.bonusCard.getShippingRange() > 0)
            this.getChildren().add( new Label("Shipping Range: " + "+" + this.bonusCard.getShippingRange()));
        if( this.bonusCard.getNotTakenBonus() > 0)
            this.getChildren().add( new Label("Extra Bonus Gold : " + this.bonusCard.getNotTakenBonus()));
        if( this.bonusCard.getPowerBonus() > 0)
            this.getChildren().add( new Label("Power : " + this.bonusCard.getPowerBonus()));
        if(this.bonusCard.getWorkerBonus() > 0)
            this.getChildren().add( new Label("Worker : " + this.bonusCard.getWorkerBonus()));
        if(this.bonusCard.getPriestBonus() > 0)
            this.getChildren().add( new Label("Priest : " + this.bonusCard.getPriestBonus()));
        if(this.bonusCard.getGoldBonus() > 0)
            this.getChildren().add( new Label("Gold : " + this.bonusCard.getGoldBonus()));
        if(this.bonusCard.isSpecialCult())
            this.getChildren().add( new Label("Special Cult"));
        if(this.bonusCard.isSpacialSpade())
            this.getChildren().add( new Label("Special Spade"));
        if(this.bonusCard.isTradeHouse())
            this.getChildren().add(new Label("Trading - Vict : " + 2 ));
        if(this.bonusCard.isDwelling())
            this.getChildren().add( new Label("Dwelling - Vict : " + 1));
        if(this.bonusCard.isSanctuary())
            this.getChildren().add( new Label("StrongHold or Sanctuary - Vict : " + 4));

        setMinHeight(130);
        setMinWidth(220);
        setMargin(this, new Insets(0, 30, 30, 0));
        setSpacing(30);
        setBackground( new Background( new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
