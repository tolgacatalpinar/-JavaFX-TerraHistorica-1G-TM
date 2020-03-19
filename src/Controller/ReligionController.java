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
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class ReligionController extends Application {
   @FXML
   private ChoiceBox<String> choiceBox;
   @FXML
   private AnchorPane board;
   @FXML
   private Button priestButton;
   @FXML
   private Button orderButton;
   @FXML
   private GridPane gridPane;
   private int[] array = {0, 0, 0};
   private int currentPlayer = 1;
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

      //update(gridPane);
      //Find religion to add as size (y coordinate)%(1/4 of anchor pane's size) to replace choice box.
      choiceBox = (ChoiceBox<String>) root.getChildrenUnmodifiable().get(1);
      //choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      //    @Override
      //    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
      //        System.out.println(choiceBox.getSelectionModel().getSelectedIndex());
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
      //priestButton.setVisible(false);
      //choose_rel.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      //    @Override
      //    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
      //        System.out.println(choose_rel.getItems().get((Integer) newValue));
      //    }
      //});
      primaryStage.setScene(new Scene(root, 1280, 720));
      primaryStage.setMaximized(false);
      primaryStage.show();


   }

   /**
    * According to religion track's data paint the locations occupied
    *
    * @param gridPane the gridpane that stays at right
    */
   private void update(GridPane gridPane, int religion_index, boolean isOrder) {
      //System.out.println(gridPane.getId());
      System.out.println(gridPane.getRowCount());
      System.out.println(gridPane.getColumnCount());
      int limit = 10;
      Pane pane = new Pane();

      int nextSpace = 0;
      // TO MOVE PRIEST
      if (!isOrder) {
         while (religions[religion_index].getPlayerPositions()[0] != nextSpace) {
            nextSpace++;
            if (nextSpace > limit)
               break;
         }
         nextSpace++;
         System.out.println("Next space to move priest is: " + nextSpace);

         if (nextSpace != limit) {
            //System.out.println("Religion count: " + religions[religion_index].);
            pane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            switch (religion_index) {
               case 0:
                  pane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                  break;
               case 1:
                  pane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                  break;
               case 2:
                  pane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                  break;
               case 3:
                  pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                  break;
            }
            gridPane.add(pane, nextSpace, religion_index);
            religions[religion_index].placePriest(0, false);
         }

      }
      else {
         pane.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
         religions[religion_index].addOrderOfReligion(0, true);
         gridPane.add(pane, nextSpace, religion_index);
//         if (!religions[religion_index].isOccupied(0)) {
//            pane.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
//            religions[religion_index].addOrderOfReligion(0, true);
//            gridPane.add(pane, nextSpace, religion_index);
//         }
//         else
//            System.out.println("Order is full!");
      }
//        for(int i = 0; i<gridPane.getRowCount(); i++){
//            for(int j = 1; j < gridPane.getColumnCount(); j++) {
//                if (religions[i].isOccupied(j-1)) {
//                    System.out.println("i is " + i + " j is "+ j);
//                    //Pane temp =(Pane) paintNodeByRowColumnIndex(i,j,gridPane);
//                   Pane temp = new Pane();
//                   System.out.println(temp == null);
//                    temp.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
//                    gridPane.add(temp,i,j);
//                }
//            }
//        }
   }

//   private javafx.scene.Node paintNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
//      ObservableList<Node> children = gridPane.getChildren();
//      for (Node node : children) {
//         System.out.println("GridPane is: " + gridPane.toString() + " and Node: " + node.toString());
//
//         if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
//            System.out.println(node.toString());
//            System.out.println("True");
//            return node;
//         }
//      }
//      return null;
//   }


   public static void main(String[] args) {
      launch(args);
   }
}