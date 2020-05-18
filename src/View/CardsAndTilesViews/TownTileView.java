package View.CardsAndTilesViews;

import Model.CardsAndTiles.TownTile;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TownTileView extends VBox {
    TownTile townTile;
    CardView card;


    public TownTileView( TownTile townTile)
    {
        this.townTile = townTile;
        card = new CardView();
        card.setSize(40, card.getDEFAULT_MIN_WIDTH() + 25);
        card.addPlayerSlots(1);
        if( this.townTile.getPowerBonus() > 0) {
           ImageView powerIm = new ImageView( new Image("power.png"));
           Label powerLabel = new Label("" + this.townTile.getPowerBonus());
           addResourceBox(powerIm, powerLabel);
        }
        if(this.townTile.getWorkerBonus() > 0) {
            ImageView powerIm = new ImageView( new Image("worker.png"));
            Label powerLabel = new Label("" + this.townTile.getWorkerBonus());
            addResourceBox(powerIm, powerLabel);
        }
        if(this.townTile.getPriestBonus() > 0) {
            ImageView powerIm = new ImageView( new Image("priest.png"));
            Label powerLabel = new Label("" + this.townTile.getPriestBonus());
            addResourceBox(powerIm, powerLabel);
        }
        if(this.townTile.getGoldBonus() > 0) {
            ImageView powerIm = new ImageView( new Image("gold.png"));
            Label powerLabel = new Label("" + this.townTile.getGoldBonus());
            addResourceBox(powerIm, powerLabel);
        }
        if(this.townTile.getVictoryBonus() > 0) {
            ImageView powerIm = new ImageView( new Image("victory_point.png"));
            Label powerLabel = new Label("" + this.townTile.getVictoryBonus());
            addResourceBox(powerIm, powerLabel);
        }
       if(this.townTile.getChristianityPoint() > 0) {

           ImageView powerIm = new ImageView( new Image("chris_symbol.png"));
           Label powerLabel = new Label("" + this.townTile.getChristianityPoint());
           addResourceBox(powerIm, powerLabel);
       }
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
      styleLabel( label);
      styleImageView(imageView);
      powerBox.getChildren().addAll(imageView, label);
      add(powerBox);
   }
}
