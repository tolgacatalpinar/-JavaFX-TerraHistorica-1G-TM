package Controller;

import Controller.GameController;
import Model.CardsAndTiles.*;
import Model.GameHandler;
import Model.Player;
import Model.Religion;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CardsAndTilesController {
    private  int selectionBonus = -1;

    public  int getSelectionFavorTile() {
        return selectionFavorTile;
    }

    public  void setSelectionFavorTile(int selectionFavorTile) {this.selectionFavorTile = selectionFavorTile;
    }

    private  int selectionFavorTile = -1;
    public void showBonusCardsTable(CardsAndTiles cardsAndTiles,Player current)
    {
        selectionBonus = -1;
        ArrayList<BonusCard> bonusCards = cardsAndTiles.getSelectedBonusCards();
        BorderPane border = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Button select = new Button("Select");
        select.setMaxHeight(100);
        select.setMinWidth(100);
        BorderPane border_bottom = new BorderPane();
        border.setBottom(border_bottom);
        border_bottom.setCenter(select);
        final Stage dialog = new Stage();
        border.setCenter(gridPane);
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(border, 1100, 600);
        dialog.setResizable(false);
        border.setBackground(new Background( new BackgroundImage( new Image("bonus_cards_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        dialog.setScene(dialogScene);
        dialog.setTitle("Bonus Cards ");

        for (int i = 0; i < bonusCards.size(); i++) {
            GridPane tempPane = new GridPane();
            tempPane.getChildren().add(new BonusCardView(bonusCards.get(i)));
            tempPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    DropShadow borderGlow = new DropShadow();
                    borderGlow.setColor(Color.BLUE);
                    borderGlow.setOffsetX(0f);
                    borderGlow.setOffsetY(0f);
                    borderGlow.setWidth(50);
                    borderGlow.setHeight(50);
                    tempPane.setEffect(borderGlow);
                }
            });
            int finalI = i;
            tempPane.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (selectionBonus != finalI)
                        tempPane.setEffect(null);
                }
            });
            tempPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setSelectionBonus(finalI);
                    for (int i = 0; i < bonusCards.size(); i++) {
                        if (i != getSelectionBonus())
                            gridPane.getChildren().get(i).setEffect(null);
                    }
                }
            });
            if(bonusCards.get(i).isPlayerOcupied()){
                    DropShadow borderGlow = new DropShadow();
                    borderGlow.setColor(Color.RED);
                    borderGlow.setOffsetX(0f);
                    borderGlow.setOffsetY(0f);
                    borderGlow.setWidth(50);
                    borderGlow.setHeight(50);
                    tempPane.setEffect(borderGlow);
                    tempPane.setDisable(true);
            }
            gridPane.add(tempPane, i % 3, i / 3);
        }
        select.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int chosen = getSelectionBonus();
                System.out.println("Selected " + chosen);
                cardsAndTiles.playerChoseBonusCard(cardsAndTiles.selectedBonusCards.get(selectionBonus),current);
                dialog.close();
            }
        });

        dialog.show();


    }
    public  int getSelectionBonus() {
        return selectionBonus;
    }
    public  void setSelectionBonus(int i) {
        selectionBonus = i;
    }
    public  int showFavorTilesTable(CardsAndTiles cardsAndTiles, Player current, Religion[] religions)
    {
        setSelectionFavorTile(-1);
        ArrayList<FavorTile> favorTiles = cardsAndTiles.getFavorTiles();
        BorderPane border = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Button select = new Button("Select");
        select.setMaxHeight(100);
        select.setMinWidth(100);
        BorderPane border_bottom = new BorderPane();
        border.setBottom(border_bottom);
        border_bottom.setCenter(select);
        final Stage dialog = new Stage();
        border.setCenter(gridPane);
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(border, 1100, 600);
        dialog.setResizable(false);
        border.setBackground(new Background( new BackgroundImage( new Image("bonus_cards_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        dialog.setScene(dialogScene);
        dialog.setTitle("Favor Tiles");
        border.setBackground(new Background( new BackgroundImage( new Image("favor_tiles_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        for (int i = 0; i < favorTiles.size(); i++) {
            GridPane tempPane = new GridPane();
            tempPane.getChildren().add(new FavorTileView(favorTiles.get(i)));
            tempPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    DropShadow borderGlow = new DropShadow();
                    borderGlow.setColor(Color.BLUE);
                    borderGlow.setOffsetX(0f);
                    borderGlow.setOffsetY(0f);
                    borderGlow.setWidth(50);
                    borderGlow.setHeight(50);
                    tempPane.setEffect(borderGlow);
                }
            });
            int finalI = i;
            tempPane.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (selectionFavorTile != finalI)
                        tempPane.setEffect(null);
                }
            });
            tempPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setSelectionFavorTile(finalI);
                    for (int i = 0; i < favorTiles.size(); i++) {
                        if (i != getSelectionFavorTile())
                            gridPane.getChildren().get(i).setEffect(null);
                    }
                }
            });
            if(favorTiles.get(i).getPlayerIds().size()>= favorTiles.get(i).getNumberOfPlayer()){
                DropShadow borderGlow = new DropShadow();
                borderGlow.setColor(Color.RED);
                borderGlow.setOffsetX(0f);
                borderGlow.setOffsetY(0f);
                borderGlow.setWidth(50);
                borderGlow.setHeight(50);
                tempPane.setEffect(borderGlow);
                tempPane.setDisable(true);
            }
            gridPane.add(tempPane, i % 4, i / 4);
        }
        select.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int chosen = getSelectionFavorTile();
                System.out.println("Selected " + chosen);
                if(cardsAndTiles.favorTiles.get(selectionFavorTile).getIslamBonus() != 0){
                    cardsAndTiles.playerChooseFavorTile(cardsAndTiles.favorTiles.get(selectionFavorTile),current,religions[0]);
                    = religions[0].updateReligion(cardsAndTiles.favorTiles.get(selectionFavorTile).getIslamBonus(),current.getPlayerId(),current.getKey());
                }else if(cardsAndTiles.favorTiles.get(selectionFavorTile).getChristianityBonus() != 0){
                    cardsAndTiles.playerChooseFavorTile(cardsAndTiles.favorTiles.get(selectionFavorTile),current,religions[1]);
                }else if(cardsAndTiles.favorTiles.get(selectionFavorTile).getHinduismBonus() != 0){
                    cardsAndTiles.playerChooseFavorTile(cardsAndTiles.favorTiles.get(selectionFavorTile),current,religions[2]);
                }
                else if(cardsAndTiles.favorTiles.get(selectionFavorTile).getJewBonus() != 0){
                    cardsAndTiles.playerChooseFavorTile(cardsAndTiles.favorTiles.get(selectionFavorTile),current,religions[3]);
                }
                dialog.close();
            }
        });
        dialog.showAndWait();
        return getSelectionFavorTile();

    }
    public void showScoreTable()
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
    public void showScoringTilesTable(CardsAndTiles cardsAndTiles)
    {
        ArrayList<ScoringTile> scoringTiles = cardsAndTiles.getSelectedScoringTiles();
        BorderPane border = new BorderPane();
        GridPane gridPane = new GridPane();
        BorderPane border_bottom = new BorderPane();
        border.setBottom(border_bottom);
        final Stage dialog = new Stage();
        border.setCenter(gridPane);
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(border, 1100, 600);
        dialog.setResizable(false);
        border.setBackground(new Background( new BackgroundImage( new Image("scoring_tiles_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        dialog.setScene(dialogScene);
        dialog.setTitle("Scoring Tile");
        dialog.show();
        for (int i = 0; i < scoringTiles.size(); i++) {
            GridPane tempPane = new GridPane();
            tempPane.getChildren().add(new ScoringTileView(scoringTiles.get(i)));
            gridPane.add(tempPane, i % 2, i / 2);
        }
        dialog.show();

    }

    public void showTownTilesTable(CardsAndTiles cardsAndTiles)
    {
        VBox wholeTown = new VBox();
        HBox first = new HBox();
        HBox second = new HBox();
        HBox third = new HBox();


        ArrayList<TownTile> townTiles = cardsAndTiles.getTownTiles();

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
