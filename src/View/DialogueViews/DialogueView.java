package View.DialogueViews;

import Model.Player;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.Serializable;
public class DialogueView implements  Serializable{

   private static int STAGE_WIDTH = 1300;
   private static int STAGE_HEIGHT = 700;
//   alert.setTitle("Special Action Error");
//                    alert.setContentText("You have not special faction ability");
//                    alert.setHeaderText("You cannot do this action!!")


   public static Stage getStage(String title, Parent component, Image backgroundImage)
   {
      final Stage dialog = new Stage();
      dialog.initStyle(StageStyle.UNDECORATED);
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
   public static VBox getDwellingUpgradePromptPane(Player current, Button dwellingButton, Button emptyTerrainButton)
   {
      Player player = current;
      int leftGold = 0;
      int rightGold = player.getFaction().DWELLING_GOLD_COST;
      int leftWorker = 0;
      int rightWorker = player.getFaction().DWELLING_WORKER_COST;
      Insets promptInsets = new Insets(0, 0, 20, 100);
      String promptText = "Do you want to build a dwelling?";
      DialoguePane dwellingOrEmptyTerrainDialogue = new DialoguePane(emptyTerrainButton, dwellingButton, promptText, promptInsets, leftGold, rightGold, leftWorker, rightWorker);
      return dwellingOrEmptyTerrainDialogue;
   }
   public static VBox getTradingPostUpgradePromptPane(Player current, Button templeButton, Button strongholdButton)
   {
      Player player = current;
      int leftGold = player.getFaction().TEMPLE_GOLD_COST;
      int rightGold = player.getFaction().STRONGHOLD_GOLD_COST;
      int leftWorker = player.getFaction().TEMPLE_WORKER_COST;
      int rightWorker = player.getFaction().STRONGHOLD_WORKER_COST;
      Insets promptInsets = new Insets(0, 0, 20, 90);
      String promptText = "Do you want to build a temple or stronghold?";
      DialoguePane templeOrStrongholdDialogue = new DialoguePane(templeButton, strongholdButton, promptText, promptInsets, leftGold, rightGold, leftWorker, rightWorker);
      return templeOrStrongholdDialogue;


//      Label templePrompt = new Label(templePromptMessage);
//      ImageView goldImTemple = new ImageView(new Image("gold.png"));
//      goldImTemple.setFitHeight(30);
//      goldImTemple.setFitWidth(30);
//      Label goldLabelTemple = new Label("" + playerArr[curPlayerId].getFaction().TEMPLE_GOLD_COST);
//      ImageView workerImTemple = new ImageView(new Image("gold.png"));
//      workerImTemple.setFitHeight(30);
//      workerImTemple.setFitWidth(30);
//      Label workerLabelTemple = new Label("" + playerArr[curPlayerId].getFaction().TEMPLE_WORKER_COST);
//
//      ImageView goldImStrong = new ImageView(new Image("gold.png"));
//      goldImStrong.setFitHeight(30);
//      goldImStrong.setFitWidth(30);
//      ImageView workerImStrong = new ImageView(new Image("gold.png"));
//      workerImStrong.setFitHeight(30);
//      workerImStrong.setFitWidth(30);
//
//      Label strongholdPrompt = new Label(strongholdPromptMessage);
//      Label goldLabelStronghold = new Label("" + playerArr[curPlayerId].getFaction().STRONGHOLD_GOLD_COST);
//      Label workerLabelStronghold = new Label("" + playerArr[curPlayerId].getFaction().STRONGHOLD_WORKER_COST);
//
//      HBox buttons = new HBox();
//      buttons.getChildren().addAll(yesButton, noButton);
//
//      HBox requiredResourcesTemple = new HBox();
//      requiredResourcesTemple.getChildren().addAll(goldImTemple, goldLabelTemple, workerImTemple, workerLabelTemple);
//
//      HBox requiredResourcesStronghold = new HBox();
//      requiredResourcesStronghold.getChildren().addAll(goldImStrong, goldLabelStronghold, workerImStrong, workerLabelStronghold);
//
//      VBox whole = new VBox();
//      whole.getChildren().addAll(templePrompt, requiredResourcesTemple, strongholdPrompt, requiredResourcesStronghold, buttons);
//      BorderPane pane = new BorderPane();
//      pane.setCenter(whole);
//      HBox selectionBox = new HBox();
//      templeButton.setDisable(false);
//      selectionBox.getChildren().addAll( templeButton, strongholdButton);
//      pane.setBottom(selectionBox);
//      pane.setPadding(new Insets(50, 50, 50, 0));
//
//      return pane;
   }
   public static BorderPane getTempleUpgradePromptPane(Player current, String promptMessage, Button yesButton, Button noButton)
   {
      Label prompt = new Label(promptMessage);
      ImageView goldIm = new ImageView(new Image("gold.png"));
      goldIm.setFitHeight(30);
      goldIm.setFitWidth(30);
      Label goldLabel = new Label("" + current.getFaction().TEMPLE_GOLD_COST);
      ImageView workerIm = new ImageView(new Image("gold.png"));
      workerIm.setFitHeight(30);
      workerIm.setFitWidth(30);
      Label workerLabel = new Label("" + current.getFaction().TEMPLE_WORKER_COST);

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
