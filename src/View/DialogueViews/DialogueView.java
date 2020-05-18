package View.DialogueViews;

import Model.Player;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
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
import javafx.util.Duration;

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
   public static Stage getStage(Parent component)
   {
      final Stage dialog = new Stage();
      dialog.initStyle(StageStyle.UNDECORATED);
      dialog.initModality(Modality.APPLICATION_MODAL);
      Scene dialogScene = new Scene(component, 400, 100);
      dialog.setScene(dialogScene);
      dialog.setResizable(false);
      ((Pane)component).setBackground(new Background( new BackgroundImage( new Image("dialogueBackground.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
              BackgroundSize.DEFAULT)));
      return dialog;
   }

   public static VBox getDwellingUpgradePromptPane(Player player, Button discardButton, Button tradingPostButton)
   {
      int leftGold = 0;
      int rightGold = player.getFaction().DWELLING_GOLD_COST;
      int leftWorker = 0;
      int rightWorker = player.getFaction().DWELLING_WORKER_COST;
      Insets promptInsets = new Insets(0, 0, 20, 100);
      String promptText = "Do you want to upgrade dwelling to trading post?";
      DialoguePane dwellingOrEmptyTerrainDialogue = new DialoguePane(discardButton, tradingPostButton, promptText, promptInsets, leftGold, rightGold, leftWorker, rightWorker);
      return dwellingOrEmptyTerrainDialogue;
   }
   public static VBox getDwellingAfterTerraformPromptPane(Player player, Button dwellingButton, Button emptyTerrainButton)
   {
      int leftGold = 0;
      int rightGold = player.getFaction().DWELLING_GOLD_COST;
      int leftWorker = 0;
      int rightWorker = player.getFaction().DWELLING_WORKER_COST;
      Insets promptInsets = new Insets(0, 0, 20, 100);
      String promptText = "Do you want to build a dwelling?";
      DialoguePane dwellingOrEmptyTerrainDialogue = new DialoguePane(emptyTerrainButton, dwellingButton, promptText, promptInsets, leftGold, rightGold, leftWorker, rightWorker);
      return dwellingOrEmptyTerrainDialogue;
   }
   public static VBox getTradingPostUpgradePromptPane(Player player, Button templeButton, Button strongholdButton)
   {
      int leftGold = player.getFaction().TEMPLE_GOLD_COST;
      int rightGold = player.getFaction().STRONGHOLD_GOLD_COST;
      int leftWorker = player.getFaction().TEMPLE_WORKER_COST;
      int rightWorker = player.getFaction().STRONGHOLD_WORKER_COST;
      Insets promptInsets = new Insets(0, 0, 20, 90);
      String promptText = "Do you want to build a temple or stronghold?";
      DialoguePane templeOrStrongholdDialogue = new DialoguePane(templeButton, strongholdButton, promptText, promptInsets, leftGold, rightGold, leftWorker, rightWorker);
      return templeOrStrongholdDialogue;

   }
   public static VBox getTempleUpgradePromptPane(Player player, Button discardButton, Button sanctuaryButton)
   {
      int leftGold = player.getFaction().TEMPLE_GOLD_COST;
      int rightGold = player.getFaction().STRONGHOLD_GOLD_COST;
      int leftWorker = player.getFaction().TEMPLE_WORKER_COST;
      int rightWorker = player.getFaction().STRONGHOLD_WORKER_COST;
      Insets promptInsets = new Insets(0, 0, 20, 90);
      String promptText = "Do you want to upgrade temple to sanctuary";
      DialoguePane templeOrStrongholdDialogue = new DialoguePane(discardButton, sanctuaryButton, promptText, promptInsets, leftGold, rightGold, leftWorker, rightWorker);
      return templeOrStrongholdDialogue;
   }
   public static VBox getUpgradeShippingPromptPane(Player player, Button discardButton, Button shippingButton)
   {
      int leftGold = 0;
      int rightGold = player.getFaction().SHIPPING_GOLD_COST;
      int leftPriest = 0;
      int rightPriest = player.getFaction().SHIPPING_PRIEST_COST;
      Insets promptInsets = new Insets(0, 0, 20, 90);
      String promptText = "Do you want to upgrade your shipping?";
      VBox shippingDialoguePane = DialoguePane.getShippingDialoguePane(discardButton, shippingButton, promptText, promptInsets, leftGold, rightGold, leftPriest, rightPriest);
      return shippingDialoguePane;
   }
   public static VBox getUpgradeSpadePromptPane(Player player, Button discardButton, Button spadeButton)
   {
      int leftGold = 0;
      int rightGold = player.getFaction().SHIPPING_GOLD_COST;
      int leftWorker = 0;
      int rightWorker = player.getFaction().SPADE_WORKER_COST;
      int leftPriest = 0;
      int rightPriest = player.getFaction().SHIPPING_PRIEST_COST;
      Insets promptInsets = new Insets(0, 0, 20, 90);
      String promptText = "Do you want to upgrade your shipping?";
      VBox shippingDialoguePane = DialoguePane.getSpadeDialoguePane(discardButton, spadeButton, promptText, promptInsets, leftGold, rightGold, leftPriest, rightPriest, leftWorker, rightWorker);
      return shippingDialoguePane;
   }
   public static VBox getErrorMessage(String error)
   {
      Label errorLabel = new Label(error);
      errorLabel.setTextFill(Color.WHITE);
      errorLabel.getStyleClass().add("errorText");
      VBox box = new VBox();
      box.getChildren().add(errorLabel);
      box.setAlignment(Pos.CENTER);
      return box;
   }
   public static void delayErrorMessage(Stage stage)
   {
      PauseTransition delay = new PauseTransition(Duration.seconds(3));
      delay.setOnFinished( event -> stage.close() );
      delay.play();
   }
}
