package Controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       Parent root = FXMLLoader.load(getClass().getResource("/Controller/sample.fxml"));

       double[] points = new double[12];
       for (int i = 0; i < 12; i += 2) {
          double angle = Math.PI * (0.5 + i / 6d);
          points[i] = Math.cos(angle);
          points[i + 1] = Math.sin(angle);
       }
       Polygon polygon = new Polygon(points);

       OffsetPane op = new OffsetPane();

       double fieldHeight = 70;
       double fieldWidth = Math.sqrt(0.75) * fieldHeight;

       for (int i = 0; i < 117; i++) {
          Terrain terrain = new Terrain();
          terrain.setShape(polygon);
          terrain.setPrefSize(fieldWidth, fieldHeight);
          if( i % 2 == 0)
          {
             ImageView imView = new ImageView("brownTerrain.png");
             imView.setFitHeight(40);
             imView.setFitWidth(Math.sqrt(0.75) * 40);
             terrain.setGraphic(imView);
          }
          else
             terrain.setOpacity(0.3);
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
       primaryStage.setScene(new Scene(root, 300, 275));

        primaryStage.show();


//        Polygon polygon = new Polygon();
//
//        //Adding coordinates to the polygon
//        polygon.getPoints().addAll(new Double[]{
//                200.0, 50.0,
//                400.0, 50.0,
//                450.0, 150.0,
//                400.0, 250.0,
//                200.0, 250.0,
//                150.0, 150.0,
//        });
//        polygon.
//
//        //Creating a Group object
//        Group root = new Group(polygon);
//
//        //Creating a scene object
//        Scene scene = new Scene(root, 600, 300);
//
//        //Setting title to the Stage
//        primaryStage.setTitle("Drawing a Polygon");
//
//        //Adding scene to the stage
//        primaryStage.setScene(scene);
//
//        //Displaying the contents of the stage
//        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
