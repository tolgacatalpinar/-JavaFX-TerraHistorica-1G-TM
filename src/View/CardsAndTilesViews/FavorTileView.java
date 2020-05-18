package View.CardsAndTilesViews;

import Model.CardsAndTiles.FavorTile;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class FavorTileView extends VBox {

   FavorTile favorTile;
   CardView card;

   public FavorTileView( FavorTile favorTile)
   {
      this.favorTile = favorTile;
      this.card = new CardView();
      addPlayerSlots();
      addReligionToView();
      if( this.favorTile.getPowerBonus() > 0) {
         ImageView powerIm = new ImageView( new Image("power.png"));
         Label powerLabel = new Label("" + this.favorTile.getPowerBonus());
         addResourceBox(powerIm, powerLabel);
      }
      if(this.favorTile.getWorkerBonus() > 0) {
         ImageView powerIm = new ImageView( new Image("worker.png"));
         Label powerLabel = new Label("" + this.favorTile.getWorkerBonus());
         addResourceBox(powerIm, powerLabel);
      }
      if(this.favorTile.getGoldBonus() > 0) {
         ImageView powerIm = new ImageView( new Image("gold.png"));
         Label powerLabel = new Label("" + this.favorTile.getGoldBonus());
         addResourceBox(powerIm, powerLabel);
      }
      if(this.favorTile.isSpecialCult()) {
         add(new Label("Spec. Cult"));
      }
      if(this.favorTile.isTradingHouse()) {
         add(new Label("Trade - Vict: " + this.favorTile.getVictoryPoint()));
      }
      if(this.favorTile.isDwellingBonus()) {
         add(new Label("Dwel - Vict: " + this.favorTile.getVictoryPoint()));
      }
      if(this.favorTile.isTownBonus()) {
         add(new Label("Town bon."));
      }
      if (this.favorTile.isPassingBonusForTradingHouse()) {
         add(new Label("Pass Trading Bonus"));
      }
      getChildren().add(card);
   }
   public void addReligionToView()
   {
      VBox religionBox = null;
      if(favorTile.getIslamBonus() > 0)
      {
         ImageView powerIm = new ImageView( new Image("islam_symbol.png"));
         Label powerLabel = new Label("" + this.favorTile.getIslamBonus());
         addResourceBox(powerIm, powerLabel);
      }
      else if(favorTile.getChristianityBonus() > 0)
      {
         ImageView powerIm = new ImageView( new Image("chris_symbol.png"));
         Label powerLabel = new Label("" + this.favorTile.getChristianityBonus());
         addResourceBox(powerIm, powerLabel);
      }
      else if(favorTile.getHinduismBonus() > 0)
      {
         ImageView powerIm = new ImageView( new Image("hinduism.png"));
         Label powerLabel = new Label("" + this.favorTile.getHinduismBonus());
         addResourceBox(powerIm, powerLabel);
      }
      else if( favorTile.getJewBonus() > 0)
      {
         ImageView powerIm = new ImageView( new Image("judaism_symbol.png"));
         Label powerLabel = new Label("" + this.favorTile.getJewBonus());
         addResourceBox(powerIm, powerLabel);
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
   public void styleLabel(Label label)
   {
      label.setTextFill(Color.WHITE);
   }
   public void styleImageView(ImageView imageView)
   {
      imageView.setFitHeight(30);
      imageView.setFitWidth(30);
   }
   public void addResourceBox(ImageView imageView, Label label)
   {
      HBox powerBox = new HBox();
      powerBox.setAlignment(Pos.CENTER);
      styleLabel( label);
      styleImageView(imageView);
      powerBox.getChildren().addAll(imageView, label);
      add(powerBox);
   }
}
