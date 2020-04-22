package View;

import Model.CardsAndTiles.FavorTile;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class FavorTileView extends HBox {

   FavorTile favorTile;

   /*
   private int islamBonus; //water
    private int christianityBonus; //Air
    private int hinduismBonus; //Fire
    private int jewBonus; //earth
    private int neededCombinedPowerTown;
    private int powerBonus;
    private int workerBonus;
    private int goldBonus;
    private int victoryPoint;
    private boolean isSpecialCult;
    private boolean isTownBonus;
    private boolean isTradingHouse;
    private boolean isDwellingBonus;
    */
   public FavorTileView( FavorTile favorTile)
   {
      this.favorTile = favorTile;
      addReligionToView();
      setStyle("-fx-border-color: red;\n" +
              "-fx-padding: 30;\n" +
              "-fx-border-width: 3;\n" +
              "-fx-border-style: solid;\n");
      if( this.favorTile.getPowerBonus() > 0)
         this.getChildren().add( new Label("Power: " + this.favorTile.getPowerBonus()));
      else if(this.favorTile.getWorkerBonus() > 0)
         this.getChildren().add( new Label("Worker: " + this.favorTile.getWorkerBonus()));
      else if(this.favorTile.getGoldBonus() > 0)
         this.getChildren().add( new Label("Gold: " + this.favorTile.getGoldBonus()));
      else if(this.favorTile.isSpecialCult())
         this.getChildren().add( new Label("Special Cult"));
      else if(this.favorTile.isTradingHouse())
         this.getChildren().add(new Label("Trading - Vict: " + this.favorTile.getVictoryPoint()));
      else if(this.favorTile.isDwellingBonus())
         this.getChildren().add( new Label("Dwelling - Vict: " + this.favorTile.getVictoryPoint()));
      else if(this.favorTile.isTownBonus())
         this.getChildren().add( new Label("Town bonus"));



      setMinHeight(130);
      setMinWidth(220);
      setMargin(this, new Insets(0, 30, 30, 0));
      setSpacing(30);
      setBackground( new Background( new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
   }

   public void addReligionToView()
   {
      VBox religionBox = null;
      if(favorTile.getIslamBonus() > 0)
      {
         religionBox = religionBlock("Islam", favorTile.getIslamBonus());
      }
      else if(favorTile.getChristianityBonus() > 0)
      {
         religionBox = religionBlock("Christianity", favorTile.getChristianityBonus());
      }
      else if(favorTile.getHinduismBonus() > 0)
      {
         religionBox = religionBlock("Hinduism", favorTile.getHinduismBonus());
      }
      else if( favorTile.getJewBonus() > 0)
      {
         religionBox = religionBlock("Jew", favorTile.getJewBonus());
      }
      if(religionBox != null)
         getChildren().add(religionBox);
   }
   public VBox religionBlock(String religionType, int count)
   {
      VBox religionBox = new VBox();
      for( int i = 0; i < count; i ++)
      {
         Label religion = new Label(religionType);
         religionBox.getChildren().add(religion);
      }
      return religionBox;
   }
}
