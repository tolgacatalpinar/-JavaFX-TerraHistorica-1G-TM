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
    private File fileName;
    private GameController gameController;
    private RoundController roundController;

    public FileManager(String gameId) throws IOException {
        fileName = createSave(gameId);
    }

    public static File createSave(String gameId) throws IOException  {
        File save = new File( gameId + ".txt");
        save.createNewFile();
        return save;
    }


    public void saveGame(GameController game, RoundController rounds) throws IOException   {
        FileOutputStream f = new FileOutputStream(fileName);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(game.getMap());
        o.writeObject(game.getPlayerList());
        o.writeObject(game.getCardsAndTiles());
        o.writeObject(game.getReligionArr());
        o.writeObject(game.getPlayerHandler());
        o.writeObject(game.getCurrentPlayer());
        o.writeObject(rounds);
        o.close();
        f.close();
    }

    public void loadGame( ) throws IOException{
        try {
           //File save = new File( "12.txt");
            FileInputStream fi = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fi);
            this.gameController.setMap((Map) oi.readObject());
            this.gameController.setPlayerList((Player[]) oi.readObject());
            this.gameController.setCardsAndTiles((CardsAndTiles) oi.readObject());
            this.gameController.setReligionArr((Religion[]) oi.readObject());
            this.gameController.setPlayerHandler((PlayerHandler) oi.readObject());
            this.gameController.setCurrentPlayer((Player) oi.readObject());
            this.roundController = (RoundController) oi.readObject();
            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public GameController getGameController() {
        return gameController;
    }

    public RoundController getRoundController() {
        return roundController;
    }
}
