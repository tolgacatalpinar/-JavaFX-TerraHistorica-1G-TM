package View.CardsAndTilesViews;

import Model.CardsAndTiles.BonusCard;
import Model.CardsAndTiles.TownTile;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;

public class TownTileView extends VBox {
    TownTile townTile;
    CardView card;


    public TownTileView( TownTile townTile)
    {
        this.townTile = townTile;
        card = new CardView();

        if( this.townTile.getPowerBonus() > 0)
            add( new Label("Power : " + this.townTile.getPowerBonus()));
        if(this.townTile.getWorkerBonus() > 0)
            add( new Label("Worker : " + this.townTile.getWorkerBonus()));
        if(this.townTile.getPriestBonus() > 0)
            add( new Label("Priest : " + this.townTile.getPriestBonus()));
        if(this.townTile.getGoldBonus() > 0)
            add( new Label("Gold : " + this.townTile.getGoldBonus()));
        if(this.townTile.getVictoryBonus() > 0)
            add( new Label("Victory Points : " + this.townTile.getVictoryBonus()));
        if(this.townTile.getChristianityPoint() > 0)
            add( new Label("Christianity Points : " + this.townTile.getChristianityPoint()));
        if(this.townTile.getHinduismPoint() > 0)
            add( new Label("Hinduism Points : " + this.townTile.getHinduismPoint()));
        if(this.townTile.getIslamPoint() > 0)
            add( new Label("ıslam Points : " + this.townTile.getIslamPoint()));
        if(this.townTile.getJewishPoint() > 0)
            add( new Label("Jewish Points : " + this.townTile.getJewishPoint()));
        getChildren().add(card);
    }

    public void add(Node node)
    {
       card.add( node);
    }
}
