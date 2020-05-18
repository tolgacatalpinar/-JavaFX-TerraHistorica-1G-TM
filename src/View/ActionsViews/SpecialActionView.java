package View.ActionsViews;

import View.CardsAndTilesViews.CardView;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SpecialActionView extends VBox {

    CardView card;
    public SpecialActionView( String title)
    {
        card = new CardView();
        card.addPlayerSlots(1);
        add( new Label(title));
        getChildren().add(card);
    }
    public void add(Node node)
    {
        if( node instanceof Label)
        {
            ((Label)node).setTextFill(Color.WHITE);
        }
        card.getChildren().add(node);
    }
}
