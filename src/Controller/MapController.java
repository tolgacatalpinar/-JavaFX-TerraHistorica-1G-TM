package Controller;

import Model.Map;
import View.MapButton;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MapController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Map map = new Map();
        OffsetPane op = new OffsetPane();
        Parent root = FXMLLoader.load(getClass().getResource("/View/GameView.fxml"));
        for(int i = 0; i< 12; i++ ) {
            for (int j = 0; j< 8; j++) {
                int size = 50;
                MapButton button = new MapButton(map.spaces[j][i].imageLoc);
                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        System.out.println("-----------------");
                        System.out.println("Row: " + button.getRow());
                        System.out.println("Col: " + button.getCol());
                    }
                });
                op.add(button);
            }
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
