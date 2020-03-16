package Controller;

import View.OffsetPane;
import Model.Terrain;
import Model.TerrainSubclasses.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MapController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       Parent root = FXMLLoader.load(getClass().getResource("/View/GameView.fxml"));


       OffsetPane op = new OffsetPane();


       for (int i = 0; i < 117; i++) {
          Terrain terrain;
          if( i == 4 || i == 13 || i == 20 || i == 24 | i == 41 || i == 59 || i == 69 ||i == 87 ||i == 90 ||i == 91 ||i == 110)
             terrain = new Desert();
          else if( i == 0 || i == 6 || i == 16 || i == 50 || i == 53 || i == 57 || i == 74 || i == 76 || i == 93 || i == 101)
             terrain = new Plains();
          else if( i == 1 ||i == 30 || i == 36 || i == 58 || i == 65 || i == 75 || i == 81  || i == 100 || i == 102 || i == 106 || i == 112)
             terrain = new Mountains();
          else if(i == 2 || i == 9 || i == 32 || i == 34 || i == 39 || i == 62 || i == 66 || i == 70 ||  i == 85 || i == 109  || i == 115)
             terrain = new Forest();
          else if( i == 3 || i == 10 || i == 40 || i == 45 || i == 55 || i == 65 || i == 89  || i == 92 || i == 97 || i == 107 || i == 114)
             terrain = new Lakes();
          else if( i == 5 || i == 8 || i == 11 || i == 44 || i == 47 || i == 49 || i == 54 || i == 83 || i == 104 || i == 108 || i == 111 || i == 116)
             terrain = new Wasteland();
          else if(i == 7 || i == 12 || i == 17 || i == 21 || i == 28 || i == 52 || i == 56 || i == 63 || i == 88 || i == 98 || i == 105)
             terrain = new Swamp();
          else
             terrain = new Empty();
          terrain.setOnMouseClicked(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent e) {
                System.out.println("-----------------");
                System.out.println("Row: " + terrain.getRow());
                System.out.println("Col: " + terrain.getCol());

             }
          });
          op.add(terrain);
       }
// horizontal placement just right of the last element
       op.setHPositionFunction((int index, double x, double y, double width, double height) -> new Point2D(x + width, y));

// vertical position half the size left/right depending on index and
// 1/4 the node height above the bottom of the last node
       op.setVPositionFunction((int index, double x, double y, double width, double height) -> new Point2D(x + (index % 2 == 0 ? width : -width) / 2, y + height * 0.75));


      root.setId("pane");

       // Showing the map screen
       Button topButton = new Button("TOP");
       topButton.setPrefSize(2000, 100);
       ((BorderPane)root).setTop( topButton);


       Button botButton = new Button( "Bottom");
       botButton.setPrefSize(2000, 150);
       ((BorderPane)root).setBottom( botButton);

       Button leftButton = new Button( "LEFT");
       leftButton.setPrefSize(200, 1000);
       ((BorderPane) root).setLeft(leftButton);

       Button rightButton = new Button("RIGHT");
       rightButton.setPrefSize(200, 1000);
       ((BorderPane) root).setRight(rightButton);


       BorderPane terrainsLayout = new BorderPane();
       terrainsLayout.setPadding(new Insets(50, 150, 50, 150));
       terrainsLayout.setCenter(op);

       ((BorderPane)root).setCenter( terrainsLayout);
       primaryStage.setScene(new Scene(root, 1550, 800));
      primaryStage.setMaximized(true);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
