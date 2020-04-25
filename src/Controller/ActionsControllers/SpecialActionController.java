package Controller.ActionsControllers;

import Model.CardsAndTiles.FavorTile;
import Model.GameHandler;
import View.ActionsViews.SpecialActionView;
import View.CardsAndTilesViews.BonusCardView;
import View.CardsAndTilesViews.FavorTileView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SpecialActionController {

    public boolean isSpade;
    public boolean isCultTack;
    public boolean isStrongholdAbility;
    public boolean isFactionAbility;
    public void showSpeacialActions(GameHandler gameHandler)
    {
        VBox wholeFavor = new VBox();
        HBox firstRow = new HBox();
        HBox secondRow = new HBox();

        firstRow.getChildren().add(new SpecialActionView("Spade Action"));
        firstRow.getChildren().add(new SpecialActionView("Cult Action"));


        secondRow.getChildren().add(new SpecialActionView("Stronghold Ability"));
        secondRow.getChildren().add(new SpecialActionView("Faction Ability"));


        wholeFavor.getChildren().addAll(firstRow, secondRow);
        wholeFavor.setPadding( new Insets(100, 0, 0, 50));

        wholeFavor.setMinHeight(800);
        wholeFavor.setMinWidth(1200);
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(wholeFavor, 1100, 600);
        dialog.setScene(dialogScene);
        dialog.setTitle("Special Actions");
        dialog.setResizable(false);
        wholeFavor.setBackground(new Background( new BackgroundImage( new Image("favor_tiles_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        dialog.show();
    }
}
