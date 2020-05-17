package View;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
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
   public static VBox getDwellingUpgradePromptPane(Player[] playerArr, int curPlayerId, Button dwellingButton, Button emptyTerrainButton)
   {
      // Styling buttons

      Label prompt = new Label("Do you want to build a dwelling?");
      ImageView goldIm = new ImageView(new Image("gold.png"));
      goldIm.setFitHeight(30);
      goldIm.setFitWidth(30);

      ImageView goldIm2 = new ImageView(new Image("gold.png"));
      goldIm2.setFitHeight(30);
      goldIm2.setFitWidth(30);

      Label goldLabel = new Label("" + playerArr[curPlayerId].getFaction().DWELLING_GOLD_COST);
      goldLabel.setTextFill(Color.WHITE);
      goldLabel.getStyleClass().add("textShadow");

      ImageView workerIm = new ImageView(new Image("worker.png"));
      workerIm.setFitHeight(30);
      workerIm.setFitWidth(30);
      ImageView workerIm2 = new ImageView(new Image("worker.png"));
      workerIm2.setFitHeight(30);
      workerIm2.setFitWidth(30);
      Label workerLabel = new Label("" + playerArr[curPlayerId].getFaction().DWELLING_WORKER_COST);
      workerLabel.setTextFill(Color.WHITE);
      workerLabel.getStyleClass().add("textShadow");

      // Empty terrain labels
      Label emptyWorker = new Label("0");
      emptyWorker.setTextFill(Color.WHITE);
      emptyWorker.getStyleClass().add("textShadow");
      Label emptyGold = new Label("0");
      emptyGold.setTextFill(Color.WHITE);
      emptyGold.getStyleClass().add("textShadow");
      // Dwelling
      HBox requiredResourcesForDwelling = new HBox();
      requiredResourcesForDwelling.setSpacing(50);
      requiredResourcesForDwelling.getChildren().addAll(goldIm, goldLabel, workerIm, workerLabel);
      VBox dwellingBox = new VBox();
      dwellingBox.getChildren().addAll(dwellingButton, requiredResourcesForDwelling);
      dwellingBox.setSpacing(50);

      // Empty Terrain
      HBox requiredResourcesForEmptyTerrain = new HBox();
      requiredResourcesForEmptyTerrain.setSpacing(50);
      requiredResourcesForEmptyTerrain.getChildren().addAll(goldIm2, emptyGold, workerIm2, emptyWorker);
      VBox emptyTerrainBox = new VBox();
      emptyTerrainBox.getChildren().addAll(emptyTerrainButton, requiredResourcesForEmptyTerrain);
      emptyTerrainBox.setSpacing(50);

      HBox buttons = new HBox();
      buttons.getChildren().addAll(emptyTerrainBox, dwellingBox);
      buttons.setSpacing(100);

      VBox whole = new VBox();
      whole.getChildren().addAll(prompt, buttons);

      whole.setPadding(new Insets(50, 0, 0, 300));

      return whole;
   }
   public static BorderPane getTradingPostUpgradePromptPane(Player[] playerArr,int curPlayerId, String templePromptMessage, String strongholdPromptMessage, Button yesButton, Button noButton, RadioButton templeButton, RadioButton strongholdButton)
   {
      Label templePrompt = new Label(templePromptMessage);
      ImageView goldImTemple = new ImageView(new Image("gold.png"));
      goldImTemple.setFitHeight(30);
      goldImTemple.setFitWidth(30);
      Label goldLabelTemple = new Label("" + playerArr[curPlayerId].getFaction().TEMPLE_GOLD_COST);
      ImageView workerImTemple = new ImageView(new Image("gold.png"));
      workerImTemple.setFitHeight(30);
      workerImTemple.setFitWidth(30);
      Label workerLabelTemple = new Label("" + playerArr[curPlayerId].getFaction().TEMPLE_WORKER_COST);

      ImageView goldImStrong = new ImageView(new Image("gold.png"));
      goldImStrong.setFitHeight(30);
      goldImStrong.setFitWidth(30);
      ImageView workerImStrong = new ImageView(new Image("gold.png"));
      workerImStrong.setFitHeight(30);
      workerImStrong.setFitWidth(30);

      Label strongholdPrompt = new Label(strongholdPromptMessage);
      Label goldLabelStronghold = new Label("" + playerArr[curPlayerId].getFaction().STRONGHOLD_GOLD_COST);
      Label workerLabelStronghold = new Label("" + playerArr[curPlayerId].getFaction().STRONGHOLD_WORKER_COST);

      HBox buttons = new HBox();
      buttons.getChildren().addAll(yesButton, noButton);

      HBox requiredResourcesTemple = new HBox();
      requiredResourcesTemple.getChildren().addAll(goldImTemple, goldLabelTemple, workerImTemple, workerLabelTemple);

      HBox requiredResourcesStronghold = new HBox();
      requiredResourcesStronghold.getChildren().addAll(goldImStrong, goldLabelStronghold, workerImStrong, workerLabelStronghold);

      VBox whole = new VBox();
      whole.getChildren().addAll(templePrompt, requiredResourcesTemple, strongholdPrompt, requiredResourcesStronghold, buttons);
      BorderPane pane = new BorderPane();
      pane.setCenter(whole);
      HBox selectionBox = new HBox();
      templeButton.setDisable(false);
      selectionBox.getChildren().addAll( templeButton, strongholdButton);
      pane.setBottom(selectionBox);
      pane.setPadding(new Insets(50, 50, 50, 0));

      return pane;
   }
   public static BorderPane getTempleUpgradePromptPane(Player[] playerArr, int curPlayerId, String promptMessage, Button yesButton, Button noButton)
   {
      Label prompt = new Label(promptMessage);
      ImageView goldIm = new ImageView(new Image("gold.png"));
      goldIm.setFitHeight(30);
      goldIm.setFitWidth(30);
      Label goldLabel = new Label("" + playerArr[curPlayerId].getFaction().TEMPLE_GOLD_COST);
      ImageView workerIm = new ImageView(new Image("gold.png"));
      workerIm.setFitHeight(30);
      workerIm.setFitWidth(30);
      Label workerLabel = new Label("" + playerArr[curPlayerId].getFaction().TEMPLE_WORKER_COST);

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
