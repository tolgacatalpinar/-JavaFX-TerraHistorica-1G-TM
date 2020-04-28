package Model;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.Serializable;

public class FileManager {
    private File save;
    private GameHandler game;
    private Map map;

    public FileManager(String gameId) throws IOException {
        save = createSave(gameId);
    }

    public static File createSave(String gameId) throws IOException  {
        File save = new File( gameId + ".txt");
        save.createNewFile();
        return save;
    }


    public void saveGame(GameHandler game, Map map) throws IOException   {
        FileOutputStream f = new FileOutputStream(save);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(game);
        o.close();
        f.close();
    }

    public void loadGame() throws IOException{
        try {
            FileInputStream fi = new FileInputStream(save);
            ObjectInputStream oi = new ObjectInputStream(fi);
            this.game = (GameHandler) oi.readObject();
            this.map = (Map) oi.readObject();
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

    public Map getMap() {
        return map;
    }

    public GameHandler getGame() {
        return game;
    }
}
