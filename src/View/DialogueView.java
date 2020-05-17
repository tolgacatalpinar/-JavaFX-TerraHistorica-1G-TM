package View;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DialogueView {

   int WIDTH = 100;
   int HEIGHT = 100;
   public Stage getStage(String title, Parent component, Image backgroundImage)
   {
      Stage stage = new Stage();
      Scene scene = new Scene(component, WIDTH, HEIGHT);
      stage.setScene(scene);
      return stage;
   }

}
