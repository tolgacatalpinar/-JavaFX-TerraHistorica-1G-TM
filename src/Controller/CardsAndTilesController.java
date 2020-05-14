package Controller;

import Controller.GameController;
import Model.CardsAndTiles.*;
import Model.GameHandler;
import Model.Player;
import View.CardsAndTilesViews.BonusCardView;
import View.CardsAndTilesViews.FavorTileView;
import View.CardsAndTilesViews.ScoringTileView;
import View.CardsAndTilesViews.TownTileView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CardsAndTilesController {

    public void showBonusCardsTable(GameController gameController)
    {
        VBox wholeBonus = new VBox();
        HBox firstRow = new HBox();
        HBox secondRow = new HBox();

        ArrayList<BonusCard> bonusCards = gameController.getCardsAndTiles().getSelectedBonusCards();

        for (int i = 0; i < (int) Math.ceil((double) bonusCards.size() / 2); i++){
            firstRow.getChildren().add(new BonusCardView(bonusCards.get(i)));
        }
        for (int i = (int) Math.ceil((double)bonusCards.size() / 2); i < bonusCards.size(); i++){

            secondRow.getChildren().add(new BonusCardView(bonusCards.get(i)));
        }
        wholeBonus.getChildren().addAll(firstRow, secondRow);
        Button select = new Button("select");
        select.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {

            }
        });
        wholeBonus.getChildren().add(select);
        wholeBonus.setPadding(new Insets(100,0,0,50));
        wholeBonus.setMinHeight(800);
        wholeBonus.setMinWidth(1200);
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(wholeBonus, 1100, 600);
        dialog.setScene(dialogScene);
        dialog.setTitle("Bonus Cards ");
        dialog.setResizable(false);
        wholeBonus.setBackground(new Background( new BackgroundImage( new Image("bonus_cards_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        dialog.show();
    }
    public void showFavorTilesTable(GameController gameController)
    {
        VBox wholeFavor = new VBox();
        HBox favorBox1 = new HBox();
        HBox favorBox2 = new HBox();
        HBox favorBox3 = new HBox();

        ArrayList<FavorTile> favorTiles = gameController.getCardsAndTiles().getFavorTiles();
        for( int i = 0; i < (int) Math.ceil((double)favorTiles.size() / 3); i ++)
        {
            favorBox1.getChildren().add(new FavorTileView(favorTiles.get(i)));
        }
        for( int i = (int) Math.ceil((double)favorTiles.size() / 3); i < (int) Math.ceil((double)favorTiles.size() * 2 / 3); i ++)
        {
            favorBox2.getChildren().add(new FavorTileView(favorTiles.get(i)));
        }
        for( int i = (int) Math.ceil((double)favorTiles.size() * 2 / 3); i < (int) Math.ceil(favorTiles.size()); i ++)
        {
            favorBox3.getChildren().add(new FavorTileView(favorTiles.get(i)));
        }
        wholeFavor.getChildren().addAll(favorBox1, favorBox2, favorBox3);
        wholeFavor.setPadding( new Insets(100, 0, 0, 50));


        wholeFavor.setMinHeight(800);
        wholeFavor.setMinWidth(1200);
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(wholeFavor, 1100, 600);
        dialog.setScene(dialogScene);
        dialog.setTitle("Favor Tiles");
        dialog.setResizable(false);
        wholeFavor.setBackground(new Background( new BackgroundImage( new Image("favor_tiles_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        dialog.show();
    }
    public void showScoreTable(GameController gameController)
    {
        Pane emptyPane = new Pane();

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(emptyPane, 1100, 600);
        dialog.setScene(dialogScene);
        dialog.setTitle("Town Tiles");
        dialog.setResizable(false);
        emptyPane.setBackground(new Background( new BackgroundImage( new Image("score_table_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        dialog.show();
    }
    public void showScoringTilesTable(GameController gameController)
    {
        HBox wholeScoring = new HBox();
        VBox first = new VBox();
        VBox second = new VBox();

        ArrayList<ScoringTile> scoringTiles = gameController.getCardsAndTiles().getSelectedScoringTiles();

        for(int i = 0; i < (int) Math.ceil((double)scoringTiles.size() / 2); i++) {
            first.getChildren().add(new ScoringTileView(scoringTiles.get(i)));
        }
        for(int i = (int) Math.ceil((double)scoringTiles.size()/2); i < scoringTiles.size() ; i++) {
            second.getChildren().add(new ScoringTileView(scoringTiles.get(i)));
        }
        wholeScoring.getChildren().addAll(first, second);
        wholeScoring.setPadding( new Insets(100, 0, 0, 50));


        wholeScoring.setMinHeight(800);
        wholeScoring.setMinWidth(1200);
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(wholeScoring, 1100, 600);
        dialog.setScene(dialogScene);
        dialog.setTitle("Scoring Tiles");
        dialog.setResizable(false);
        wholeScoring.setBackground(new Background( new BackgroundImage( new Image("scoring_tiles_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        dialog.show();
    }
    public void showTownTilesTable(GameController gameController)
    {
        VBox wholeTown = new VBox();
        HBox first = new HBox();
        HBox second = new HBox();
        HBox third = new HBox();


        ArrayList<TownTile> townTiles = gameController.getCardsAndTiles().getTownTiles();

        for(int i = 0; i < (int) Math.ceil((double)townTiles.size() / 3); i++) {
            first.getChildren().add(new TownTileView(townTiles.get(i)));
        }
        for(int i = (int) Math.ceil((double)townTiles.size() / 3); i < (int) Math.ceil((double)townTiles.size() * 2 / 3); i++) {
            second.getChildren().add(new TownTileView(townTiles.get(i)));
        }
        for(int i = (int) Math.ceil((double)townTiles.size() * 2 / 3); i < townTiles.size() ; i++) {
            third.getChildren().add(new TownTileView(townTiles.get(i)));
        }
        wholeTown.getChildren().addAll(first, second, third);
        wholeTown.setPadding( new Insets(100, 0, 0, 50));


        wholeTown.setMinHeight(800);
        wholeTown.setMinWidth(1200);
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(wholeTown, 1100, 600);
        dialog.setScene(dialogScene);
        dialog.setTitle("Town Tiles");
        dialog.setResizable(false);
        wholeTown.setBackground(new Background( new BackgroundImage( new Image("town_tiles_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        dialog.show();
    }

}
