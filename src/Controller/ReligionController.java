package Controller;

import Model.Religion;
import Model.ReligionSubclasses.Christianity;
import Model.ReligionSubclasses.Hinduism;
import Model.ReligionSubclasses.Islam;
import Model.ReligionSubclasses.Jewish;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class ReligionController extends Application {
   @FXML
   private ChoiceBox<String> choiceBox;
   @FXML
   private Button priestButton;
   @FXML
   private Button orderButton;
   @FXML
   private GridPane gridPane;
   private int[] array = {0, 0, 1};
   private int currentPlayer = 0;
   private int playerCount = 3;
   private boolean playerKeyStatus = false;
   private Islam islam_track = new Islam(3, array);
   private Hinduism hindu_track = new Hinduism(3, array);
   private Christianity chirst_track = new Christianity(3, array);
   private Jewish jewish_track = new Jewish(3, array);
   private Religion[] religions = {islam_track, chirst_track, hindu_track, jewish_track};

   @Override
   public void start(Stage primaryStage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("/View/ReligionView.fxml"));
      gridPane = (GridPane) root.getChildrenUnmodifiable().get(0);
      update(gridPane,1,false);

      //Find religion to add as size (y coordinate)%(1/4 of anchor pane's size) to replace choice box.
      choiceBox = (ChoiceBox<String>) root.getChildrenUnmodifiable().get(1);
      //choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      //    @Override
      //    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
      //    }
      //});
      priestButton = (Button) root.getChildrenUnmodifiable().get(3);
      priestButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            int religion_index = choiceBox.getSelectionModel().getSelectedIndex();
            Religion choosen_religion = religions[religion_index];
            System.out.println("Gained power is " + choosen_religion.placePriest(currentPlayer, playerKeyStatus));
            System.out.println("Priest placed on " + choiceBox.getSelectionModel().getSelectedItem());
            System.out.println("Index: " + religion_index);
            update(gridPane, religion_index, false);
         }
      });
      orderButton = (Button) root.getChildrenUnmodifiable().get(4);
      orderButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
         @Override
         public void handle(MouseEvent event) {
            int religion_index = choiceBox.getSelectionModel().getSelectedIndex();
            Religion choosen_religion = religions[religion_index];
            System.out.println("Gained power is " + choosen_religion.addOrderOfReligion(currentPlayer, playerKeyStatus));
            System.out.println("Order acquired " + choiceBox.getSelectionModel().getSelectedItem());
            update(gridPane, religion_index, true);
         }
      });
      primaryStage.setScene(new Scene(root, 1920, 1080));
      primaryStage.setMaximized(true);
      primaryStage.show();


   }
   /**
    * According to religion track's data paint the locations occupied
    *
    * @param gridPane the gridpane that stays at right
    */
   private void update(GridPane gridPane, int religion_index, boolean isOrder) {
        gridPane.getChildren().clear();
        Color[] colors = {Color.BLUE, Color.RED,Color.GREEN};
        //Area without order colorizing
        for (int i = 0; i < playerCount; i++){
            for(int j = 0; j< religions.length; j++){
                Pane newPane = new Pane();
                int position = religions[j].getPlayerPositions()[i];
                newPane.setBackground(new Background(new BackgroundFill(colors[i], CornerRadii.EMPTY, Insets.EMPTY)));
                gridPane.add(newPane,position+1,j);
            }
        }
        //Order Colorizing
       for(int i = 0; i< religions.length; i++) {
           for(int j = 0; j< 4; j++){
                   int player_at_order = -1;
                   Pane orderPane = new Pane();
                   if (j == 0){
                       player_at_order = religions[i].getOrderOfCult_3();
                   }else if( j == 1){
                       player_at_order = religions[i].getOrderOfCult_2_1();
                   }else if (j == 2){
                       player_at_order = religions[i].getOrderOfCult_2_2();
                   }else{
                       player_at_order = religions[i].getOrderOfCult_2_3();
                   }
                   if(player_at_order != -1){
                       System.out.println("added to" + i + " slot " + j );
                       orderPane.setBackground(new Background(new BackgroundFill(colors[player_at_order], CornerRadii.EMPTY, Insets.EMPTY)));
                       GridPane orderGridPane = (GridPane) gridPane.getChildren().get(i);
                       System.out.println(i);
                       //orderGridPane.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
                       orderGridPane.add(orderPane, j % 2, j / 2);
                       gridPane.add(orderGridPane,i,0);
                   }
           }
       }

  //  System.out.println(gridPane.getRowCount());
  //  System.out.println(gridPane.getColumnCount());
  //  int limit = 11;
  //  Pane pane = new Pane();
  //  int nextSpace = 0;
  //  // TO MOVE PRIEST
  //  if (!isOrder) {
  //      nextSpace = religions[religion_index].getPlayerPositions()[currentPlayer];
  //      System.out.println("Next space to move priest is: " + nextSpace);
  //
  //      if (nextSpace != limit) {
  //          //System.out.println("Religion count: " + religions[religion_index].);
  //          pane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
  //          switch (religion_index) {
  //              case 0:
  //                  pane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
  //                  break;
  //              case 1:
  //                  pane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
  //                  break;
  //              case 2:
  //                  pane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
  //                  break;
  //              case 3:
  //                  pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
  //                  break;
  //          }
  //          gridPane.add(pane, nextSpace, religion_index);
  //      }
  //  } else {
  //      //pane.setBackground(new Background(new BackgroundFill(Color.INDIGO, CornerRadii.EMPTY, Insets.EMPTY)));
  //      //religions[religion_index].addOrderOfReligion(0, true);
  //      //nextSpace = religions[religion_index].getPlayerPositions()[0];
  //      //gridPane.add(pane, nextSpace, religion_index);
  //        for(int i = 0; i< 4; i++){
  //                 Pane orderPane = new Pane();
  //                 orderPane.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
  //
  //                if (religions[religion_index].isOccupied(i)){
  //                    GridPane orderGridPane = (GridPane)gridPane.getChildren().get(religion_index);
  //                    //orderGridPane.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
  //                    orderGridPane.add(orderPane,i%2,i/2);
  //                }
  //
  //        }
   }
   public static void main(String[] args) {
      launch(args);
   }
}