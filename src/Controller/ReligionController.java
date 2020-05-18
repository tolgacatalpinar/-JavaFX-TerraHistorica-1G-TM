package Controller;

import Model.FactionSubclasses.*;
import Model.Player;
import Model.PlayerHandler;
import Model.Religion;
import Model.ReligionSubclasses.Christianity;
import Model.ReligionSubclasses.Hinduism;
import Model.ReligionSubclasses.Islam;
import Model.ReligionSubclasses.Jewish;
import View.ScoreTableView;
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
import java.io.Serializable;
import java.util.*;
import java.util.stream.IntStream;

public class ReligionController implements Serializable{
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
    private PlayerHandler playerHandler = new PlayerHandler();
    String[] colors = new String[5];

    /**
     * Status paramater 0 -> Just show board religion
     * Status paramater 1 -> add to order according to religion choice
     * Status paramater 2 -> place priest according to religion choice
     *
     */
    public void showReligion(Player[] playerArr,int status, Religion[] religions,int currentPlayer,boolean kaltrekşın)
    {

        for (int i = 0; i< playerArr.length; i++ ) {
            colors[i] = getImage(playerArr[i]);
        }
        this.religions = religions;
        playerCount = playerArr.length;
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
                        //if(playerArr[currentPlayer])
                        if(playerArr[currentPlayer].getPriestNum() > 0){
                            int[] returnInfo = temp_religion.addOrderOfReligion(currentPlayer,playerArr[currentPlayer].getKey());
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
                                    playerArr[currentPlayer].setKey(playerArr[currentPlayer].getKey()-1);
                            }
                            if(returnInfo[2] > 0 ) {
                                //Store the progress value for favor tile bonuses
                                playerArr[currentPlayer].spendPriest(1);
                                playerArr[currentPlayer].setPriestOnBank(playerArr[currentPlayer].getPriestOnBank()-1);
                                playerArr[currentPlayer].addPowerToBowl(returnInfo[0]);
                            }
                            temp_religion.updateRoundBasedPositions(returnInfo[2], currentPlayer);
                        }
                        else {
                            System.out.println("Not enough resources");
                        }
                        //play
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
                        Player curPlayer  = playerArr[currentPlayer];
                        if(kaltrekşın||playerArr[currentPlayer].getPriestNum() > 0){
                            int[] returnInfo = temp_religion.placePriest(currentPlayer,playerArr[currentPlayer].getKey());
                            System.out.println(returnInfo[0] + " " + returnInfo[1] + " " + returnInfo[2]);
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
                                    playerArr[currentPlayer].setKey(playerArr[currentPlayer].getKey()-1);
                            }
                            if(returnInfo[2] > 0 ) {
                                //Store the progress value for favor tile bonuses
                                if(kaltrekşın){
                                    playerArr[currentPlayer].getSpecialActionToken().isCultTack = false;
                                }else
                                    playerArr[currentPlayer].spendPriest(1);
                                playerArr[currentPlayer].addPowerToBowl(returnInfo[0]);
                            }
                            temp_religion.updateRoundBasedPositions(returnInfo[2],currentPlayer);
                        }
                        else {
                            System.out.println("Not enough resources");
                        }
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
        religions = religionArr;
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
                showReligion(playerArr, 1,religions,currentPlayer, false);
            }
        });
        place_priest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dialog.close();
                showReligion(playerArr, 2,religions,currentPlayer,false);
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
                        ImageView powerImage = new ImageView("power.png");
                        powerImage.setFitHeight(100);
                        powerImage.setFitWidth(10);
                        gridPane.add(powerImage,j,i);
                    }
                    gridPane.add(text, j,i);
                }
                else if (j == 0){
                    for (int y = 0; y < 4; y++){
                        Label label = new Label();
                        final Effect glow = new Glow(100.0);
                        label.setEffect(glow);
                        label.setFont(new Font("Stencil", 20));
                        label.setTextFill(Color.LIGHTGREY);
                        if (y == 0)
                            label.setText("    3");
                        else
                            label.setText("    2");
                        ((GridPane)(gridPane.getChildren().get(27*i))).add(label,y%2,y/2);
                    }
                }
            }
        }
        System.out.println("player count "+ playerCount);
       for(int i = 0; i< religions.length; i++){
           for (int j = 0; j < playerCount; j++){
               int position = religions[i].getPlayerPositions()[j];
               GridPane newPane = (GridPane)getNodeByRowColumnIndex(i,position+1,gridPane);
               Pane playerPane = (Pane) getNodeByRowColumnIndex(j/2,j%2,newPane);
               ImageView player_image = new ImageView(colors[j]);
               player_image.setFitWidth(50);
               player_image.setFitHeight(72.5);
               playerPane.getChildren().add(player_image);
               System.out.println("Positions: " + (position+1) + " and " + i);
           }
       }
       for(int k = 0; k < 4; k++){
           for (int i = 0; i < 4; i++) {
               Pane orderPane = new Pane();
               ImageView order_image = new ImageView("priest.png");
               order_image.setFitWidth(50);
               order_image.setFitHeight(65);
               orderPane.getChildren().add(order_image);

               if (religions[k].isOccupied(i)) {
                   GridPane orderGridPane = (GridPane) gridPane.getChildren().get(27*k);
                   BackgroundImage myBI;
                   if(k == 1){
                        myBI= new BackgroundImage(new Image("chris_starting.png",80,140,false,true),
                               BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                               BackgroundSize.DEFAULT);
                   }else if(k == 0){
                        myBI= new BackgroundImage(new Image("plsolsun.png",80,140,false,true),
                               BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                               BackgroundSize.DEFAULT);

                   }else if( k == 3){
                        myBI= new BackgroundImage(new Image("hindu_starting.png",80,140,false,true),
                               BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                               BackgroundSize.DEFAULT);
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

    /**
     *
     *
     *
     */
    public ArrayList<ArrayList<Integer>>[] calculateReligionScores(Religion[] religions,Player[] playerList) {

        ArrayList<ArrayList<Integer>>[] realResult = new ArrayList[religions.length];

        for(int i = 0; i < religions.length; i++) {
            int [] playerPositions = religions[i].getPlayerPositions();
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();
            int playerCount = 0;
            for (int j= 10; j >= 0; j--) {
                ArrayList<Integer> players = new ArrayList<Integer>();
                for ( int k = 0; k < playerPositions.length; k++) {
                    if ( playerPositions[k] == j) {
                        players.add(k);
                        playerCount++;
                    }
                }
                if(players.size() > 0)
                    result.add(players);

                if( result.size() >=3 ) {
                    break;
                }
            }
            if(playerCount >= playerList.length && result.size() < 3) {
                while (result.size() < 3) {
                    result.add(new ArrayList<Integer>());
                }
            }
            realResult[i] = result;
        }
        return realResult;
    }
    public String getImage(Player player){
        String image = null;
        if (player.getFaction() instanceof AliesterCrowley) {
           return  "file:src/Images/FactionImages/Image_AleisterCrowley.jpeg";
        } else if (player.getFaction() instanceof AmerigoVespucci) {
           return "file:src/Images/FactionImages/Image_AmerigoVespucci.jpeg";
        } else if (player.getFaction() instanceof Buddha) {
           return "file:src/Images/FactionImages/Image_Buddha.jpeg";
        } else if (player.getFaction() instanceof DariusTheGreat) {
           return "file:src/Images/FactionImages/Image_DariusTheGreat.jpeg";
        } else if (player.getFaction() instanceof ErikTheRed) {
            return "file:src/Images/FactionImages/Image_ErikTheRed.jpeg";
        } else if (player.getFaction() instanceof Gilgamesh) {
            return "file:src/Images/FactionImages/Image_Gilgamesh.jpeg";
        } else if (player.getFaction() instanceof HelenOfTroy) {
            return "file:src/Images/FactionImages/Image_HelenOfTroy.jpeg";
        } else if (player.getFaction() instanceof HusseinTheTeaMaker) {
            return "file:src/Images/FactionImages/Image_HusseinTheTeaMaker.jpeg";
        } else if (player.getFaction() instanceof LeonardoDaVinci) {
            return "file:src/Images/FactionImages/Image_LeonardoDaVinci.jpeg";
        } else if (player.getFaction() instanceof MarieCurie) {
            return "file:src/Images/FactionImages/Image_MarieCurie.jpeg";
        } else if (player.getFaction() instanceof MorganLeFay) {
           return "file:src/Images/FactionImages/Image_MorganLeFay.jpeg";
        } else if (player.getFaction() instanceof Ramesses) {
            return "file:src/Images/FactionImages/Image_Ramesses.jpeg";
        } else if (player.getFaction() instanceof StPatrick) {
           return "file:src/Images/FactionImages/Image_StPatrick.jpeg";
        } else if (player.getFaction() instanceof VladTheImpaler) {
           return "file:src/Images/FactionImages/Image_VladTheImpaler.jpeg";
        }
        return image;
    }

}
