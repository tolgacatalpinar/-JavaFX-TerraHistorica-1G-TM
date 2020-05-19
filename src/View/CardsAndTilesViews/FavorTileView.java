package View.CardsAndTilesViews;

import Model.CardsAndTiles.FavorTile;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class FavorTileView extends VBox {

   FavorTile favorTile;
   public CardView card;

   public FavorTileView( FavorTile favorTile)
   {
      this.favorTile = favorTile;
      this.card = new CardView();
      addPlayerSlots();
      addReligionToView();
      if( this.favorTile.getPowerBonus() > 0)
         add( new Label("Power: " + this.favorTile.getPowerBonus()));
      if(this.favorTile.getWorkerBonus() > 0)
         add( new Label("Worker: " + this.favorTile.getWorkerBonus()));
      if(this.favorTile.getGoldBonus() > 0)
         add( new Label("Gold: " + this.favorTile.getGoldBonus()));
      if(this.favorTile.isSpecialCult())
         add( new Label("Spec. Cult"));
      if(this.favorTile.isTradingHouse())
         add(new Label("Trade - Vict: " + this.favorTile.getVictoryPoint()));
      if(this.favorTile.isDwellingBonus())
         add( new Label("Dwel - Vict: " + this.favorTile.getVictoryPoint()));
      if(this.favorTile.isTownBonus())
         add( new Label("Town bon."));
      if (this.favorTile.isPassingBonusForTradingHouse())
         add(new Label("Pass Trading Bonus" ));
      getChildren().add(card);
   }
   public void addReligionToView()
   {
      VBox religionBox = null;
      if(favorTile.getIslamBonus() > 0)
      {
         religionBox = religionBlock("Islam: ", favorTile.getIslamBonus());
      }
      else if(favorTile.getChristianityBonus() > 0)
      {
         religionBox = religionBlock("Christ: ", favorTile.getChristianityBonus());
      }
      else if(favorTile.getHinduismBonus() > 0)
      {
         religionBox = religionBlock("Hinduism: ", favorTile.getHinduismBonus());
      }
      else if( favorTile.getJewBonus() > 0)
      {
         religionBox = religionBlock("Jew: ", favorTile.getJewBonus());
      }
      if(religionBox != null)
         add(religionBox);
   }
   public VBox religionBlock(String religionType, int count)
   {
      VBox religionBox = new VBox();
      Label religion = new Label(religionType + count );
      add(religion);
      return religionBox;
   }
   public void add(Node node)
   {
      if( node instanceof Label)
      {
         ((Label)node).setTextFill(Color.WHITE);
      }
      card.add(node);
   }
   public void addPlayerSlots()
   {
      if( favorTile.getJewBonus() > 1 ||favorTile.getHinduismBonus() > 1 ||favorTile.getChristianityBonus() > 1 || favorTile.getIslamBonus() > 1)
      {
         card.addPlayerSlots(1);
      }
      else
      {
         card.addPlayerSlots(3);
      }
   }
}
