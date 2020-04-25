package Controller.ActionsControllers;

import Model.CardsAndTiles.FavorTile;
import Model.GameHandler;
import Model.Player;
import View.ActionsViews.SpecialActionView;
import View.CardsAndTilesViews.BonusCardView;
import View.CardsAndTilesViews.FavorTileView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SpecialActionController {

    public void showSpeacialActions(GameHandler gameHandler)
    {
        VBox wholeFavor = new VBox();
        HBox firstRow = new HBox();
        HBox secondRow = new HBox();
        HBox special1 = new HBox();
        HBox special2 = new HBox();

        HBox special3 = new HBox();
        HBox special4 = new HBox();
        Player[] players = gameHandler.getPlayerList();
        int playerId = gameHandler.getCurrentPlayer();
        Player player = players[playerId];
        special1.getChildren().add(new SpecialActionView("Spade Action"));
        special1.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(player.getSpecialActionToken().isCultTack){
                    System.out.println("Spade Action");
                    event.consume();
                }
                else
                    System.err.println("Spade Error");

            }
        });
        special2.getChildren().add(new SpecialActionView("Cult Action"));
        special2.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {

                if(player.getSpecialActionToken().isCultTack){
                    System.out.println("Cult Action");
                    event.consume();
                }
                else
                    System.err.println("Cult Action Error");
            }
        });

        special3.getChildren().add(new SpecialActionView("Stronghold Ability"));
        special3.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(player.getSpecialActionToken().isCultTack){
                    System.out.println("Stronghold Ability");
                    event.consume();
                }
                else
                    System.err.println("Stronghold Ability Error");
            }
        });
        special4.getChildren().add(new SpecialActionView("Faction Ability"));
        special4.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(player.getSpecialActionToken().isCultTack){
                    System.out.println("Faction Ability");
                    event.consume();
                }
                else
                    System.err.println("Faction Ability Error");
            }
        });
        firstRow.getChildren().addAll(special1,special2);
        secondRow.getChildren().addAll(special3,special4);
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
