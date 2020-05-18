package View.DialogueViews;

import Model.Player;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class DialoguePane extends VBox {

   public DialoguePane(Button leftButton, Button rightButton, String promptText, Insets promptInsets, int leftGold, int rightGold, int leftWorker, int rightWorker)
   {
      // Prompt
      Label prompt = new Label(promptText);
      prompt.setTextFill(Color.ORANGE);
      prompt.getStyleClass().add("textShadow");
      prompt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
      prompt.setPadding(promptInsets);

      // Images
      ImageView goldIm = new ImageView(new Image("gold.png"));
      goldIm.setFitHeight(30);
      goldIm.setFitWidth(30);

      ImageView goldIm2 = new ImageView(new Image("gold.png"));
      goldIm2.setFitHeight(30);
      goldIm2.setFitWidth(30);

      Label leftGoldLabel = new Label("" + leftGold);
      leftGoldLabel.setTextFill(Color.WHITE);
      leftGoldLabel.getStyleClass().add("textShadow");

      ImageView leftWorkerIm = new ImageView(new Image("worker.png"));
      leftWorkerIm.setFitHeight(30);
      leftWorkerIm.setFitWidth(30);
      ImageView rightWorkerIm = new ImageView(new Image("worker.png"));
      rightWorkerIm.setFitHeight(30);
      rightWorkerIm.setFitWidth(30);
      Label leftWorkerLabel = new Label("" + leftWorker);
      leftWorkerLabel.setTextFill(Color.WHITE);
      leftWorkerLabel.getStyleClass().add("textShadow");

      // Empty terrain labels
      Label rightWorkerLabel = new Label("" + rightWorker);
      rightWorkerLabel.setTextFill(Color.WHITE);
      rightWorkerLabel.getStyleClass().add("textShadow");
      Label rightGoldLabel = new Label("" + rightGold);
      rightGoldLabel.setTextFill(Color.WHITE);
      rightGoldLabel.getStyleClass().add("textShadow");
      // Dwelling
      HBox requiredResourcesForLeft = new HBox();
      requiredResourcesForLeft.setSpacing(50);
      requiredResourcesForLeft.getChildren().addAll(goldIm, leftGoldLabel, leftWorkerIm, leftWorkerLabel);
      VBox leftBox = new VBox();
      leftBox.getChildren().addAll(leftButton, requiredResourcesForLeft);
      leftBox.setSpacing(30);

      // Empty Terrain
      HBox requiredResourcesForRight = new HBox();
      requiredResourcesForRight.setSpacing(50);
      requiredResourcesForRight.getChildren().addAll(goldIm2, rightGoldLabel, rightWorkerIm, rightWorkerLabel);
      VBox rightBox = new VBox();
      rightBox.getChildren().addAll(rightButton, requiredResourcesForRight);
      rightBox.setSpacing(30);

      HBox boxes = new HBox();
      boxes.getChildren().addAll(leftBox, rightBox);
      boxes.setSpacing(100);

      this.getChildren().addAll(prompt, boxes);

      this.setPadding(new Insets(50, 0, 0, 300));
   }

   public static VBox getShippingDialoguePane(Button leftButton, Button rightButton, String promptText, Insets promptInsets, int leftGold, int rightGold, int leftPriest, int rightPriest)
   {
      // Prompt
      Label prompt = new Label(promptText);
      prompt.setTextFill(Color.ORANGE);
      prompt.getStyleClass().add("textShadow");
      prompt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
      prompt.setPadding(promptInsets);

      // Images
      ImageView goldIm = new ImageView(new Image("gold.png"));
      goldIm.setFitHeight(30);
      goldIm.setFitWidth(30);

      ImageView goldIm2 = new ImageView(new Image("gold.png"));
      goldIm2.setFitHeight(30);
      goldIm2.setFitWidth(30);

      Label leftGoldLabel = new Label("" + leftGold);
      leftGoldLabel.setTextFill(Color.WHITE);
      leftGoldLabel.getStyleClass().add("textShadow");

      ImageView leftPriestIm = new ImageView(new Image("priest.png"));
      leftPriestIm.setFitHeight(30);
      leftPriestIm.setFitWidth(30);
      ImageView rightPriestIm = new ImageView(new Image("priest.png"));
      rightPriestIm.setFitHeight(30);
      rightPriestIm.setFitWidth(30);
      Label leftPriestLabel = new Label("" + leftPriest);
      leftPriestLabel.setTextFill(Color.WHITE);
      leftPriestLabel.getStyleClass().add("textShadow");

      // Empty terrain labels
      Label rightPriestLabel = new Label("" + rightPriest);
      rightPriestLabel.setTextFill(Color.WHITE);
      rightPriestLabel.getStyleClass().add("textShadow");
      Label rightGoldLabel = new Label("" + rightGold);
      rightGoldLabel.setTextFill(Color.WHITE);
      rightGoldLabel.getStyleClass().add("textShadow");
      // Dwelling
      HBox requiredResourcesForLeft = new HBox();
      requiredResourcesForLeft.setSpacing(50);
      requiredResourcesForLeft.getChildren().addAll(goldIm, leftGoldLabel, leftPriestIm, leftPriestLabel);
      VBox leftBox = new VBox();
      leftBox.getChildren().addAll(leftButton, requiredResourcesForLeft);
      leftBox.setSpacing(30);

      // Empty Terrain
      HBox requiredResourcesForRight = new HBox();
      requiredResourcesForRight.setSpacing(50);
      requiredResourcesForRight.getChildren().addAll(goldIm2, rightGoldLabel, rightPriestIm, rightPriestLabel);
      VBox rightBox = new VBox();
      rightBox.getChildren().addAll(rightButton, requiredResourcesForRight);
      rightBox.setSpacing(30);

      HBox boxes = new HBox();
      boxes.getChildren().addAll(leftBox, rightBox);
      boxes.setSpacing(100);

      VBox whole = new VBox();
      whole.getChildren().addAll(prompt, boxes);

      whole.setPadding(new Insets(50, 0, 0, 300));
      return whole;
   }
}
