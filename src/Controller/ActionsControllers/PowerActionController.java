package Controller.ActionsControllers;

import Model.GameHandler;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PowerActionController {
    private int selection = -1;

    public int getSelection() {
        return selection;
    }

    private void setSelection(int selection) {
        this.selection = selection;
    }

    public void showPowerActions(GameHandler gameHandler){

        BorderPane border = new BorderPane();
        BackgroundImage bg = new BackgroundImage( new Image("religion_bg.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        border.setBackground(new Background(bg));
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Button select = new Button("Select");
        select.setMaxHeight(100);
        select.setMinWidth(100);
        BorderPane border_bottom = new BorderPane();
        border.setBottom(border_bottom);
        border_bottom.setCenter(select);

        for(int i = 0; i < 6; i++){
            GridPane tempPane = new GridPane();
            ImageView power_left = new ImageView("chris_track.png");
            ImageView power_right = new ImageView("juda_track.png");
            ImageView power_middle = new ImageView("purple_arrow.png");
            power_middle.setFitWidth(tempPane.getWidth()/3);
            power_middle.setFitHeight(tempPane.getHeight()/3);
            power_left.setFitWidth(tempPane.getWidth()/3);
            power_left.setFitHeight(tempPane.getHeight()/3);
            power_right.setFitWidth(tempPane.getWidth()/3);
            power_right.setFitHeight(tempPane.getHeight()/3);
            tempPane.add(power_left,0,0);
            tempPane.add(power_middle,1,0);
            tempPane.add(power_right,2,0);
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
                    if(selection != finalI )
                        tempPane.setEffect(null);;
                }
            });

            tempPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    setSelection(finalI);
                    for(int i = 0; i < 6; i++){
                        if(i != getSelection())
                            gridPane.getChildren().get(i).setEffect(null);
                    }
                }
            });
            gridPane.add(tempPane,i%3, i/3);
        }
        select.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                int chosen = getSelection();
                System.out.println("Selected"+ chosen);
            }
        });
        border.setCenter(gridPane);
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(border, 1100, 600);
        dialog.setScene(dialogScene);
        dialog.setTitle("Power Action");
        dialog.setResizable(false);
        //update(gridPane,status);
        //Find religion to add as size (y coordinate)%(1/4 of anchor pane's size) to replace choice box.
        dialog.show();


    }
}
