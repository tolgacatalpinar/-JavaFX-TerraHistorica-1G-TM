package View.CardsAndTilesViews;

import Model.CardsAndTiles.ScoringTile;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ScoringTileView extends VBox {
    ScoringTile scoringTile;
    CardView card;
    public ScoringTileView( ScoringTile scoringTile,int index)
    {
        this.scoringTile = scoringTile;
        card = new CardView();
            add(new Label(""+(index + 1) + "\n"));
        if(this.scoringTile.isDwellingBonus() )
            add( new Label("Gain "+  + this.scoringTile.getVictoryBonus()+ " Victory Points per Dwelling"));
        if(this.scoringTile.isTradingHouseBonus() )
            add( new Label("Gain "+  + this.scoringTile.getVictoryBonus()+ " Victory Points per Trading Post"));
        if(this.scoringTile.isStrongHoldBonus() )
            add( new Label("Gain "+  + this.scoringTile.getVictoryBonus()+ " Victory Points per Stronghold or Sanctuary"));
        if(this.scoringTile.isRequiredTown() )
            add( new Label("Gain "+  + this.scoringTile.getVictoryBonus()+ " Victory Points per founded town"));
        if(this.scoringTile.isRequiredSpade() )
            add( new Label("Gain "+  + this.scoringTile.getVictoryBonus()+ " Victory Points per upgraded spade"));
        if(this.scoringTile.isRequiredIslam())
            add( new Label("Required Islam Point(s): " + this.scoringTile.getRequiredIslam()));
        if(this.scoringTile.isRequiredBudism())
            add( new Label("Required Buddhism Point(s): " + this.scoringTile.getRequiredBudism()));
        if(this.scoringTile.isRequiredChrist())
            add( new Label("Required Christianity Point(s): " + this.scoringTile.getRequiredChrist()));
        if(this.scoringTile.isRequiredJudaism())
            add( new Label("Required Judaism Point(s): " + this.scoringTile.getRequiredJudaism()));
        if( this.scoringTile.getPowerBonus() > 0)
            add( new Label("Power Bonus you will gain: " + this.scoringTile.getPowerBonus()));
        if(this.scoringTile.getWorkerBonus() > 0)
            add( new Label("Worker Bonus you will gain: " + this.scoringTile.getWorkerBonus()));
        if(this.scoringTile.getPriestBonus() > 0)
            add( new Label("Priest Bonus you will gain: " + this.scoringTile.getPriestBonus()));
        if(this.scoringTile.getGoldBonus() > 0)
            add( new Label("Gold Bonus you will gain: " + this.scoringTile.getGoldBonus()));
        if(this.scoringTile.getSpadeBonus() > 0)
            add( new Label("Spade you will gain:"));
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
