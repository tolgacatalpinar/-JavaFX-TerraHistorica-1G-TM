package View.CardsAndTilesViews;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CardView extends HBox {

   private final int DEFAULT_MIN_HEIGHT = 130;
   private final int DEFAULT_MIN_WIDTH = 220;
   private final int DEFAULT_MARGIN_TOP = 0;
   private final int DEFAULT_MARGIN_RIGHT = 30;
   private final int DEFAULT_MARGIN_BOTTOM = 30;
   private final int DEFAULT_MARGIN_LEFT = 0;
   private final int DEFAULT_SPACING = 5;


   public CardView()
   {
      defaultConfigure();
   }

   private void defaultConfigure() {
      setMinHeight(DEFAULT_MIN_HEIGHT);
      setMinWidth(DEFAULT_MIN_WIDTH);

      // Spacing between cards
      VBox.setMargin(this, new Insets(DEFAULT_MARGIN_TOP, DEFAULT_MARGIN_RIGHT, DEFAULT_MARGIN_BOTTOM, DEFAULT_MARGIN_LEFT));
      setSpacing(DEFAULT_SPACING);
      setStyle("-fx-border-color: red;\n" +
              "-fx-padding: 30;\n" +
              "-fx-border-width: 2;\n" +
              "-fx-border-style: solid;\n");
      setBackground( new Background( new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
   }

   public void add(Node pane)
   {
      getChildren().add(pane);
   }
   public void setSize(int height, int width)
   {
      setMinHeight(height);
      setMinWidth(width);
   }
   public void setMargin(Insets insets)
   {
      VBox.setMargin(this, insets);
   }


}
