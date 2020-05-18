package View.DialogueViews;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class DialogueImageButton extends Button {
   int width = 300;
   int height = 500;
   public DialogueImageButton(String imagePath)
   {
      this.setPrefSize(width, height);
      //this.setMinSize(width, height);
      this.setBackground( new Background( new BackgroundImage(new Image(imagePath), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
      this.styleProperty().bind(Bindings.when(this.hoverProperty())
              .then("-fx-effect: dropshadow( gaussian , rgba(255,255,255,255) , 30,0.5,0,1 );")
              .otherwise(""));
   }


}
