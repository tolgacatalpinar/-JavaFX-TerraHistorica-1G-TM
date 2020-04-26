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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
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
        final int[] choice = {0};
        special1.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(player.getSpecialActionToken().isSpade){
                    System.out.println("Spade Action");
                    choice[0] = 1;
                    System.out.println(choice[0]);
                    event.consume();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Special Action Error");
                    alert.setContentText("You have not special spade action");
                    alert.setHeaderText("You cannot do this action");
                    alert.showAndWait();
                }
            }
        });
        special2.getChildren().add(new SpecialActionView("Cult Action"));
        special2.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {

                if(player.getSpecialActionToken().isCultTack){
                    System.out.println("Cult Action");
                    choice[0] = 2;
                    System.out.println(choice[0]);

                    event.consume();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Special Action Error");
                    alert.setHeaderText("You cannot do this action");
                    alert.setContentText("You have not special cult action");
                    alert.showAndWait();
                }
            }
        });

        special3.getChildren().add(new SpecialActionView("Stronghold Ability"));
        special3.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(player.getSpecialActionToken().isStrongholdAbility){
                    System.out.println("Stronghold Ability");
                    choice[0] = 3;
                    System.out.println(choice[0]);

                    event.consume();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Special Action Error");
                    alert.setContentText("You have not special stronghold ability");
                    alert.setHeaderText("You cannot do this action");
                    alert.showAndWait();
                }
            }
        });
        special4.getChildren().add(new SpecialActionView("Faction Ability"));
        special4.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(player.getSpecialActionToken().isFactionAbility){
                    System.out.println("Faction Ability");
                    choice[0] = 4;
                    System.out.println(choice[0]);

                    event.consume();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Special Action Error");
                    alert.setContentText("You have not special faction ability");
                    alert.setHeaderText("You cannot do this action!!");
                    alert.showAndWait();
                }
            }
        });
        Button apply = new Button();
        apply.setText("Apply");
        apply.setFont(Font.font(20));
        apply.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(choice[0] == 1){
                    player.getSpecialActionToken().isSpade = false;
                    player.setSpadeInventory(1);
                    event.consume();
                }
                else if(choice[0] == 2){
                    player.getSpecialActionToken().isCultTack = false;
                    event.consume();
                }
                else if(choice[0] == 3){
                    player.getSpecialActionToken().isStrongholdAbility = false;
                    event.consume();
                }
                else if (choice[0] == 4){
                    player.getSpecialActionToken().isFactionAbility = false;
                    event.consume();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Special Action Error");
                    alert.setContentText("You have not special faction ability");
                    alert.setHeaderText("You cannot do this action!!");
                    alert.showAndWait();
                }
            }
        });
        firstRow.getChildren().addAll(special1,special2);
        secondRow.getChildren().addAll(special3,special4);
        wholeFavor.getChildren().addAll(firstRow, secondRow);
        wholeFavor.getChildren().add(apply);
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
