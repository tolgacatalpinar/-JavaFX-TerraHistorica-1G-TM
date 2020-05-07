package View;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import java.awt.*;
import java.io.File;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
//        String path = "song.mp3";
//
//        //Instantiating Media class
//          Media media = new Media("file:src/Images/FactionImages/song.mp3");
//
//        //Instantiating MediaPlayer class
//          MediaPlayer mediaPlayer = new MediaPlayer(media);
//
//        //by setting this property to true, the audio will be played
//        mediaPlayer.setAutoPlay(true);
//        primaryStage.setTitle("Playing Audio");
//        primaryStage.show();

//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int screenHeight = screenSize.height;
//        int screenWidth = screenSize.width;
//        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen

        Parent root = FXMLLoader.load(getClass().getResource("MenuViews/MainMenuView.fxml"));
        primaryStage.setTitle("Terra Historica");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
//        primaryStage.setScene(new Scene(root, screenWidth, screenHeight));
//        primaryStage.setResizable(true);
//        primaryStage.setX(primaryScreenBounds.getMinX());
//        primaryStage.setY(primaryScreenBounds.getMinY());
//        primaryStage.setWidth(primaryScreenBounds.getWidth());
//        primaryStage.setHeight(primaryScreenBounds.getHeight());
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
