package Controller;

import Model.CardsAndTiles.*;
import Model.Player;
import Model.Religion;
import View.CardsAndTilesViews.BonusCardView;
import View.CardsAndTilesViews.FavorTileView;
import View.CardsAndTilesViews.ScoringTileView;
import View.CardsAndTilesViews.TownTileView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
    private  int selectionFavorTile = -1;
    private int selectionTown = -1;
    public void showBonusCardsTable(CardsAndTiles cardsAndTiles,Player current,boolean check)
    {
        selectionBonus = -1;
        ArrayList<BonusCard> bonusCards = cardsAndTiles.getSelectedBonusCards();
        BorderPane border = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        BorderPane border_bottom = new BorderPane();
        border.setBottom(border_bottom);
        final Stage dialog = new Stage();
        border.setCenter(gridPane);
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(border, 1100, 600);
        dialog.setResizable(false);
        border.setBackground(new Background( new BackgroundImage( new Image("the_background_1.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false))));
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
        if(check){
            Button select = new Button("Select");
            select.setMaxHeight(100);
            select.setMinWidth(100);
            border_bottom.setCenter(select);
            select.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int chosen = getSelectionBonus();
                    System.out.println("Selected " + chosen);
                    cardsAndTiles.playerChoseBonusCard(cardsAndTiles.selectedBonusCards.get(selectionBonus),current);
                    dialog.close();
                }
            });
        }
        dialog.show();
    }
    public  int showFavorTilesTable(CardsAndTiles cardsAndTiles, Player current, Religion[] religions,boolean check,Player[] arr)
    {
        setSelectionFavorTile(-1);
        ArrayList<FavorTile> favorTiles = cardsAndTiles.getFavorTiles();
        BorderPane border = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        BorderPane border_bottom = new BorderPane();
        border.setBottom(border_bottom);

        final Stage dialog = new Stage();
        border.setCenter(gridPane);
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(border, 1100, 700);
        dialog.setResizable(false);
        dialog.setScene(dialogScene);
        dialog.setTitle("Favor Tiles");
        border.setBackground(new Background( new BackgroundImage( new Image("the_background_8.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false))));

        for (int i = 0; i < favorTiles.size(); i++) {
            GridPane tempPane = new GridPane();
            tempPane.getChildren().add(new FavorTileView(favorTiles.get(i)));
            ReligionController religionController = new ReligionController();
            if(favorTiles.get(i).getPlayerIds() != null){
                for(int j = 0; j < favorTiles.get(i).getPlayerIds().size();i++){
                    String image_url = religionController.getImage(arr[favorTiles.get(i).getPlayerIds().get(j)]);
                    ImageView player_image = new ImageView(image_url);
                    ((FavorTileView) tempPane.getChildren().get(0)).card.addPlayerToSlot(0,player_image);
                }
            }

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
        if(check){
            Button select = new Button("Select");
            select.setMaxHeight(100);
            select.setMinWidth(100);
            border_bottom.setCenter(select);
            select.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int chosen = getSelectionFavorTile();
                    int[] returnInfo = {0,0,0};
                    int religion_index = -1;
                    if(cardsAndTiles.favorTiles.get(selectionFavorTile).getIslamBonus() != 0){
                        religion_index = 0;
                        cardsAndTiles.playerChooseFavorTile(cardsAndTiles.favorTiles.get(selectionFavorTile),current,religions[0]);
                        returnInfo=religions[0].updateReligion(cardsAndTiles.favorTiles.get(selectionFavorTile).getIslamBonus(),current.getPlayerId(),current.getKey());
                    }else if(cardsAndTiles.favorTiles.get(selectionFavorTile).getChristianityBonus() != 0){
                        religion_index = 1;
                        cardsAndTiles.playerChooseFavorTile(cardsAndTiles.favorTiles.get(selectionFavorTile),current,religions[1]);
                        returnInfo =religions[1].updateReligion(cardsAndTiles.favorTiles.get(selectionFavorTile).getChristianityBonus(),current.getPlayerId(),current.getKey());
                    }else if(cardsAndTiles.favorTiles.get(selectionFavorTile).getHinduismBonus() != 0){
                        religion_index = 3;
                        cardsAndTiles.playerChooseFavorTile(cardsAndTiles.favorTiles.get(selectionFavorTile),current,religions[3]);
                        returnInfo =religions[3].updateReligion(cardsAndTiles.favorTiles.get(selectionFavorTile).getHinduismBonus(),current.getPlayerId(),current.getKey());
                    }
                    else if(cardsAndTiles.favorTiles.get(selectionFavorTile).getJewBonus() != 0){
                        religion_index = 2;
                        cardsAndTiles.playerChooseFavorTile(cardsAndTiles.favorTiles.get(selectionFavorTile),current,religions[2]);
                        returnInfo = religions[2].updateReligion(cardsAndTiles.favorTiles.get(selectionFavorTile).getJewBonus(),current.getPlayerId(),current.getKey());
                    }
                    System.out.println("Selected " + chosen);
                    if(returnInfo[1] == 4) {
                        System.out.println("Cannot advance more on this religion");
                    }
                    else if( returnInfo[1] == 1) {
                        System.out.println("Since someone used key, you can't reach end"); // Can be replaced with a GUI message
                    }
                    else if( returnInfo[1] == 2) {
                        System.out.println("Since there is no key end pos is stuck on 9"); // Can be replaced with a GUI message
                    }
                    else if ( returnInfo[1] == 3) {
                        current.setKey(current.getKey()-1);
                    }
                    if(returnInfo[2] > 0 ) {
                        //Store the progress value for favor tile bonuses
                        current.addPowerToBowl(returnInfo[0]);
                    }
                    religions[religion_index].updateRoundBasedPositions(returnInfo[2], current.getPlayerId());
                    dialog.close();
                }
            });
        }
        dialog.showAndWait();
        return getSelectionFavorTile();
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
        border.setBackground(new Background( new BackgroundImage( new Image("the_background_3.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false))));
        dialog.setScene(dialogScene);
        dialog.setTitle("Scoring Tile");
        dialog.show();
        for (int i = 0; i < scoringTiles.size(); i++) {
            GridPane tempPane = new GridPane();
            tempPane.getChildren().add(new ScoringTileView(scoringTiles.get(i),i));
            gridPane.add(tempPane, i % 2, i / 2);
        }
        dialog.show();
    }
    public void showTownTilesTable(CardsAndTiles cardsAndTiles, Player current, Religion[] religions, boolean check) {
        setSelectionTown(-1);
        ArrayList<TownTile> townTiles = cardsAndTiles.getTownTiles();
        BorderPane border = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        BorderPane border_bottom = new BorderPane();
        border.setBottom(border_bottom);
        final Stage dialog = new Stage();
        border.setCenter(gridPane);
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(border, 1100, 600);
        dialog.setResizable(false);
        dialog.setScene(dialogScene);
        dialog.setTitle("Town Tiles");
        for (int i = 0; i < townTiles.size(); i++) {
            GridPane tempPane = new GridPane();
            tempPane.getChildren().add(new TownTileView(townTiles.get(i)));
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
                    if (selectionTown != finalI)
                        tempPane.setEffect(null);
                }
            });
            tempPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setSelectionTown(finalI);
                    for (int i = 0; i < townTiles.size(); i++) {
                        if (i != getSelectionTown())
                            gridPane.getChildren().get(i).setEffect(null);
                    }
                }
            });
            if (townTiles.get(i).isOccupied()) {
                DropShadow borderGlow = new DropShadow();
                borderGlow.setColor(Color.RED);
                borderGlow.setOffsetX(0f);
                borderGlow.setOffsetY(0f);
                borderGlow.setWidth(50);
                borderGlow.setHeight(50);
                tempPane.setEffect(borderGlow);
                tempPane.setDisable(true);
            }
            if(check){
                Button select = new Button("Select");
                select.setMaxHeight(100);
                select.setMinWidth(100);
                border_bottom.setCenter(select);
                select.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        int chosen = getSelectionTown();
                        System.out.println();
                        int[] returnInfo;
                        cardsAndTiles.playerChooseTownTile(cardsAndTiles.townTiles.get(selectionTown),current);
                        if(cardsAndTiles.townTiles.get(selectionTown).getIslamPoint() != 0){
                            System.out.println("Selected " + chosen);
                            for(int i = 0; i < 4;i++){
                                returnInfo=religions[i].updateReligion(cardsAndTiles.townTiles.get(selectionTown).getIslamPoint(),current.getPlayerId(),current.getKey());
                                if(returnInfo[1] == 4) {
                                    System.out.println("Cannot advance more on this religion");
                                }
                                else if( returnInfo[1] == 1) {
                                    System.out.println("Since someone used key, you can't reach end"); // Can be replaced with a GUI message
                                }
                                else if( returnInfo[1] == 2) {
                                    System.out.println("Since there is no key end pos is stuck on 9"); // Can be replaced with a GUI message
                                }
                                else if ( returnInfo[1] == 3) {
                                    current.setKey(current.getKey()-1);
                                }
                                if(returnInfo[2] > 0 ) {
                                    current.addPowerToBowl(returnInfo[0]);
                                }
                                religions[i].updateRoundBasedPositions(returnInfo[2], current.getPlayerId());
                            }

                        }

                        dialog.close();
                    }
                });
            }
            gridPane.add(tempPane, i % 4, i / 4);
            dialog.setScene(dialogScene);
            dialog.setTitle("Town Tiles");
            dialog.setResizable(false);
            border.setBackground(new Background(new BackgroundImage(new Image("the_background_9.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    new BackgroundSize(1.0, 1.0, true, true, false, false))));
            dialog.show();
        }
    }
    public int getSelectionTown() {
        return selectionTown;
    }
    public void setSelectionTown(int selectionTown) {
        this.selectionTown = selectionTown;
    }
    public  int getSelectionBonus() {
        return selectionBonus;
    }
    public  void setSelectionBonus(int i) {
        selectionBonus = i;
    }
    public  int getSelectionFavorTile() {
        return selectionFavorTile;
    }
    public  void setSelectionFavorTile(int selectionFavorTile) {this.selectionFavorTile = selectionFavorTile;}
}
