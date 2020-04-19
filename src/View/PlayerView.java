package View;

import Model.Faction;
import Model.FactionSubclasses.*;
import Model.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class PlayerView extends BorderPane {

   ImageView imageView;
   String playerName;

   public PlayerView(Player player)
   {
      playerName = player.getNickName();
      Faction faction = player.getFaction();
      if( faction instanceof AliesterCrowley) {
         Image image = new Image("Images/FactionImages/Image_AleisterCrowley.jpeg");
         addImage(image);
      }
      else if(faction instanceof AmerigoVespucci) {
         Image image = new Image("../Images/FactionImages/Image_AmerigoVespucci.jpeg");
         addImage(image);
      }
//      else if(faction instanceof Buddha) {
//         image = null;
//      }
//      else if(faction instanceof DariusTheGreat)
//         image = null;
//      else if(faction instanceof ErikTheRed)
//         image = null;
//      else if(faction instanceof Gilgamesh)
//         image = null;
//      else if(faction instanceof HelenOfTroy)
//         image = null;
//      else if(faction instanceof HusseinTheTeaMaker)
//         image = null;
//      else if(faction instanceof LeonardoDaVinci)
//         image = null;
//      else if(faction instanceof MarieCurie)
//         image = null;
//      else if(faction instanceof MorganLeFay)
//         image = null;
//      else if(faction instanceof Ramesses)
//         image = null;
//      else if(faction instanceof StPatrick)
//         image = null;
//      else if(faction instanceof VladTheImpaler)
//         image = null;
   }
   public void addImage(Image image)
   {
      imageView.setImage(image);
   }


}
