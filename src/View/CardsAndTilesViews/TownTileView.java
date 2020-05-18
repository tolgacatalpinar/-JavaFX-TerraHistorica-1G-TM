package View.CardsAndTilesViews;

import Model.CardsAndTiles.BonusCard;
import Model.CardsAndTiles.TownTile;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
        card.setSize(40, card.getDEFAULT_MIN_WIDTH() + 25);
        card.addPlayerSlots(1);
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
          add( new Label("All Religions: +" + this.townTile.getChristianityPoint()));
        getChildren().add(card);
    }
    public void add(Node node)
    {
       if( node instanceof Label)
       {
          ((Label)node).setTextFill(Color.WHITE);
       }
       card.add( node);
    }
   public void addPlayerToSlot(ImageView playerView)
   {
      card.addPlayerToSlot(0, playerView);
   }
}
