package Controller;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ReligionController extends Application{
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Button priestButton;
    @FXML
    private Button orderButton;
    @FXML
    private GridPane gridPane;

    @FXML
    private GridPane myOrder;

    private int[] array = {2, 3, 5};
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

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        update(gridPane,1,true);
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        //thread.start();

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
        //Setup panes and grid panes
        for(int i = 0; i < 4; i++){
            for (int j = 0; j<12; j++){
                GridPane tempGrid = new GridPane();
                //tempGrid.setPrefSize(gridPane.getWidth()/12,gridPane.getHeight()/4);
                tempGrid.setStyle("-fx-background-color: gray, -fx-control-inner-background; -fx-background-insets: 0, 2; -fx-padding: 2;");
                for(int k = 0; k<4; k++){
                    Pane newPane = new Pane();
                    newPane.setPrefSize(tempGrid.getWidth()/4,tempGrid.getHeight()/4);
                    newPane.setPrefSize(50,90);
                    //newPane.setStyle("-fx-background-color: blue");
                    //newPane.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                    newPane.setBackground(Background.EMPTY);
                    tempGrid.add(newPane, k/2, k%2);
                }

                gridPane.add(tempGrid, j,i);

            }
        }
        Color[] colors = {Color.BLUE, Color.RED,Color.GREEN};
        //Area without order colorizing
      // for(int i = 0; i< religions.length; i++){
      //     for (int j = 0; j < playerCount; j++){
      //         int position = religions[i].getPlayerPositions()[j];
      //         GridPane newPane = (GridPane)getNodeByRowColumnIndex(i,position+1,gridPane);
      //         //newPane.setBackground(Background.EMPTY);
      //         Pane playerPane = (Pane) getNodeByRowColumnIndex(1,1,newPane);
      //         playerPane.setBackground(new Background(new BackgroundFill(colors[j], CornerRadii.EMPTY, Insets.EMPTY)));
      //        System.out.println("Positions: " + (position+1) + " and " + i);
      //     }
      // }
       for(int i = 0; i< religions.length; i++){
           for (int j = 0; j < playerCount; j++){
               int position = religions[i].getPlayerPositions()[j];
               GridPane newPane = (GridPane)getNodeByRowColumnIndex(i,position+1,gridPane);
               Pane playerPane = (Pane) getNodeByRowColumnIndex(j/2,j%2,newPane);
               //newPane.setBackground(Background.EMPTY);
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
                    //orderGridPane.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
                    orderGridPane.add(orderPane, i % 2, i / 2);
                }
            }
        }




       //  System.out.println(gridPane.getRowCount());
       //  System.out.println(gridPane.getColumnCount());
//     int limit = 11;
//     Pane pane = new Pane();
//     int nextSpace = 0;
//     // TO MOVE PRIEST
//     if (!isOrder) {
//        nextSpace = religions[religion_index].getPlayerPositions()[currentPlayer];
//        System.out.println("Next space to move priest is: " + nextSpace);
//
//          if (nextSpace != limit) {
//             //System.out.println("Religion count: " + religions[religion_index].);
//             pane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
//             switch (religion_index) {
//                case 0:
//                   pane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
//                   break;
//                case 1:
//                   pane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
//                   break;
//                case 2:
//                   pane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
//                   break;
//                case 3:
//                   pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
//                   break;
//             }
//             gridPane.add(pane, nextSpace, religion_index);
//          }
//       } else {
//          //pane.setBackground(new Background(new BackgroundFill(Color.INDIGO, CornerRadii.EMPTY, Insets.EMPTY)));
//          //religions[religion_index].addOrderOfReligion(0, true);
//          //nextSpace = religions[religion_index].getPlayerPositions()[0];
//          //gridPane.add(pane, nextSpace, religion_index);
//
//       }
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

    public static void main(String[] args) {
        launch(args);
    }


}
