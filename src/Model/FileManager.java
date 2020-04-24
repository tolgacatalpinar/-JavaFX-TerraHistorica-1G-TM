package Model;
import Model.CardsAndTiles.*;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileManager {
    Player[] players;
    Map map;
    CardsAndTiles cardsAndTiles;
    File save;
    FileWriter writer;

    public FileManager(GameHandler game, Map map) throws IOException {
        this.map = map;
        players = game.getPlayerList();
        cardsAndTiles =game.getCardsAndTiles();
        save = createSave(game.gameId);
        writer = new FileWriter(save);
    }
    public void saveGame(File file, GameHandler game)throws IOException  {
        saveRounds(file, players, 0, 1, writer);
        savePlayers(file, players, writer);
        saveCardsAndTiles(file, cardsAndTiles, writer);
        writer.close();
    }

    public static File createSave(String gameId) throws IOException  {
        File save = new File( gameId + ".txt");
        save.createNewFile();
        return save;
    }

    public void savePlayers(File file, Player[] players, FileWriter writer) throws IOException  {
        int playerCount = players.length;
        for(int i = 0; i < playerCount; i++){
            writer.write(players[i].getPlayerId() + "\n" );
            writer.write(players[i].getNickName() + "\n" );
            writer.write(players[i].getFaction() + "\n" ); // Faction Object
            writer.write(players[i].getVictoryPointNum() + "\n" );
            writer.write(players[i].getGoldNum() + "\n" );
            writer.write(players[i].getGoldIncome() + "\n" );
            writer.write(players[i].getWorkerNum() + "\n" );
            writer.write(players[i].getWorkerIncome() + "\n" );
            writer.write(players[i].getCultBonusIncome() + "\n" );
            writer.write(players[i].getPriestNum() + "\n" );
            writer.write(players[i].getPriestIncome() + "\n" );
            writer.write(players[i].getSpadeLevel() + "\n" );
            writer.write(players[i].getShipLevel() + "\n" );
            writer.write(players[i].getShipLevel() + "\n" );
            writer.write(players[i].getDwellingNum() + "\n" );
            writer.write(players[i].getTradingPostNum() + "\n" );
            writer.write(players[i].getTempleNum() + "\n" );
            writer.write(players[i].getSanctuaryNum() + "\n" );
            writer.write(players[i].getStrongholdNum() + "\n" );
            writer.write(players[i].getBridgeNum() + "\n" );
            writer.write(players[i].getPerBuildingIncome() + "\n" );
            writer.write(players[i].getTerraformWorkerCost() + "\n" );
            writer.write(players[i].getReligionTrackInventory() + "\n" );
            writer.write(players[i].getSpadeInventory() + "\n" );
            writer.write(players[i].getTownPowerValue() + "\n" );
            writer.write(players[i].getKey() + "\n" );
            writer.write(players[i].getSpecialActionToken() + "\n" );
            writer.write("\n");
        }
        writer.write("separator2" + "\n");
    }
    public void saveRounds(File file, Player[] orderedPlayers, int turn, int round, FileWriter writer) throws IOException  {
        int playerCount = players.length;
        writer.write(playerCount + "\n");
        writer.write(turn);
        writer.write(round);
        writer.write("separator1" + "\n");
    }

    public void saveCardsAndTiles(File file, CardsAndTiles cardsTiles, FileWriter writer) throws IOException  {
        ArrayList <FavorTile> favors = cardsTiles.getFavorTiles();
        ArrayList <ScoringTile> scores = cardsTiles.getScoringTiles();
        ArrayList <TownTile> towns = cardsTiles.getTownTiles();
        ArrayList <BonusCard> bonuses = cardsTiles.getBonusCards();
        ArrayList <ScoringTile> selectedScores = cardsTiles.getSelectedScoringTiles();
        ArrayList <BonusCard> selectedBonuses = cardsTiles.getSelectedBonusCards();
    }


}
