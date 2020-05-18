package Model;
import Controller.GameController;
import Controller.RoundController;
import Model.CardsAndTiles.CardsAndTiles;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;  // Import this class to handle errors


public class FileManager {

    public FileManager() throws IOException {
        System.out.println("constructor");
    }

    public  static File createSave(String gameId) throws IOException  {
        File save = new File( gameId + ".txt");
        save.createNewFile();
        return save;
    }


    public  static void saveGame(GameController game, RoundController rounds, File fileName) throws IOException   {
        FileOutputStream f = new FileOutputStream(fileName);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(game.getMap());
        o.writeObject(game.getPlayerList());
        o.writeObject(game.getCardsAndTiles());
        o.writeObject(game.getReligionArr());
        o.writeObject(game.getPlayerHandler());
        o.writeObject(game.getCurrentPlayer());
        o.writeObject(game.getRoundController());
        o.close();
        f.close();
    }

    public static GameController loadGame(File fileName, GameController gameController) throws IOException{
        try {
           //File save = new File( "12.txt");
            FileInputStream fi = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fi);

             gameController.setMap((Map)oi.readObject());
             gameController.setPlayerList((Player[]) oi.readObject());
             gameController.setCardsAndTiles((CardsAndTiles) oi.readObject());
             gameController.setReligionArr((Religion[]) oi.readObject());
             gameController.setPlayerHandler((PlayerHandler) oi.readObject());
             gameController.setCurrentPlayer((Player) oi.readObject());
             gameController.setRoundController((RoundController) oi.readObject());

            oi.close();
            fi.close();
            return gameController;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /// file manager v0.9
}
