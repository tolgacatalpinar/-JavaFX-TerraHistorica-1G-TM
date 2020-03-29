package Controller;


import Model.Map;
//import Model.River;
import Model.Space;
import Model.Terrain;
//import Model.TerrainSubclasses.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapController extends Application implements Initializable {
   final int ROW_NUMBER = 9;
   final int COLUMN_NUMBER = 13;
   @FXML
   Pane mapPane;
   @FXML
   VBox mapVBox;
   Button[][] terrains;
   Map map;

   @Override
   public void start(Stage primaryStage) throws Exception {
      System.out.println("Start is called");
      //Map map = new Map();
      //OffsetPane op = new OffsetPane();
      Parent root = FXMLLoader.load(getClass().getResource("/View/GameView.fxml"));

      // SORT FXML
//       try
//       {
//          File file=new File("C://Users//TOLGA//IdeaProjects//TerraHistoricaFX//src//Controller//fxml.txt");    //creates a new file instance
//          FileReader fr=new FileReader(file);   //reads the file
//          BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
//          StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
//          String line;
//          ArrayList<String> list = new ArrayList<>();
//          while((line=br.readLine())!=null)
//          {
//             list.add(line);
//          }
//
//          // SORT
//          for (int i = 0; i < list.size(); i++) {
//
//             for (int j = 1; j < list.size() - i; j++) {
//                int firstIndex = list.get(j - 1).indexOf("terrain");
//                firstIndex += 6;
//                String firstNumber = "";
//                while(list.get(j - 1).charAt(firstIndex + 1) != ('\"'))
//                {
//                   firstNumber += list.get(j - 1).charAt(firstIndex+1);
//                   firstIndex ++;
//                   //System.out.println("while in");
//                }
//
//                int secondIndex = list.get(j).indexOf("terrain");
//                secondIndex += 6;
//                String secondNumber = "";
//                while(list.get(j).charAt(secondIndex + 1) != ('\"'))
//                {
//                   secondNumber += list.get(j).charAt(secondIndex+1);
//                   secondIndex ++;
//                   //System.out.println("while in");
//                }
//
//                System.out.println(firstNumber + "-" + secondNumber);
//                int firstInt = Integer.parseInt(firstNumber);
//                int secondInt = Integer.parseInt(secondNumber);
//                if (firstInt > secondInt) {
//                   System.out.println("Replace " + firstNumber + " with " + secondNumber);
//                   // swap arr[j+1] and arr[i]
//                   String temp = list.get(j - 1);
//                   list.set(j - 1, list.get(j));
//                   list.set(j, temp);
//                }
//             }
//          }
//          for (int i = 0; i < list.size(); i ++)
//          {
//             System.out.println( list.get(i));
//          }
//          fr.close();    //closes the stream and release the resources
//       }
//       catch(IOException e)
//       {
//          e.printStackTrace();
//       }


      //


      for (int i = 0; i < 20; i++) {
         //System.out.println("Element: " + mapPane.getChildren().get(i).getId());
      }

//        for(int i = 0; i< 12; i++ ) {
//            for (int j = 0; j< 8; j++) {
//                int size = 50;
//                TerrainButton button = new TerrainButton(map.spaces[j][i].imageLoc);
//                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent e) {
//                        System.out.println("-----------------");
//                        System.out.println("Row: " + button.getRow());
//                        System.out.println("Col: " + button.getCol());
//                    }
//                });
//                op.add(button);
//            }
//            }
//        // horizontal placement just right of the last element
//        op.setHPositionFunction((int index, double x, double y, double width, double height) -> new Point2D(x + width, y));
//
//// vertical position half the size left/right depending on index and
//// 1/4 the node height above the bottom of the last node
//        op.setVPositionFunction((int index, double x, double y, double width, double height) -> new Point2D(x + (index % 2 == 0 ? width : -width) / 2, y + height * 0.75));
//
//
//        root.setId("pane");

      // Showing the map screen
//        Button topButton = new Button("TOP");
//        topButton.setPrefSize(2000, 100);
//        ((BorderPane)root).setTop( topButton);
//
//
//        Button botButton = new Button( "Bottom");
//        botButton.setPrefSize(2000, 150);
//        ((BorderPane)root).setBottom( botButton);
//
//        Button leftButton = new Button( "LEFT");
//        leftButton.setPrefSize(200, 1000);
//        ((BorderPane) root).setLeft(leftButton);
//
//        Button rightButton = new Button("RIGHT");
//        rightButton.setPrefSize(200, 1000);
//        ((BorderPane) root).setRight(rightButton);
//
//
//        BorderPane terrainsLayout = new BorderPane();
//        terrainsLayout.setPadding(new Insets(50, 150, 50, 150));
//        terrainsLayout.setCenter(op);

      //((BorderPane)root).setCenter( terrainsLayout);


      primaryStage.setScene(new Scene(root, 1280, 720));
      //primaryStage.setMaximized(true);
      primaryStage.setResizable(false);
      primaryStage.show();

   }


   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
      System.out.println("Initialize is called");
      int index = 0;
      terrains = new Button[ROW_NUMBER][COLUMN_NUMBER];

      for (int i = 0; i < ROW_NUMBER * COLUMN_NUMBER; i++) {
         int row = i / 13;
         int col = i % 13;
         if (row % 2 == 0)
            terrains[row][col] = (Button) mapPane.getChildren().get(index);
         else if (col == 12) {
            terrains[row][col] = null;
            index--;
         } else
            terrains[row][col] = (Button) mapPane.getChildren().get(index);
         index++;
      }

//      for (int i = 0; i < ROW_NUMBER; i++) {
//         for (int j = 0; j < COLUMN_NUMBER; j++) {
//            Button button = terrains[i][j];
//            if (button == null)
//               System.out.println("Element at: " + i + ", " + j + " " + null);
//            else
//               System.out.println("Element at: " + i + ", " + j + " " + button.getId());
//         }
//      }
      createSpaces();

   }
   public void createSpaces()
   {
      System.out.println("Create is called");
      Space[][] spaces = new Space[ROW_NUMBER][COLUMN_NUMBER];
      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            Space space = new Space();
            if( terrains[i][j] == null)
               space.setType("Empty");
            else
            {
               switch (terrains[i][j].getId()) {
                  //TOLGA BURADA HATA VERİYOR KNK
                  case "blackHexagon": space.setType("Swamp");
                     break;
                  case "blueHexagon": space.setType("Lakes");
                     break;
                  case "brownHexagon": space.setType("Plains");
                     break;
                  case "greenHexagon": space.setType("Forest");
                     break;
                  case "redHexagon": space.setType("Wasteland");
                     break;
                  case "riverHexagon": space.setType("River");
                     break;
                  case "whiteHexagon": space.setType("Mountains");
                     break;
                  default: space.setType("River");
                     break;
               }
            }
            spaces[i][j] = space;
         }
      }
      map = new Map(spaces);
   }
}
