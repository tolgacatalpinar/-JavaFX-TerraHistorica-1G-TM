package Controller;

import Model.*;
import Model.ReligionSubclasses.*;
import Model.CardsAndTiles.BonusCard;
import Model.CardsAndTiles.CardsAndTiles;
import Model.CardsAndTiles.FavorTile;
import Model.CardsAndTiles.TownTile;
import Model.FactionSubclasses.DariusTheGreat;
import Model.FactionSubclasses.VladTheImpaler;
import View.*;


public class GameHandler {

    Player[] playerList;
    CardsAndTiles cardsAndTiles;
    Religion[] religions;
    public GameHandler( int playerSize)
    {
        cardsAndTiles = new CardsAndTiles(playerSize);
        playerList = new Player[playerSize];
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
                player.setHavingDwellingBonus(false);
                player.setHavingSanctuary(false);
                player.setHavingTradeHouse(false);
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

    public void buildTradingPost( Player player)
    {
        //player.buildTradingPost();
        if(player.getFaction() instanceof DariusTheGreat )
        {
            nomadAbility( player);
        }
    }

    public void chooseScoringTile( Player player)
    {
        if( player.getFaction() instanceof VladTheImpaler)
            selectScoringTile(player, 2);
        else
            selectScoringTile(player, 1);
    }
    public void selectScoringTile( Player player, int count) {
        if( count == 0)
        {
            return;
        }
        count --;
        ScoringTile.displayScoringTileChoice();
    }

    public void nomadAbility( Player player)
    {
        // TODO
    }
}
