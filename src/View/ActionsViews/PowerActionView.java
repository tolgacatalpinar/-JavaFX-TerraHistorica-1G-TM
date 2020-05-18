package View.ActionsViews;

import Controller.TerrainController;
import Model.Map;
import Model.Player;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PowerActionView extends BorderPane {
    private int  selection = -1;
    Button select = new Button("Select");

    public PowerActionView(){
        BackgroundImage bg = new BackgroundImage(new Image("religion_bg.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(bg));
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        select.setMaxHeight(100);
        select.setMinWidth(100);
        BorderPane border_bottom = new BorderPane();
        this.setBottom(border_bottom);
        border_bottom.setCenter(select);
        for (int i = 0; i < 6; i++) {
            ImageView power_middle = new ImageView("arrow.png");
            ImageView power_image = new ImageView("power.png");
            ImageView bridge = new ImageView("bridge.png");
            ImageView priest = new ImageView("priest.png");
            ImageView worker =  new ImageView("worker.png");
            ImageView gold = new ImageView("gold.png");
            ImageView spade =  new ImageView("spade.png");
            Label label1 = new Label("\n3");
            label1.setTextFill(Color.WHITE);
            Label label2 = new Label("\n2");
            label2.setTextFill(Color.WHITE);
            label1.setFont(new Font("Stencil", 40));
            label2.setFont(new Font("Stencil", 40));
            label1.setOpacity(0.6);
            label2.setOpacity(0.6);
            power_middle.setFitHeight(150);
            power_middle.setFitWidth(150);
            priest.setFitWidth(150);
            priest.setFitHeight(150);
            worker.setFitWidth(150);
            worker.setFitHeight(150);
            gold.setFitWidth(150);
            gold.setFitHeight(150);
            spade.setFitWidth(150);
            spade.setFitHeight(150);
            power_image.setFitWidth(150);
            power_image.setFitHeight(150);
            bridge.setFitHeight(150);
            bridge.setFitWidth(150);
            HBox option;
            if(i == 0){
                label2.setText("\n1");
                option = new HBox(power_image, label1, power_middle, bridge,label2 );
            }else if (i == 1) {
                label2.setText("\n1");
                option = new HBox(power_image, label1, power_middle, priest,label2 );
            }else if (i == 2) {
                label1.setText("\n4");
                option = new HBox(power_image, label1 , power_middle, worker, label2 );
            }else if (i == 3) {
                label1.setText("\n4");
                label2.setText("\n7");
                option = new HBox(power_image, label1, power_middle,gold , label2);
            }else if (i == 4) {
                label1.setText("\n4");
                label2.setText("\n1");
                option = new HBox(power_image, label1, power_middle,spade, label2);
            }else {
                label1.setText("\n6");
                option = new HBox(power_image, label1, power_middle, spade,label2 );
            }
            GridPane tempPane = new GridPane();
            option.setMaxWidth(tempPane.getWidth() / 3);
            option.setMaxHeight(tempPane.getHeight() / 3);
            tempPane.add(option,0,0);
            tempPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    DropShadow borderGlow = new DropShadow();
                    borderGlow.setColor(Color.ORANGE);
                    borderGlow.setOffsetX(0f);
                    borderGlow.setOffsetY(0f);
                    borderGlow.setWidth(50);
                    borderGlow.setHeight(50);
                    tempPane.setEffect(borderGlow);
                }
            });
            int finalI = i;
            tempPane.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (selection != finalI)
                        tempPane.setEffect(null);

                }
            });

            tempPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setSelection(finalI);
                    for (int i = 0; i < 6; i++) {
                        if (i != getSelection())
                            gridPane.getChildren().get(i).setEffect(null);
                    }
                }
            });
            gridPane.add(tempPane, i % 2, i / 2);
        }

        this.setCenter(gridPane);


    }
    public int getSelection() {
        return selection;
    }
    public Button getSelectButton(){
        return this.select;
    }
    public void setSelection(int selection) {
        this.selection = selection;
    }


}
