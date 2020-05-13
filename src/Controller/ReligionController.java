package Controller;

import Model.GameHandler;
import Model.Player;
import Model.Religion;
import Model.ReligionSubclasses.Christianity;
import Model.ReligionSubclasses.Hinduism;
import Model.ReligionSubclasses.Islam;
import Model.ReligionSubclasses.Jewish;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ReligionController{
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Button priestButton;
    @FXML
    private Button orderButton;
    @FXML
    private GridPane gridPane;
    private int currentPlayer;
    private int playerCount;
    private Religion[] religions;

    /**
     * Status paramater 0 -> Just show board religion
     * Status paramater 1 -> add to order according to religion choice
     * Status paramater 2 -> place priest according to religion choice
     *
     */
    public void showReligion(Player[] playerArr,int status, Religion[] religions,int currentPlayer)
    {

        BorderPane border = new BorderPane();
        GridPane gridPane = new GridPane();
        GridPane religon_buttons = new GridPane();

        border.setMaxHeight(600);
        border.setMaxWidth(1100);
        border.setCenter(gridPane);
        Button to_islam = new Button();
        Button to_chris = new Button();
        Button to_hindu = new Button();
        Button to_juda = new Button();
        religon_buttons.add(to_islam,1,0);
        religon_buttons.add(to_chris,1,1);
        religon_buttons.add(to_hindu,1,2);
        religon_buttons.add(to_juda,1,3);

        to_islam.setBackground(new Background(new BackgroundImage(new Image("islam_symbol.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true))));
        to_chris.setBackground(new Background(new BackgroundImage(new Image("chris_symbol.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true))));
        to_juda.setBackground(new Background(new BackgroundImage(new Image("hinduism.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true))));
        to_hindu.setBackground(new Background(new BackgroundImage(new Image("judaism_symbol.png"),BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true))));
        to_islam.setMinWidth(150);
        to_chris.setMinWidth(150);
        to_hindu.setMinWidth(150);
        to_juda.setMinWidth(150);
        to_islam.setMinHeight(150);
        to_chris.setMinHeight(150);
        to_hindu.setMinHeight(150);
        to_juda.setMinHeight(150);
        update(gridPane);
        if(status ==1 ){
            for (int i = 0; i < 4; i++) {
                Button temp_button = (Button) getNodeByRowColumnIndex(i,1, religon_buttons);
                Religion temp_religion = religions[i];
                temp_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("Add priest to order for "+ temp_religion.getClass().toString() + " and player "+ currentPlayer);
                        temp_religion.addOrderOfReligion(currentPlayer,playerArr[currentPlayer].getKey());
                        disableButtons(religon_buttons);
                        update(gridPane);
                    }
                });
            }
        }
        else if(status == 2 ){
            for (int i = 0; i < 4; i++) {
                Button temp_button = (Button) getNodeByRowColumnIndex(i,1, religon_buttons);
                Religion temp_religion = religions[i];
                temp_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("Place priest for "+ temp_religion.getClass().toString() + " and player "+ currentPlayer);
                        temp_religion.placePriest(currentPlayer,playerArr[currentPlayer].getKey());
                        disableButtons(religon_buttons);
                        update(gridPane);
                    }
                });
            }
        }
        else{
            disableButtons(religon_buttons);
            update(gridPane);
        }

        religon_buttons.setMinSize(150,600);
        border.setRight(religon_buttons);

        BackgroundImage bg = new BackgroundImage( new Image("religion_bg.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        border.setBackground(new Background(bg));
       //border.setPrefHeight(800);
        //border.setPrefWidth(1200);
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(border, 1100, 600);
        dialog.setScene(dialogScene);
        dialog.setTitle("Religion");
        dialog.setResizable(false);
        //update(gridPane,status);
        //Find religion to add as size (y coordinate)%(1/4 of anchor pane's size) to replace choice box.
        dialog.show();

    }
    public void showChoices(Player[] playerArr, Religion[] religionArr, int currentPlayer)
    {
        BorderPane border = new BorderPane();
        GridPane gridPane = new GridPane();
        border.setMaxHeight(200);
        border.setMaxWidth(200);
        border.setCenter(gridPane);
        Button add_order = new Button();
        Button place_priest = new Button();
        BackgroundImage add_to_order_bg = new BackgroundImage( new Image("add_to_order.jpg",300.0,300.0,true,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        add_order.setBackground(new Background(add_to_order_bg));
        BackgroundImage place_priest_bg = new BackgroundImage( new Image("place_priest.jpg",300.0,300.0,true,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        place_priest.setBackground(new Background(place_priest_bg));

        gridPane.add(add_order,1,1);
        gridPane.add(place_priest,2,1);
        add_order.setPrefSize(300, 300);
        place_priest.setPrefSize(300, 300);

        BackgroundImage bg = new BackgroundImage( new Image("religion_bg.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        border.setBackground(new Background(bg));
        final Stage dialog = new Stage();

        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(border, 600, 300);
        dialog.setScene(dialogScene);
        dialog.setTitle("Chose your action");
        dialog.setResizable(false);
        //Find religion to add as size (y coordinate)%(1/4 of anchor pane's size) to replace choice box.
        add_order.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dialog.close();
                showReligion(playerArr, 1,religions,currentPlayer);
            }
        });
        place_priest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dialog.close();
                showReligion(playerArr, 1,religions,currentPlayer);
            }
        });
        dialog.show();


    }

    /**
     * According to religion track's data paint the locations occupied
     *
     * @param gridPane the gridpane that stays at right
     */
    private void update(GridPane gridPane) {
        gridPane.getChildren().clear();
        //Setup panes and grid panes
        for(int i = 0; i < 4; i++){
            for (int j = 0; j<12; j++){
                GridPane tempGrid = new GridPane();
                Label text = new Label();
                if(j >= 1){
                    if(i == 1){
                        BackgroundImage myBI= new BackgroundImage(new Image("chris_track.png",110,145,false,true),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        //then you set to your node
                        tempGrid.setBackground(new Background(myBI));

                    }else if(i == 0){
                        BackgroundImage myBI= new BackgroundImage(new Image("islam_track.png",110,145,false,true),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        //then you set to your node
                        tempGrid.setBackground(new Background(myBI));

                    }else if( i == 3){
                        BackgroundImage myBI= new BackgroundImage(new Image("hindu_track.png",110,145,false,true),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        //then you set to your node
                        tempGrid.setBackground(new Background(myBI));

                    }else{
                        BackgroundImage myBI= new BackgroundImage(new Image("juda_track.png",110,145,false,true),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        //then you set to your node
                        tempGrid.setBackground(new Background(myBI));
                    }
                }else {
                    if(i == 1){
                        BackgroundImage myBI= new BackgroundImage(new Image("chris_starting.png",80,140,false,true),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        //then you set to your node
                        tempGrid.setBackground(new Background(myBI));
                    }else if(i == 0){
                        BackgroundImage myBI= new BackgroundImage(new Image("plsolsun.png",80,140,false,true),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        //then you set to your node
                        tempGrid.setBackground(new Background(myBI));
                    }else if( i == 3){
                        BackgroundImage myBI= new BackgroundImage(new Image("hindu_starting.png",80,140,false,true),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        //then you set to your node
                        tempGrid.setBackground(new Background(myBI));
                    }else{
                        BackgroundImage myBI= new BackgroundImage(new Image("juda_starting.png",80,140,false,true),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        //then you set to your node
                        tempGrid.setBackground(new Background(myBI));
                    }
                }
                for(int k = 0; k<4; k++){
                    Pane newPane = new Pane();
                    newPane.setPrefSize(tempGrid.getWidth()/4,tempGrid.getHeight()/4);
                    newPane.setPrefSize(50,90);
                    tempGrid.add(newPane, k/2, k%2);
                }
                gridPane.add(tempGrid, j,i);
                if(j > 0){
                    final Effect glow = new Glow(100.0);
                    text.setEffect(glow);
                    text.setText("        "+(j-1));
                    text.setFont(new Font("Stencil", 20));
                    text.setTextFill(Color.LIGHTGREY);
                    if(j == 3 || j == 6 || j== 9 || j==11){
                        ImageView powerImage = new ImageView("islam_symbol.png");
                        powerImage.setFitHeight(100);
                        powerImage.setFitWidth(10);
                        gridPane.add(powerImage,j,i);
                    }
                    gridPane.add(text, j,i);
                }
            }
        }
        System.out.println("player count "+ playerCount);
        Color[] colors = {Color.BLUE, Color.RED,Color.GREEN};
       for(int i = 0; i< religions.length; i++){
           for (int j = 0; j < playerCount; j++){
               int position = religions[i].getPlayerPositions()[j];
               GridPane newPane = (GridPane)getNodeByRowColumnIndex(i,position+1,gridPane);
               Pane playerPane = (Pane) getNodeByRowColumnIndex(j/2,j%2,newPane);
               playerPane.setBackground(new Background(new BackgroundFill(colors[j], CornerRadii.EMPTY, Insets.EMPTY)));
               System.out.println("Positions: " + (position+1) + " and " + i);
           }
       }
       for(int k = 0; k < 4; k++){
           for (int i = 0; i < 4; i++) {
               Pane orderPane = new Pane();
               orderPane.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
               if (religions[k].isOccupied(i)) {
                   GridPane orderGridPane = (GridPane) gridPane.getChildren().get(27*k);
                   BackgroundImage myBI;
                   if(k == 1){
                        myBI= new BackgroundImage(new Image("chris_starting.png",80,140,false,true),
                               BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                               BackgroundSize.DEFAULT);
                       //then you set to your node
                   }else if(k == 0){
                        myBI= new BackgroundImage(new Image("plsolsun.png",80,140,false,true),
                               BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                               BackgroundSize.DEFAULT);
                       //then you set to your node

                   }else if( k == 3){
                        myBI= new BackgroundImage(new Image("hindu_starting.png",80,140,false,true),
                               BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                               BackgroundSize.DEFAULT);
                       //then you set to your node
                   }else{
                        myBI= new BackgroundImage(new Image("juda_starting.png",80,140,false,true),
                               BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                               BackgroundSize.DEFAULT);
                       //then you set to your node
                   }
                   //then you set to your node
                   orderGridPane.setBackground(new Background(myBI));
                   orderGridPane.add(orderPane, i % 2, i / 2);
               }
           }
       }
    }
    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }
    public void disableButtons(GridPane buttons_grid){
        for (int i = 0; i < 4; i++){
            Button temp_button = (Button) getNodeByRowColumnIndex(i,1, buttons_grid);
            temp_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Can't do more actions in this round");
                }
            });
        }

    }

}
