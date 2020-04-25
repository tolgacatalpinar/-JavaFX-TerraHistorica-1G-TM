package Controller;

import Model.GameHandler;
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
    private int[] array = {2, 3, 5};
    private int currentPlayer = 0;
    private int playerCount = 3;
    private boolean playerKeyStatus = false;
    private Islam islam_track = new Islam(3, array);
    private Hinduism hindu_track = new Hinduism(3, array);
    private Christianity chirst_track = new Christianity(3, array);
    private Jewish jewish_track = new Jewish(3, array);
    private Religion[] religions = {islam_track, chirst_track, hindu_track, jewish_track};


    public void showReligion(GameHandler gameHandler)
    {
        BorderPane border = new BorderPane();
        GridPane gridPane = new GridPane();
        border.setPadding(new Insets(10,10,10,10));
        border.setMaxHeight(600);
        border.setMaxWidth(1100);
        border.setCenter(gridPane);
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
        update(gridPane,1,false);
        //Find religion to add as size (y coordinate)%(1/4 of anchor pane's size) to replace choice box.
        dialog.show();

    }
    /**
     * According to religion track's data paint the locations occupied
     *
     * @param gridPane the gridpane that stays at right
     */
    private void update(GridPane gridPane, int religion_index, boolean isOrder) {
        gridPane.getChildren().clear();
        //Setup panes and grid panes
        for(int i = 0; i < 4; i++){
            for (int j = 0; j<12; j++){
                GridPane tempGrid = new GridPane();
                Label text = new Label();
                if(j >= 1){
                    if(i == 1){
                        BackgroundImage myBI= new BackgroundImage(new Image("chris_track.png",tempGrid.getWidth(),tempGrid.getHeight(),false,true),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        //then you set to your node
                        tempGrid.setBackground(new Background(myBI));
                    }else if(i == 0){
                        BackgroundImage myBI= new BackgroundImage(new Image("islam_track.png",tempGrid.getWidth(),tempGrid.getHeight(),false,true),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        //then you set to your node
                        tempGrid.setBackground(new Background(myBI));
                    }else if( i == 3){
                        BackgroundImage myBI= new BackgroundImage(new Image("hindu_track.png",tempGrid.getWidth(),tempGrid.getHeight(),false,true),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        //then you set to your node
                        tempGrid.setBackground(new Background(myBI));
                    }else{
                        BackgroundImage myBI= new BackgroundImage(new Image("juda_track.png",tempGrid.getWidth(),tempGrid.getHeight(),false,true),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        //then you set to your node
                        tempGrid.setBackground(new Background(myBI));
                    }
                }else {
                    BackgroundImage myBI= new BackgroundImage(new Image("islam_door.png",100,180,false,true),
                            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT);
                    //then you set to your node
                    tempGrid.setBackground(new Background(myBI));
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
                   GridPane orderGridPane = (GridPane) gridPane.getChildren().get(k*12);
                   BackgroundImage myBI= new BackgroundImage(new Image("islam_door.png",100,180,false,true),
                           BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                           BackgroundSize.DEFAULT);
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

}
