package View.CardsAndTilesViews;

import Model.CardsAndTiles.ScoringTile;
import Model.CardsAndTiles.TownTile;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ScoringTileView extends VBox {



    ScoringTile scoringTile;
    CardView card;

    public ScoringTileView( ScoringTile scoringTile)
    {
        this.scoringTile = scoringTile;
        card = new CardView();

        if(this.scoringTile.isDwellingBonus() )
            add( new Label("Dwel - Vict: " + this.scoringTile.getVictoryBonus()));
        if(this.scoringTile.isTradingHouseBonus() )
            add( new Label("Trade- Vict: " + this.scoringTile.getVictoryBonus()));
        if(this.scoringTile.isStrongHoldBonus() )
            add( new Label("Strong-Sanct - Vict: " + this.scoringTile.getVictoryBonus()));
        if(this.scoringTile.isRequiredTown() )
            add( new Label("Town B: " + this.scoringTile.getVictoryBonus()));
        if(this.scoringTile.isRequiredSpade() )
            add( new Label("Spade B: " + this.scoringTile.getVictoryBonus()));

        if(this.scoringTile.isRequiredIslam())
            add( new Label("Req Islam P: " + this.scoringTile.getRequiredIslam()));
        if(this.scoringTile.isRequiredBudism())
            add( new Label("Req Hinduism P: " + this.scoringTile.getRequiredBudism()));
        if(this.scoringTile.isRequiredChrist())
            add( new Label("Req Christ P: " + this.scoringTile.getRequiredChrist()));
        if(this.scoringTile.isRequiredJudaism())
            add( new Label("Req Judaism P: " + this.scoringTile.getRequiredJudaism()));


        if( this.scoringTile.getPowerBonus() > 0)
            add( new Label("Power: " + this.scoringTile.getPowerBonus()));
        if(this.scoringTile.getWorkerBonus() > 0)
            add( new Label("Worker: " + this.scoringTile.getWorkerBonus()));
        if(this.scoringTile.getPriestBonus() > 0)
            add( new Label("Priest: " + this.scoringTile.getPriestBonus()));
        if(this.scoringTile.getGoldBonus() > 0)
            add( new Label("Gold: " + this.scoringTile.getGoldBonus()));
        if(this.scoringTile.getSpadeBonus() > 0)
            add( new Label("Spade B"));
        getChildren().add(card);
    }
    public void add( Node node)
    {
       if( node instanceof Label)
       {
          ((Label)node).setTextFill(Color.WHITE);
       }
       card.add(node);
    }
}
