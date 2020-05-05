package Model;

import Model.ReligionSubclasses.*;
import Model.CardsAndTiles.BonusCard;
import Model.CardsAndTiles.CardsAndTiles;
import Model.CardsAndTiles.FavorTile;
import Model.CardsAndTiles.TownTile;
import Model.FactionSubclasses.DariusTheGreat;
import java.io.Serializable;
import java.util.ArrayList;

public class GameHandler implements Serializable{

    int currentPlayerId;
    Player[] playerList;
    CardsAndTiles cardsAndTiles;
    Religion[] religions;
    Map map;
    int playerCount;
    PlayerHandler playerHandler;
    int currentRound;
    final int MAX_ROUND = 6;
    public GameHandler(Player[] playerList, int playerSize)
    {
        //Initialize map
        map = new Map();

        //Get Players
        playerCount = playerSize;
        currentPlayerId = 0;
        this.playerList = playerList;
        playerHandler = new PlayerHandler();

        cardsAndTiles = new CardsAndTiles(playerSize);

        currentRound = 0;

        //Initialize religions
        religions = new Religion[4];
        int [] player_initial_islam = new int[playerSize];
        int [] player_initial_chirst = new int[playerSize];
        int [] player_initial_jew = new int[playerSize];
        int [] player_initial_hindu = new int[playerSize];
        for (int j = 0; j< playerSize; j++) {
                player_initial_islam[j] = playerList[j].getInitialIslam();
                player_initial_chirst[j] = playerList[j].getInitialChristianity();
                player_initial_jew[j] = playerList[j].getInitialJudaism();
                player_initial_hindu[j] = playerList[j].getInitialHinduism();
        }
        religions[0] = new Islam(playerSize, player_initial_islam);
        religions[1] = new Christianity(playerSize, player_initial_chirst);
        religions[2] = new Jewish(playerSize, player_initial_jew);
        religions[3] = new Hinduism(playerSize, player_initial_hindu);

    }


    /**
     * Will be called after ending a turn
     */
    public void endTurn() {
        currentPlayerId++;
        if(currentPlayerId >= playerCount) {
            currentPlayerId = 0;
        }
    }

    /**
     * Round is over, update resources and pass to next round
     */
    public void endRound() {

        for(int i = 0; i < playerCount; i++){
            playerHandler.updateResources(playerList[i]);
        }
        currentRound++;
        if(currentRound > MAX_ROUND) {
            System.out.println("GAME OVER");
        }
    }

    public void passRound() {
        playerHandler.passRound(playerList[currentPlayerId]);

        if(isRoundOver()) {
            endRound();
        }
        endTurn();
    }

    private boolean isRoundOver() {

        for (int i = 0; i < playerCount; i++) {
            if (!playerList[i].isRoundPassed()) {
                return false;
            }
        }
        return true;
    }

    public void buildDwelling(Space space) {

        if(currentRound > 0) { //Not initial dwellings, check for resources
            if(playerHandler.buildStructure(playerList[currentPlayerId], "Dwelling", false) == 1) {
                space.setPlayer(playerList[currentPlayerId]);
                map.buildDwelling(space, playerList[currentPlayerId].getTerrainTile(), false);
            }
        }
        if(currentRound == 0) { //Initial dwellings, do not check for resources
            playerHandler.buildInitialDwelling(playerList[currentPlayerId]);
            space.setPlayer(playerList[currentPlayerId]);
            map.buildDwelling(space, playerList[currentPlayerId].getTerrainTile(), true);
        }

    }

    public boolean upgradeStructure(Space space, String structure) {

        ArrayList<Player> adjacentPlayers = map.adjacentPlayers(space,  playerList[currentPlayerId].getTerrainTile()); //Method is not implemented yet

        if(playerHandler.buildStructure(playerList[currentPlayerId], structure, adjacentPlayers !=null) == 1) {
            if(!map.upgradeStructure(space,  playerList[currentPlayerId].getTerrainTile(), structure)) {
                System.out.println("You cannot upgrade current building to " + structure);
                return false;
            }
            return true; //Structure is updated successfully
        }
        return false; //Structure cannot be upgraded
    }

    /**
     * This method is called each time after upgrading a structure is successful
     * and for each player in the adjacent player list that accepts to exchange power and vp
     */
    public void acceptPowerFromAdjacentOpponent(Player player, int powerVal) {
                playerHandler.acceptPowerFromAdjacentOpponent(powerVal, player);
    }

    /**
     * This method is called after upgrading a structure, to get the adjacent opponents
     * and ask them if they want to exchange power
     * @param space that contains upgraded structure of current player
     * @return the list that are adjacent to the space
     */
    public ArrayList<Player> getAdjacentPlayerList(Space space) {
        return map.adjacentPlayers(space, playerList[currentPlayerId].getTerrainTile());
    }

    /**
     * Getting adjacent spaces to build dwelling
     * @param space
     * @return
     */
    public ArrayList<Space> getAdjacentSpaces(Space space) {
        //return map.adjacencyList(space);
        return null;
    }

    public void terraform(Space space, String toTransform) {

        if (playerHandler.terraform(playerList[currentPlayerId]) ) {
            map.transformTerrain(space, toTransform);
            //Successful terraforming
        }
    }

    public void upgradeSpadeLevel() {

        playerHandler.upgradeSpadeLevel(playerList[currentPlayerId]);
    }

    public void upgradeShippingLevel() {
        playerHandler.upgradeShippingLevel(playerList[currentPlayerId]);
    }

    public void placePriest() {

    }

    public void buildBridge() {

    }

    public void isTownFound() {

    }

    /**
     * May be moved from playerhandler to gamehandler instead of using twice
     * @param exchange
     */
    public void exchangeResources(String exchange){
        playerHandler.exchangeResources(playerList[currentPlayerId], exchange);
    }

    /**
     * May be moved from playerhandler to gamehandler instead of using twice
     * @param powerAction
     */
    public void usePowerAction(String powerAction) {
        if(playerHandler.usePowerAction( powerAction, playerList[currentPlayerId])) {
            if( powerAction.equals("power to bridge")) {
                buildBridge();
            }

        }

    }



    /**
     * playerChoseBonusCard(cardsAndTiles.bonusCards.get(cardIndex), player, playerID);
     * notTakenCard is not used.
     * TODO
     * @param bonusCard which is chosen by the player.
     * @param player who select bonusCard.
     */
    public void playerChoseBonusCard(BonusCard bonusCard, Player player) {
        if (bonusCard.isPlayerOcupied()) {
            System.err.println(" Please choose different Bonus Card \n" +
                    "This card was chosen by another player");

        }
        else {
            int playerId = player.getPlayerId();
            int previousCardId = cardsAndTiles.findPlayerCard(playerId);
            if (previousCardId != -1) {
                cardsAndTiles.bonusCards.get(previousCardId).setPlayerOcupied(false);
                cardsAndTiles.bonusCards.get(previousCardId).setPlayerId(-1);
                player.setShipLevel(player.getShipLevel() - cardsAndTiles.bonusCards.get(previousCardId).getShippingRange());
                player.getSpecialActionToken().isCultTack = false;
                player.getSpecialActionToken().isSpade = false;
                PlayerHandler.returnBonusCard(player);
            }
            bonusCard.setPlayerOcupied(true);
            bonusCard.setPlayerId(playerId);
            player.setGoldNum(player.getGoldIncome() + bonusCard.getGoldBonus());
            player.setWorkerNum(player.getWorkerIncome() + bonusCard.getWorkerBonus());
            player.addPowerToBowl(bonusCard.getPowerBonus());
            player.setShipLevel(player.getShipLevel() + bonusCard.getShippingRange());
            player.setPriestNum(player.getPriestIncome() + bonusCard.getPriestBonus());
            player.getSpecialActionToken().isCultTack = bonusCard.isSpecialCult();
            player.getSpecialActionToken().isSpade = bonusCard.isSpacialSpade();
            player.setHavingDwellingBonus(bonusCard.isDwelling());
            player.setHavingSanctuary(bonusCard.isSanctuary());
            player.setHavingTradeHouse(bonusCard.isTradeHouse());
        }
    }


    public void playerChooseFavorTile(FavorTile favorTile,Player player, Religion religion){
        if(favorTile.getPlayerIds().size() >= favorTile.getNumberOfPlayer() ){
            System.err.println("You cannot choose this Favor");
        }
        else
        {
            favorTile.getPlayerIds().add((Integer) player.getPlayerId() );
            player.addPowerToBowl(religion.updateReligion(favorTile.getIslamBonus(),player.getPlayerId(),player.getKey()));
            player.addPowerToBowl(religion.updateReligion(favorTile.getChristianityBonus(),player.getPlayerId(),player.getKey()));
            player.addPowerToBowl(religion.updateReligion(favorTile.getJewBonus(),player.getPlayerId(),player.getKey()));
            player.addPowerToBowl(religion.updateReligion(favorTile.getHinduismBonus(),player.getPlayerId(),player.getKey()));

            religion.updateReligion(favorTile.getChristianityBonus(),player.getPlayerId(),player.getKey());

            player.setTownPowerValue(6);
            player.addPowerToBowl( favorTile.getPowerBonus());
            player.setWorkerNum(player.getWorkerNum() + favorTile.getWorkerBonus());
            player.setGoldNum(player.getGoldNum() + favorTile.getGoldBonus());
            player.setVictoryPointNum(player.getVictoryPointNum() + favorTile.getVictoryPoint());
            player.setUpgradeToTradingPostBonus(favorTile.isTradingHouse());
            player.setPassingTradingPostBonus(favorTile.isPassingBonusForTradingHouse());
            player.setBuildingDwellingBonus(favorTile.isDwellingBonus());
            if(favorTile.isSpecialCult())
            player.getSpecialActionToken().isCultTack = favorTile.isSpecialCult();

        }
    }

    public void playerChooseTownTile(TownTile townTile, Player player, Religion religion){
        if(townTile.isOccupied()){
            System.err.println("This Tile was chosen by another player");
        }
        else
        {
            townTile.setPlayerId(player.getPlayerId());
            player.addPowerToBowl( townTile.getPowerBonus());
            player.setPriestNum(player.getPriestNum() + townTile.getPriestBonus());
            player.setVictoryPointNum(player.getVictoryPointNum() + townTile.getVictoryBonus());
            player.setWorkerNum(player.getWorkerNum() + townTile.getWorkerBonus());
            player.setGoldNum(player.getGoldNum() + townTile.getGoldBonus());
            player.addPowerToBowl(religion.updateReligion(townTile.getIslamPoint(),player.getPlayerId(),player.getKey()));
            player.addPowerToBowl(religion.updateReligion(townTile.getChristianityPoint(),player.getPlayerId(),player.getKey()));
            player.addPowerToBowl(religion.updateReligion(townTile.getJewishPoint(),player.getPlayerId(),player.getKey()));
            player.addPowerToBowl(religion.updateReligion(townTile.getHinduismPoint(),player.getPlayerId(),player.getKey()));
        }
    }


   public Player[] getPlayerList() {
      return playerList;
   }

   public CardsAndTiles getCardsAndTiles() {
      return cardsAndTiles;
   }

   public Religion[] getReligions() {
      return religions;
   }

    public int getCurrentPlayerId() {
        return currentPlayerId;
    }

    public void setCurrentPlayerId( int currentPlayerId) {

       this.currentPlayerId = currentPlayerId;
    }
    public int getPlayerCount(){
        return playerCount;
    }

}
