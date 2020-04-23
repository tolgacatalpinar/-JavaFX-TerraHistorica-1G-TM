package View;

import Model.CardsAndTiles.ScoringTile;
import Model.CardsAndTiles.TownTile;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ScoringTileView extends VBox {



    ScoringTile scoringTile;



    public ScoringTileView( ScoringTile scoringTile)
    {
        this.scoringTile = scoringTile;
        setStyle("-fx-border-color: red;\n" +
                "-fx-padding: 30;\n" +
                "-fx-border-width: 5;\n" +
                "-fx-border-style: solid;\n");


        if(this.scoringTile.isDwellingBonus() )
            this.getChildren().add( new Label("Dwelling - Victory : " + this.scoringTile.getVictoryBonus()));
        if(this.scoringTile.isTradingHouseBonus() )
            this.getChildren().add( new Label("Trade House - Victory : " + this.scoringTile.getVictoryBonus()));
        if(this.scoringTile.isStrongHoldBonus() )
            this.getChildren().add( new Label("Stronghold or Sanctuary - Victory : " + this.scoringTile.getVictoryBonus()));
        if(this.scoringTile.isRequiredTown() )
        this.getChildren().add( new Label("Town Bonus: " + this.scoringTile.getVictoryBonus()));
        if(this.scoringTile.isRequiredSpade() )
            this.getChildren().add( new Label("Spade Bonus : " + this.scoringTile.getVictoryBonus()));

        if(this.scoringTile.isRequiredIslam())
            this.getChildren().add( new Label("Required Islam Points : " + this.scoringTile.getRequiredIslam()));
        if(this.scoringTile.isRequiredBudism())
            this.getChildren().add( new Label("Required Hinduism Points : " + this.scoringTile.getRequiredBudism()));
        if(this.scoringTile.isRequiredChrist())
            this.getChildren().add( new Label("Required Christ Points : " + this.scoringTile.getRequiredChrist()));
        if(this.scoringTile.isRequiredJudaism())
            this.getChildren().add( new Label("Required Judaism Points : " + this.scoringTile.getRequiredJudaism()));


        if( this.scoringTile.getPowerBonus() > 0)
            this.getChildren().add( new Label("Power : " + this.scoringTile.getPowerBonus()));
        if(this.scoringTile.getWorkerBonus() > 0)
            this.getChildren().add( new Label("Worker : " + this.scoringTile.getWorkerBonus()));
        if(this.scoringTile.getPriestBonus() > 0)
            this.getChildren().add( new Label("Priest : " + this.scoringTile.getPriestBonus()));
        if(this.scoringTile.getGoldBonus() > 0)
            this.getChildren().add( new Label("Gold : " + this.scoringTile.getGoldBonus()));
        if(this.scoringTile.getSpadeBonus() > 0)
            this.getChildren().add( new Label("Spade Bonus"));

        setMinHeight(130);
        setMinWidth(220);
        setMargin(this, new Insets(0, 30, 30, 0));
        setSpacing(30);
        setBackground( new Background( new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
