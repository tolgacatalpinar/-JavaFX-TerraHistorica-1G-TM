package View.ActionsViews;

import Model.CardsAndTiles.BonusCard;
import View.CardsAndTilesViews.CardView;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SpecialActionView extends VBox {

    CardView card;
    public SpecialActionView( String title)
    {
        card = new CardView();

        card.addPlayerSlots(1);
        add( new Label(title));


        //card.addPlayerToSlot(0, new ImageView( new Image("Image_Gilgamesh.jpeg")));
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
    public void addPlayerToSlot(ImageView playerView)
    {
        card.addPlayerToSlot(0, playerView);
    }
}
