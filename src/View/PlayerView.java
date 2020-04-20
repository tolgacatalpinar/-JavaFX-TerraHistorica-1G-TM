package View;

import Model.Faction;
import Model.FactionSubclasses.*;
import Model.Player;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;

public class PlayerView extends BorderPane {

   ImageView imageView;
   String playerName;
   Player player;

   public PlayerView(Player player) {
      playerName = player.getNickName();
      this.player = player;
      Faction faction = player.getFaction();
      imageView = new ImageView();
      if (faction instanceof AliesterCrowley) {
         Image image = new Image("file:src/Images/FactionImages/Image_AleisterCrowley.jpeg");
         addImage(image);
      } else if (faction instanceof AmerigoVespucci) {
         Image image = new Image("file:src/Images/FactionImages/Image_AmerigoVespucci.jpeg");
         addImage(image);
      } else if (faction instanceof Buddha) {
         Image image = new Image("file:src/Images/FactionImages/Image_Buddha.jpeg");
         addImage(image);
      } else if (faction instanceof DariusTheGreat) {
         Image image = new Image("file:src/Images/FactionImages/Image_DariusTheGreat.jpeg");
         addImage(image);
      } else if (faction instanceof ErikTheRed) {
         Image image = new Image("file:src/Images/FactionImages/Image_ErikTheRed.jpeg");
         addImage(image);
      } else if (faction instanceof Gilgamesh) {
         Image image = new Image("file:src/Images/FactionImages/Image_Gilgamesh.jpeg");
         addImage(image);
      } else if (faction instanceof HelenOfTroy) {
         Image image = new Image("file:src/Images/FactionImages/Image_HelenOfTroy.jpeg");
         addImage(image);
      } else if (faction instanceof HusseinTheTeaMaker) {
         Image image = new Image("file:src/Images/FactionImages/Image_HusseinTheTeaMaker.jpeg");
         addImage(image);
      } else if (faction instanceof LeonardoDaVinci) {
         Image image = new Image("file:src/Images/FactionImages/Image_LeonardoDaVinci.jpeg");
         addImage(image);
      } else if (faction instanceof MarieCurie) {
         Image image = new Image("file:src/Images/FactionImages/Image_MarieCurie.jpeg");
         addImage(image);
      } else if (faction instanceof MorganLeFay) {
         Image image = new Image("file:src/Images/FactionImages/Image_MorganLeFay.jpeg");
         addImage(image);
      } else if (faction instanceof Ramesses) {
         Image image = new Image("file:src/Images/FactionImages/Image_Ramesses.jpeg");
         addImage(image);
      } else if (faction instanceof StPatrick) {
         Image image = new Image("file:src/Images/FactionImages/Image_StPatrick.jpeg");
         addImage(image);
      } else if (faction instanceof VladTheImpaler) {
         Image image = new Image("file:src/Images/FactionImages/Image_VladTheImpaler.jpeg");
         addImage(image);
      }
      addIncomes();
      addResources();
      this.setPadding(new Insets(20, 50, 20, 0));
   }

   public void addImage(Image image) {
      imageView.setImage(image);
      imageView.setFitWidth(80);
      imageView.setFitHeight(120);
      imageView.setCache(true);
      setCenter(imageView);
      //this.insets
      //setMargin(this, new Insets(60));
   }
   public void addIncomes()
   {
      VBox incomes = new VBox();
      Label playerName = new Label(this.playerName);
      playerName.setPadding(new Insets(0, 0, 3, 0));

      Label goldIn = new Label("Gold Income: " + player.getGoldIncome());
      Label priestIn = new Label("Priest Income: " + player.getPriestIncome());
      Label workerIn = new Label("Worker Income: " + player.getWorkerIncome());
      Label cultIn = new Label("Cult Income: " + player.getCultBonusIncome());
      Label powerIn = new Label("Power Income: " + player.getPowerIncome());

      incomes.getChildren().addAll(playerName, goldIn, priestIn, workerIn, cultIn, powerIn);
      incomes.setPadding( new Insets(10, 0, 0, 10));
      setRight(incomes);
   }
   public void addResources()
   {
      VBox wholeBlock = new VBox();
      HBox resources1 = new HBox();
      HBox resources2 = new HBox();

      Label gold = new Label("Gold: " + player.getGoldNum());
      gold.setPadding( new Insets(0, 3, 0, 0));

      Label priest = new Label("Priest: " + player.getPriestNum());
      priest.setPadding( new Insets(0, 3, 0, 0));

      Label worker = new Label("Worker: " + player.getWorkerNum());
      worker.setPadding( new Insets(0, 3, 0, 0));

      // POWER IS TO BE ADDED
      Label victory = new Label("Victory: " + player.getVictoryPointNum());
      victory.setPadding( new Insets(0, 3, 0, 0));

      resources1.getChildren().addAll(gold, priest);
      resources2.getChildren().addAll(worker, victory);

      resources1.setPadding( new Insets(2, 0, 0, 0));
      resources2.setPadding( new Insets(2, 0, 0, 0));

      wholeBlock.getChildren().addAll(resources1, resources2);
      setBottom(wholeBlock);


   }


}
