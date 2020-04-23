package View;

import Model.CardsAndTiles.BonusCard;
import Model.CardsAndTiles.TownTile;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class TownTileView extends HBox {
    TownTile townTile;



    public TownTileView( TownTile townTile)
    {
        this.townTile = townTile;
        setStyle("-fx-border-color: red;\n" +
                "-fx-padding: 30;\n" +
                "-fx-border-width: 5;\n" +
                "-fx-border-style: solid;\n");

        if( this.townTile.getPowerBonus() > 0)
            this.getChildren().add( new Label("Power : " + this.townTile.getPowerBonus()));
        if(this.townTile.getWorkerBonus() > 0)
            this.getChildren().add( new Label("Worker : " + this.townTile.getWorkerBonus()));
        if(this.townTile.getPriestBonus() > 0)
            this.getChildren().add( new Label("Priest : " + this.townTile.getPriestBonus()));
        if(this.townTile.getGoldBonus() > 0)
            this.getChildren().add( new Label("Gold : " + this.townTile.getGoldBonus()));
        if(this.townTile.getVictoryBonus() > 0)
            this.getChildren().add( new Label("Victory Points : " + this.townTile.getVictoryBonus()));
        if(this.townTile.getChristianityPoint() > 0)
            this.getChildren().add( new Label("Christianity Points : " + this.townTile.getChristianityPoint()));
        if(this.townTile.getHinduismPoint() > 0)
            this.getChildren().add( new Label("Hinduism Points : " + this.townTile.getHinduismPoint()));
        if(this.townTile.getIslamPoint() > 0)
            this.getChildren().add( new Label("ıslam Points : " + this.townTile.getIslamPoint()));
        if(this.townTile.getJewishPoint() > 0)
            this.getChildren().add( new Label("Jewish Points : " + this.townTile.getJewishPoint()));

        setMinHeight(130);
        setMinWidth(220);
        setMargin(this, new Insets(0, 30, 30, 0));
        setSpacing(30);
        setBackground( new Background( new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
