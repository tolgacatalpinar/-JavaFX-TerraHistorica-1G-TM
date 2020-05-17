package Controller;

import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class SoundController {
   private static String gameSoundPath = "src/Sounds/InGameSound.mp3";
   private static String introSoundPath = "src/Sounds/introSound.mp3";
   public static int volume = 20;
   public static Group currentMusicGroup;

   public static void playGameMusic(double volume)
   {
      play(gameSoundPath, volume);
   }
   public static void playIntroMusic(double volume)
   {
      play(introSoundPath, volume);
   }
   private static void play(String soundPath, double volume)
   {
      if(currentMusicGroup != null)
      {
         currentMusicGroup = null;
      }
      currentMusicGroup = new Group();

      Media sound = new Media(new File(soundPath).toURI().toString());
      MediaPlayer mediaPlayer = new MediaPlayer(sound);
      mediaPlayer.setVolume((double)volume / 100);
      mediaPlayer.setAutoPlay(true);

      MediaView mediaView = new MediaView(mediaPlayer);
      currentMusicGroup.getChildren().add(mediaView);
   }
   public void setVolume(int volume)
   {
      this.volume = (volume % 100) / 2;
   }
}
