package View;

import Model.Faction;
import Model.FactionSubclasses.*;
import Model.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;

import java.io.Serializable;

public class PlayerView extends BorderPane  {
   class ResourceLabel extends Label
   {
      public ResourceLabel(String label)
      {
         super(label);
         setStyle("-fx-font-weight: bold");
      }
   }
   ImageView imageView;
   String playerName;
   Player player;
   final int IMAGE_SIZE = 20;

   public PlayerView(Player player) {
      playerName = player.getNickName();

      this.player = player;

      Faction faction = player.getFaction();

      imageView = new ImageView();

      if (faction instanceof AliesterCrowley) {
         Image image = new Image("file:src/Images/FactionImages/Image_AleisterCrowley.jpeg");
         addImage(image);
         getStyleClass().add("swamp");
      } else if (faction instanceof AmerigoVespucci) {
         Image image = new Image("file:src/Images/FactionImages/Image_AmerigoVespucci.jpeg");
         addImage(image);
         getStyleClass().add("lakes");
      } else if (faction instanceof Buddha) {
         Image image = new Image("file:src/Images/FactionImages/Image_Buddha.jpeg");
         addImage(image);
         getStyleClass().add("plains");
      } else if (faction instanceof DariusTheGreat) {
         Image image = new Image("file:src/Images/FactionImages/Image_DariusTheGreat.jpeg");
         addImage(image);
         getStyleClass().add("desert");
      } else if (faction instanceof ErikTheRed) {
         Image image = new Image("file:src/Images/FactionImages/Image_ErikTheRed.jpeg");
         addImage(image);
         getStyleClass().add("lakes");
      } else if (faction instanceof Gilgamesh) {
         Image image = new Image("file:src/Images/FactionImages/Image_Gilgamesh.jpeg");
         addImage(image);
         getStyleClass().add("wasteland");
      } else if (faction instanceof HelenOfTroy) {
         Image image = new Image("file:src/Images/FactionImages/Image_HelenOfTroy.jpeg");
         addImage(image);
         getStyleClass().add("forest");
      } else if (faction instanceof HusseinTheTeaMaker) {
         Image image = new Image("file:src/Images/FactionImages/Image_HusseinTheTeaMaker.jpeg");
         addImage(image);
         getStyleClass().add("plains");
      } else if (faction instanceof LeonardoDaVinci) {
         Image image = new Image("file:src/Images/FactionImages/Image_LeonardoDaVinci.jpeg");
         addImage(image);
         getStyleClass().add("mountains");
      } else if (faction instanceof MarieCurie) {
         Image image = new Image("file:src/Images/FactionImages/Image_MarieCurie.jpeg");
         addImage(image);
         getStyleClass().add("swamp");
      } else if (faction instanceof MorganLeFay) {
         Image image = new Image("file:src/Images/FactionImages/Image_MorganLeFay.jpeg");
         addImage(image);
         getStyleClass().add("swamp");
      } else if (faction instanceof Ramesses) {
         Image image = new Image("file:src/Images/FactionImages/Image_Ramesses.jpeg");
         addImage(image);
         getStyleClass().add("desert");
      } else if (faction instanceof StPatrick) {
         Image image = new Image("file:src/Images/FactionImages/Image_StPatrick.jpeg");
         addImage(image);
         getStyleClass().add("mountains");
      } else if (faction instanceof VladTheImpaler) {
         Image image = new Image("file:src/Images/FactionImages/Image_VladTheImpaler.jpeg");
         addImage(image);
         getStyleClass().add("wasteland");
      }
      addAllResources();
      this.setPadding(new Insets(0, 20, 0, 0));
   }
   public void updateView(Player player)
   {
      VBox resources = new VBox();
      ResourceLabel playerName = new ResourceLabel(this.playerName);
      playerName.setTextFill(Color.WHITE);
      playerName.setPadding(new Insets(0, 0, 3, 0));
      HBox gold = getSingleResourceView(new Image("gold.png"), player.getGoldNum(), player.getGoldIncome());
      HBox priest = getSingleResourceView(new Image("priest.png"), player.getPriestNum(), player.getPriestNum());
      HBox worker = getSingleResourceView(new Image("worker.png"), player.getWorkerNum(), player.getWorkerIncome());
      HBox power = getSingleResourceView(new Image("power.png"), player.getBowlOnePower(), player.getBowlTwoPower(), player.getBowlThreePower(), player.getPowerIncome());
      HBox victory = getSingleResourceView(new Image("victory_point.png"), player.getVictoryPointNum());
      HBox spade = getSingleResourceView(new Image("spade.png"), player.getFreeSpade(), player.getTerraformWorkerCost());
      HBox shipping = getSingleResourceView(new Image("shipping.png"), player.getShipLevel());
      resources.getChildren().addAll(gold, priest, worker, power, victory, spade, shipping);
      resources.setPadding( new Insets(10, 0, 0, 20));
      setRight(null);
      setRight(resources);

   }
   public void addImage(Image image) {

      imageView.setImage(image);
      imageView.setFitWidth(80);
      imageView.setFitHeight(120);
      imageView.setCache(true);
      setCenter(imageView);
   }
   public void addAllResources()
   {
      VBox resources = new VBox();
      ResourceLabel playerName = new ResourceLabel(this.playerName);
      playerName.setTextFill(Color.WHITE);
      playerName.setPadding(new Insets(0, 0, 3, 0));
      HBox gold = getSingleResourceView(new Image("gold.png"), player.getGoldNum(), player.getGoldIncome());
      HBox priest = getSingleResourceView(new Image("priest.png"), player.getPriestNum(), player.getPriestNum());
      HBox worker = getSingleResourceView(new Image("worker.png"), player.getWorkerNum(), player.getWorkerIncome());
      HBox power = getSingleResourceView(new Image("power.png"), player.getBowlOnePower(), player.getBowlTwoPower(), player.getBowlThreePower(), player.getPowerIncome());
      HBox victory = getSingleResourceView(new Image("victory_point.png"), player.getVictoryPointNum());
      HBox spade = getSingleResourceView(new Image("spade.png"), player.getFreeSpade(), player.getTerraformWorkerCost());
      HBox shipping = getSingleResourceView(new Image("shipping.png"), player.getShipLevel());
      resources.getChildren().addAll(gold, priest, worker, power, victory, spade, shipping);
      resources.setPadding( new Insets(10, 0, 0, 20));
      setRight(resources);

   }
   public HBox getSingleResourceView( Image image, int number1)
   {
      HBox resourceBox = new HBox();
      ImageView resourceImage = new ImageView(image);
      resourceImage.setFitWidth(IMAGE_SIZE);
      resourceImage.setFitHeight(IMAGE_SIZE);
      ResourceLabel label = new ResourceLabel("\t" + number1);

      label.setTextFill(Color.WHITE);
      label.getStyleClass().add("textShadow");

      resourceBox.getChildren().addAll(resourceImage, label);
      return resourceBox;
   }
   public HBox getSingleResourceView( Image image, int number1, int number2)
   {
      HBox resourceBox = new HBox();
      ImageView resourceImage = new ImageView(image);
      resourceImage.setFitWidth(IMAGE_SIZE);
      resourceImage.setFitHeight(IMAGE_SIZE);
      ResourceLabel current = new ResourceLabel("\t" + number1);
      ResourceLabel in = new ResourceLabel("\t+" + number2);
      current.setTextFill(Color.WHITE);
      current.getStyleClass().add("textShadow");
      in.setTextFill(Color.GREEN);
      in.getStyleClass().add("textShadow");
      resourceBox.getChildren().addAll(resourceImage, current, in);
      return resourceBox;
   }
   public HBox getSingleResourceView( Image image, int number1, int number2, int number3, int number4)
   {
      HBox resourceBox = new HBox();
      ImageView resourceImage = new ImageView(image);
      resourceImage.setFitWidth(IMAGE_SIZE);
      resourceImage.setFitHeight(IMAGE_SIZE);
      ResourceLabel label1 = new ResourceLabel("\t" + number1);
      ResourceLabel label2 = new ResourceLabel("\t" + number2);
      ResourceLabel label3 = new ResourceLabel("\t" + number3);
      ResourceLabel label4 = new ResourceLabel("\t+" + number4);
      label1.setTextFill(Color.WHITE);
      label1.getStyleClass().add("textShadow");

      label2.setTextFill(Color.WHITE);
      label2.getStyleClass().add("textShadow");

      label3.setTextFill(Color.WHITE);
      label3.getStyleClass().add("textShadow");

      label4.setTextFill(Color.GREEN);
      label4.getStyleClass().add("textShadow");

      resourceBox.getChildren().addAll(resourceImage, label1, label2, label3, label4);
      return resourceBox;
   }

}
