package Controller;

import Model.ReligionSubclasses.Christianity;
import Model.ReligionSubclasses.Hinduism;
import Model.ReligionSubclasses.Islam;
import Model.ReligionSubclasses.Jewish;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class ReligionController extends Application {

    @FXML
    private Pane pane;
    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane islam;
    @FXML
    private AnchorPane christianity;
    @FXML
    private AnchorPane jewish;
    @FXML
    private AnchorPane hindu;
    @FXML
    private ImageView hindu_image;
    private Islam islam_track;
    private Hinduism hindu_track;
    private Christianity chirst_track;

    private Jewish jewish_track;
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/View/ReligionView.fxml"));
       // hindu_image.setImage(new Image("/Images/air.png",160,160,false,true));

        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}