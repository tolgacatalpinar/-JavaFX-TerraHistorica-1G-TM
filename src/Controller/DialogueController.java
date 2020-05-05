package Controller;

import Model.GameHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialogueController {

   private static int STAGE_WIDTH = 400;
   private static int STAGE_HEIGHT = 300;
//   alert.setTitle("Special Action Error");
//                    alert.setContentText("You have not special faction ability");
//                    alert.setHeaderText("You cannot do this action!!")


   public static Stage getStage(String title, Parent component, Image backgroundImage)
   {
      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      Scene dialogScene = new Scene(component, STAGE_WIDTH, STAGE_HEIGHT);
      dialog.setScene(dialogScene);
      dialog.setTitle(title);
      dialog.setResizable(false);
      if( component instanceof Pane)
         ((Pane)component).setBackground(new Background( new BackgroundImage( backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
              BackgroundSize.DEFAULT)));
      return dialog;
   }
   public static BorderPane getBuildPromptPane(GameHandler gameHandler, String promptMessage, Button yesButton, Button noButton)
   {
      Label prompt = new Label(promptMessage);
      ImageView goldIm = new ImageView(new Image("gold.png"));
      goldIm.setFitHeight(30);
      goldIm.setFitWidth(30);
      Label goldLabel = new Label("" + gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().DWELLING_GOLD_COST);
      ImageView workerIm = new ImageView(new Image("gold.png"));
      workerIm.setFitHeight(30);
      workerIm.setFitWidth(30);
      Label workerLabel = new Label("" + gameHandler.getPlayerList()[gameHandler.getCurrentPlayerId()].getFaction().DWELLING_WORKER_COST);

      HBox requiredResources = new HBox();
      HBox buttons = new HBox();
      buttons.getChildren().addAll(yesButton, noButton);
      requiredResources.getChildren().addAll(goldIm, goldLabel, workerIm, workerLabel);
      VBox whole = new VBox();
      whole.getChildren().addAll(prompt, requiredResources, buttons);
      BorderPane pane = new BorderPane();
      pane.setCenter(whole);
      pane.setPadding(new Insets(50, 50, 0, 0));

      return pane;
   }
}
