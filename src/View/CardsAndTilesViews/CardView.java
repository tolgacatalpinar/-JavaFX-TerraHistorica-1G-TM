package View.CardsAndTilesViews;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CardView extends VBox {

   private final int DEFAULT_MIN_HEIGHT = 130;
   private final int DEFAULT_MIN_WIDTH = 220;
   private final int DEFAULT_MARGIN_TOP = 0;
   private final int DEFAULT_MARGIN_RIGHT = 30;
   private final int DEFAULT_MARGIN_BOTTOM = 30;
   private final int DEFAULT_MARGIN_LEFT = 0;
   private final int DEFAULT_SPACING = 5;
   private final int DEFAULT_PLAYER_SLOT_RADIUS = 40;
   private VBox playerSlots;
   public CardView()
   {
      defaultConfigure();
   }
   private void defaultConfigure() {
      setMinHeight(DEFAULT_MIN_HEIGHT);
      setMinWidth(DEFAULT_MIN_WIDTH);
      VBox.setMargin(this, new Insets(DEFAULT_MARGIN_TOP, DEFAULT_MARGIN_RIGHT, DEFAULT_MARGIN_BOTTOM, DEFAULT_MARGIN_LEFT));
      setSpacing(DEFAULT_SPACING);
      setStyle("-fx-border-color: red;\n" +
              "-fx-padding: 25 0 0 5;\n" +
              "-fx-border-width: 2;\n" +
              "-fx-border-style: solid;\n");
      setBackground(new Background( new BackgroundImage( new Image("card_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
              BackgroundSize.DEFAULT)));
   }
   public void add(Node pane,int index)
   {
      getChildren().add(index,pane);
   }
   public void add(Node pane){
      getChildren().add(pane);
   }
   public void addPlayerSlots( int slotNumber)
   {
      VBox slots = new VBox();
      for( int i = 0; i < slotNumber; i ++)
      {
         StackPane stack = new StackPane();
         stack.getChildren().add( new ImageView(new Image("player_slot.png")));
         slots.getChildren().add(stack);
      }
      add(slots);
      playerSlots = slots;

   }
   public void addPlayerToSlot( int index, ImageView image)
   {
      image.setFitHeight(DEFAULT_PLAYER_SLOT_RADIUS);
      image.setFitWidth(DEFAULT_PLAYER_SLOT_RADIUS);
      ((StackPane)playerSlots.getChildren().get(index)).getChildren().add(0, image);
   }
   public void setSize(int height, int width)
   {
      setMinHeight(height);
      setMinWidth(width);
   }
   public int getDEFAULT_MIN_WIDTH() {
      return DEFAULT_MIN_WIDTH;
   }

}
