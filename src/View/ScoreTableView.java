package View;

import Model.FactionSubclasses.*;
import Model.Player;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import java.util.ArrayList;

    public class ScoreTableView extends BorderPane {

        public ScoreTableView(ArrayList<ArrayList<Integer>>[] religionScores, ArrayList<Integer>[] pathScores, Player[] playerList){
            this.setWidth(1200);
            this.setHeight(600);
            this.setBackground(new Background( new BackgroundImage( new Image("score_table_background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT)));


            VBox connection = new VBox();
            ImageView vic_p1 = new ImageView("victory_point.png");
            ImageView vic_p2 = new ImageView("victory_point.png");
            Label label1 = new Label("\n    8\n\n");
            ImageView sep_line = new ImageView("seperate_line.png");
            sep_line.setFitWidth(140);
            sep_line.setFitHeight(30);
            Label label2 = new Label("\n    4\n\n");
            ImageView sep_line2 = new ImageView("seperate_line.png");
            sep_line2.setFitWidth(140);
            sep_line2.setFitHeight(30);
            Label label3 = new Label("\n    2\n");
            label1.setFont(new Font("Stencil", this.getHeight()/17));
            label2.setFont(new Font("Stencil", this.getHeight()/17));
            label3.setFont(new Font("Stencil", this.getHeight()/17));
            vic_p1.setFitWidth(this.getWidth()/8);
            vic_p1.setFitHeight(this.getHeight()/5);
            vic_p2.setFitWidth(this.getWidth()/8);
            vic_p2.setFitHeight(this.getHeight()/5);
            VBox ranking1 = new VBox(vic_p2, label1 ,sep_line, label2,sep_line2, label3 );
            ranking1.setStyle("-fx-background-color: rgba(133,88,23,0.43);");
            HBox allTable = new HBox();
            allTable.getChildren().add(ranking1);
            for (int i = 0; i< 4;i++){
                VBox tempBox = new VBox();
                tempBox.setPrefHeight(500);
                tempBox.setCenterShape(true);
                ImageView religion_image;
                if (i == 0){
                    religion_image = new ImageView("islam_symbol.png");
                }else if (i == 1){
                    religion_image = new ImageView("chris_symbol.png");
                }else if (i == 2){
                    religion_image = new ImageView("judaism_symbol.png");
                }else{
                    religion_image = new ImageView("hinduism.png");
                }
                religion_image.setFitWidth(this.getWidth()/8);
                religion_image.setFitHeight(this.getHeight()/5);
                tempBox.getChildren().add(religion_image);

                for (int j = 0; j < 3; j++){
                    HBox tempHBox = new HBox();
                    if(j>0 && religionScores[i].get(j).size() > 0) {
                        ImageView sep_line4 = new ImageView("seperate_line.png");
                        sep_line4.setFitWidth(140);
                        sep_line4.setFitHeight(50);
                        tempBox.getChildren().add(sep_line4);
                    }
                    double align = religionScores[i].get(j).size();
                    for(int shaped = 1; shaped < religionScores[i].get(j).size();shaped++) {
                        Label label = new Label("\n");
                        tempBox.getChildren().add(label);
                        align = religionScores[i].get(j).size()/1.7;
                    }
                    for(int k = 0; k < religionScores[i].get(j).size(); k++){
                        int player_id = religionScores[i].get(j).get(k);
                        ImageView player_images = new ImageView(getImage(playerList[player_id]));
                        if(religionScores[i].get(j).size() == 1){
                            System.out.println("giiiiii");
                            Label label = new Label("\t");
                            tempHBox.getChildren().add(label);
                        }
                        player_images.setFitHeight(this.getHeight()/(5*align));
                        player_images.setFitWidth(this.getWidth()/(13*align));
                        tempHBox.setFillHeight(true);
                        tempHBox.getChildren().add(player_images);
                    }
                    tempBox.getChildren().add(tempHBox);
                    tempBox.setStyle("-fx-background-color: rgba(133,88,23,0.43);");
                }

                allTable.getChildren().add(tempBox);
            }
            Label label4 = new Label("\n   18\n\n");
            Label label5 = new Label("\n   12\n\n");
            Label label6 = new Label("\n    6");
            label4.setFont(new Font("Stencil", this.getHeight()/17));
            label5.setFont(new Font("Stencil", this.getHeight()/17));
            label6.setFont(new Font("Stencil", this.getHeight()/17));
            ImageView sep_line6 = new ImageView("seperate_line.png");
            sep_line6.setFitWidth(140);
            sep_line6.setFitHeight(30);
            ImageView sep_line7 = new ImageView("seperate_line.png");
            sep_line7.setFitWidth(140);
            sep_line7.setFitHeight(30);
            VBox ranking2 = new VBox(vic_p1, label4 , sep_line6, label5, sep_line7, label6 );
            allTable.getChildren().add(ranking2);
            ImageView con_image = new ImageView("hiclipart.com (1).png");
            con_image.setFitWidth(this.getWidth()/8);
            con_image.setFitHeight(this.getHeight()/5);
            connection.getChildren().add(con_image);
            for (int j = 0; j < 3; j++){
                HBox tempHBox = new HBox();
                if(j>0 && pathScores[j].size() > 0) {
                    ImageView sep_line8 = new ImageView("seperate_line.png");
                    sep_line8.setFitWidth(140);
                    sep_line8.setFitHeight(60);
                    connection.getChildren().add(sep_line8);
                }
                double align = pathScores[j].size();
                for(int shaped = 1; shaped < pathScores[j].size();shaped++) {
                    Label label = new Label("\n");
                    connection.getChildren().add(label);
                    align = pathScores[j].size()/1.7;
                }
                for(int k = 0; k < pathScores[j].size(); k++){
                    int player_id = pathScores[j].get(k);
                    ImageView player_images = new ImageView(getImage(playerList[player_id]));
                    if(pathScores[j].size() == 1){
                        System.out.println("giiiiii");
                        Label label = new Label("\t");
                        tempHBox.getChildren().add(label);
                    }
                    player_images.setFitHeight(this.getHeight()/(5*align));
                    player_images.setFitWidth(this.getWidth()/(13*align));
                    tempHBox.getChildren().add(player_images);
                }

                connection.getChildren().add(tempHBox);
                connection.setStyle("-fx-background-color: rgba(29,80,107,0.36);");
            }
            allTable.getChildren().add(connection);
            ranking2.setStyle("-fx-background-color: rgba(29,80,107,0.36);");
            this.setCenter(allTable);


        }
        public Image getImage(Player player){
            Image image = null;
            if (player.getFaction() instanceof AliesterCrowley) {
                image = new Image("file:src/Images/FactionImages/Image_AleisterCrowley.jpeg");
            } else if (player.getFaction() instanceof AmerigoVespucci) {
                image = new Image("file:src/Images/FactionImages/Image_AmerigoVespucci.jpeg");
            } else if (player.getFaction() instanceof Buddha) {
                image = new Image("file:src/Images/FactionImages/Image_Buddha.jpeg");
            } else if (player.getFaction() instanceof DariusTheGreat) {
                image = new Image("file:src/Images/FactionImages/Image_DariusTheGreat.jpeg");
            } else if (player.getFaction() instanceof ErikTheRed) {
                image = new Image("file:src/Images/FactionImages/Image_ErikTheRed.jpeg");
            } else if (player.getFaction() instanceof Gilgamesh) {
                image = new Image("file:src/Images/FactionImages/Image_Gilgamesh.jpeg");
            } else if (player.getFaction() instanceof HelenOfTroy) {
                image = new Image("file:src/Images/FactionImages/Image_HelenOfTroy.jpeg");
            } else if (player.getFaction() instanceof HusseinTheTeaMaker) {
                image = new Image("file:src/Images/FactionImages/Image_HusseinTheTeaMaker.jpeg");
            } else if (player.getFaction() instanceof LeonardoDaVinci) {
                image = new Image("file:src/Images/FactionImages/Image_LeonardoDaVinci.jpeg");
            } else if (player.getFaction() instanceof MarieCurie) {
                image = new Image("file:src/Images/FactionImages/Image_MarieCurie.jpeg");
            } else if (player.getFaction() instanceof MorganLeFay) {
                image = new Image("file:src/Images/FactionImages/Image_MorganLeFay.jpeg");
            } else if (player.getFaction() instanceof Ramesses) {
                image = new Image("file:src/Images/FactionImages/Image_Ramesses.jpeg");
            } else if (player.getFaction() instanceof StPatrick) {
                image = new Image("file:src/Images/FactionImages/Image_StPatrick.jpeg");
            } else if (player.getFaction() instanceof VladTheImpaler) {
                image = new Image("file:src/Images/FactionImages/Image_VladTheImpaler.jpeg");
            }
            return image;
        }
    }
